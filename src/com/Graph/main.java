package com.Graph;

import java.io.FileNotFoundException;

public class main {

    private static Object Vertex;

    public static void main(String[] args) throws FileNotFoundException{
        // Instanciation of graph by passing the type of the class
        WeightedGraph graph = new WeightedGraph(10);
        
        graph.addEgde(0, 1, 10);
        graph.addEgde(0, 2, 5);
        graph.printGraph();
        
        ParserJSON parser = new ParserJSON();

    }
}
