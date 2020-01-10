package com.Graph;

import org.json.JSONException;

import java.io.FileNotFoundException;
import java.util.List;

public class main {

    private static Object Vertex;

    public static void main(String[] args) throws FileNotFoundException, JSONException {

        
        ParserJSON parser = new ParserJSON();
        System.out.println(parser.getAllStations());
        List<Vertex> listeStations = parser.getAllStations();
        // Instanciation of graph by passing the type of the class
        WeightedGraph graph = new WeightedGraph(listeStations);
        parser.getAllEdges(graph);
        
//        graph.addEgde(0, 1, 10);
//        graph.addEgde(0, 2, 5);
//        graph.addEgde(0, 5, 5);
//        graph.addEgde(0, 6, 5);
        graph.printGraph();
        

        //System.out.println(graph.getVertexNeighbors("A_1970"));

    }
}
