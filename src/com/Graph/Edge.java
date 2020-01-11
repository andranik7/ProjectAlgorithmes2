package com.Graph;

public class Edge {
    Vertex source;
    Vertex destination;
    double weight;

    public Edge(Vertex source, Vertex destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Vertex getSource() {
    	return source;
    }
    
    public Vertex getDest() {
    	return destination;
    }
}
