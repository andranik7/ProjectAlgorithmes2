package com.Graph;

import java.io.FileNotFoundException;
import java.util.List;

public class main {

    public main(String[] args) throws FileNotFoundException{

        
        ParserJSON parser = new ParserJSON();
        System.out.println(parser.getAllStations());
        List<Vertex> listeStations = parser.getAllStations();
        
        
        // Instanciation of graph by passing the type of the class
        WeightedGraph graph = new WeightedGraph(listeStations);
        
        graph.addEgde(0, 1, 10);
        graph.addEgde(0, 2, 5);
        graph.addEgde(2, 3, 5);
        graph.addEgde(4, 5, 5);
        graph.addEgde(5, 6, 5);

       /* graph.printGraph();*/
        

        /*System.out.println(graph.getVertexById("1795"));*/

        AdjacencyDiGraph bfs = new AdjacencyDiGraph(graph);

        System.out.println(bfs.shortestPath(1698,1696));

        graph.addEgde(0, 5, 5);
        graph.addEgde(0, 6, 5);
        graph.printGraph();
        

        System.out.println(graph.getVertexNeighbors("A_1970"));

    }
}
