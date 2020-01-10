package com.Graph;

import java.io.FileNotFoundException;
import java.util.List;

public class main {

    private static Object Vertex;

    public static void main(String[] args) throws FileNotFoundException{

        
        ParserJSON parser = new ParserJSON();
        System.out.println(parser.getAllStations());
        List<String> listeStations = parser.getAllStations();
        
        
        // Instanciation of graph by passing the type of the class
        WeightedGraph graph = new WeightedGraph(listeStations);
        
        graph.addEgde(0, 1, 10);
        graph.addEgde(0, 2, 5);
        graph.printGraph();
    }
}
