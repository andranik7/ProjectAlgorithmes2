package com.Graph;

import org.json.JSONException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) throws FileNotFoundException, JSONException {

        ParserJSON parser = new ParserJSON();
        List<Vertex> verticesList = parser.getAllStations();
        WeightedGraph graph = new WeightedGraph(verticesList, new ArrayList<>());
        parser.getAllEdges(graph);


 
        graph.printGraph();
        
        Dijkstra dijk = new Dijkstra(graph);
        
        dijk.dijkstra(graph.getVertexById("1621"),graph.getVertexById("B_1998"));
    }
}
