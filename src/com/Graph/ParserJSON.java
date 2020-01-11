package com.Graph;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.json.*;
import java.util.ArrayList;


public class ParserJSON {
	public ParserJSON () throws FileNotFoundException {

	}

	public List<Vertex> getAllStations() throws FileNotFoundException{
		List<String> listeNomsStations = new ArrayList<>();
		List<Vertex> listeVertexStations = new ArrayList<>();
		//JSON parser object to parse read file

		InputStream is = new FileInputStream(new File("data.json"));

		JSONTokener tokener;
		try {
			tokener = new JSONTokener(is);
			JSONObject object = new JSONObject(tokener);
			//System.out.println("Stations  : " + object.getJSONObject("stations"));

			JSONObject stations = object.getJSONObject("stations");

			@SuppressWarnings("unchecked")
			Iterator<String> keys = stations.keys();

	        while(keys.hasNext()) {
	            String key = keys.next();
	            if (stations.get(key) instanceof JSONObject) {
	                  //System.out.println(key + " " + stations.get(key));

	          	      JSONObject station = stations.getJSONObject(key);
	          	      //System.out.println(station.getString("nom"));
	                  if(!listeNomsStations.contains(station.getString("nom"))) {
	                	listeNomsStations.add(station.getString("nom"));
	                	double lat = Double.parseDouble(station.getString("lat"));
	                	double lng = Double.parseDouble(station.getString("lng"));
	                	Vertex v = new Vertex(station.getString("nom"), lat, lng, key);
	                	System.out.println(key);
	                	listeVertexStations.add(v);
	                  }
	                 
        	        
	            }
	        }
	        //System.out.println(listeNomsStations);
	        

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listeVertexStations;
	}

	public void getAllEdges(WeightedGraph graph) throws FileNotFoundException, JSONException {
		String prevArret;
		String arret;
		int vertex1;
		int vertex2;
		//JSON parser object to parse read file
		InputStream is = new FileInputStream(new File("data.json"));

		JSONTokener tokener;
		try {
			tokener = new JSONTokener(is);
			JSONObject object = new JSONObject(tokener);

			JSONArray routes = object.getJSONArray("routes");

			for (int i = 0; i < routes.length(); i++){
				JSONObject route = routes.getJSONObject(i);
				JSONArray arrets = route.getJSONArray("arrets");
				for (int j = 1; j < arrets.length(); j++){
					arret = arrets.getString(j);
					prevArret = arrets.getString(j-1);
					vertex1 = graph.getVertexById(prevArret);
					vertex2 = graph.getVertexById(arret);
					graph.addEgde(vertex1,vertex2,10);
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		graph.printGraph();
	}

}

