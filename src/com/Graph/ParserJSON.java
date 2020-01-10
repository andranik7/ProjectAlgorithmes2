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
	
	public List<String> getAllStations() throws FileNotFoundException{
		List<String> listeStations = new ArrayList<>(); 
		//JSON parser object to parse read file
		
        InputStream is = new FileInputStream(new File("data.json"));

        JSONTokener tokener;
		try {
			tokener = new JSONTokener(is);
	        JSONObject object = new JSONObject(tokener);
	        System.out.println("Stations  : " + object.getJSONObject("stations"));
	        
	        JSONObject stations = object.getJSONObject("stations");
	       
	        @SuppressWarnings("unchecked")
			Iterator<String> keys = stations.keys();

	        while(keys.hasNext()) {
	            String key = keys.next();
	            if (stations.get(key) instanceof JSONObject) {
	                  //System.out.println(key + " " + stations.get(key));

	          	      JSONObject station = stations.getJSONObject(key);
	          	      System.out.println(station.getString("nom"));
	                  if(!listeStations.contains(station.getString("nom"))) {
	                	listeStations.add(station.getString("nom"));
	                  }
	                  
	                  
        	        
	            }
	        }
	        //System.out.println(listeStations);
	        
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listeStations;
	}

}

