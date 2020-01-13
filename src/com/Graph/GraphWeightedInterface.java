package com.Graph;

import java.util.List;


interface GraphWeightedInterface {
    void addEgde(int source, int destination, int weight);
    void printGraph();
    Vertex getVertexById(String id);
    List<Vertex> getVertexNeighbors(Vertex vertex);
    Edge getEdge(Vertex src, Vertex dest);
    
    


}
