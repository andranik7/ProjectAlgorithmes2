package com.Graph;

public class Vertex {
    private String stop_name;
    private double stop_lat;
    private double stop_lon;
    public String id;


    public Vertex(String stop_name, double stop_lat, double stop_lon, String id) {
        this.stop_name = stop_name;
        this.stop_lat = stop_lat;
        this.stop_lon = stop_lon;
        this.id = id; // id corresponds to the position in verticesList
    }

    public double getStop_lat() {
        return stop_lat;
    }

    public double getStop_lon() {
        return stop_lon;
    }

    public String getStop_name() {
        return stop_name;
    }
    
    public String getStop_id() {
    	return id;
    }
}
