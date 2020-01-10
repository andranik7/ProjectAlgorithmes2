package com.Graph;

public class Edge {
    int source;
    int destination;
    int weight;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public int getSource() {
    	return source;
    }
    
    public int getDestination() {
    	return destination;
    }
}
