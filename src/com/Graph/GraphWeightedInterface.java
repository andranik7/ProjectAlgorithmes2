package com.Graph;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;


interface GraphWeightedInterface {
    void addEgde(int source, int destination, int weight);
    void printGraph();
    Vertex getVertexById(String id);
    List<Vertex> getVertexNeighbors(Vertex vertex);
    Edge getEdge(Vertex src, Vertex dest);
    
    


}
