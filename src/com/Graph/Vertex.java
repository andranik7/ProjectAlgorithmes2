package com.Graph;

import java.util.Comparator;

public class Vertex {
    private String stop_name;
    private double stop_lat;
    private double stop_lon;
    private double heuritic;
    public String id;


    public Vertex(String stop_name, double stop_lat, double stop_lon, String id) {
        this.stop_name = stop_name;
        this.stop_lat = stop_lat;
        this.stop_lon = stop_lon;
        this.heuritic = 0;
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

    public void setHeuritic(double heuritic) {
        this.heuritic = heuritic;
    }

    public double getHeuritic() {
        return heuritic;
    }

    public double distanceTo(Vertex dest){
        return Math.sqrt(Math.pow(Math.abs(stop_lat - dest.getStop_lat()),2) +
                Math.pow(Math.abs(stop_lon - dest.getStop_lon()),2));
    }

    public boolean isLessTo (Vertex comp, Vertex dest) {
        if (heuritic + this.distanceTo(dest) - comp.getHeuritic() + comp.distanceTo(dest) > 0)
            return true;
        else
            return false;
    }

}

