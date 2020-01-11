package com.Graph;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.json.*;
import java.util.ArrayList;


public class ParserJSON {

	private JSONObject openFile() throws FileNotFoundException {
		InputStream is = new FileInputStream(new File("data.json"));
		JSONTokener tokener;
		try {
			tokener = new JSONTokener(is);
			JSONObject object = new JSONObject(tokener);
			return object;
		} catch(JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Vertex> getAllStations() throws FileNotFoundException, JSONException {
		List<Vertex> verticesList = new ArrayList<>();
		JSONObject stations = openFile().getJSONObject("stations");

		@SuppressWarnings("unchecked")
		Iterator<String> key = stations.keys();
		while(key.hasNext()) {
			String id = key.next();
			JSONObject station = stations.getJSONObject(id);
			double lat = Double.parseDouble(station.getString("lat"));
			double lng = Double.parseDouble(station.getString("lng"));
			Vertex v = new Vertex(station.getString("nom"), lat, lng, id);
			verticesList.add(v);
		}
		return verticesList;
	}

	public void getAllEdges(WeightedGraph graph) throws FileNotFoundException, JSONException {
		Vertex vertexSource;
		Vertex vertexDest;
		JSONArray routes = openFile().getJSONArray("routes");


		for (int i = 0; i < routes.length(); i++){
			JSONObject route = routes.getJSONObject(i);
			JSONArray stops = route.getJSONArray("arrets");
			for (int j = 1; j < stops.length(); j++) {
				vertexSource = graph.getVertexById(stops.getString(j - 1));
				vertexDest = graph.getVertexById(stops.getString(j));
				graph.addEdge(vertexSource, vertexDest, 10.0);
			}
		}
	}
}

