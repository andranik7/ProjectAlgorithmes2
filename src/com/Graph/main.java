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

        //graph.printGraph();
        System.out.println("----- path -----");
        List<Edge> path = graph.AStarPathFinder(verticesList.get(3),verticesList.get(35));

        for (int i = path.size()-1; i >= 0 ; i--){
            System.out.println(path.get(i).getSource().getStop_name() + " id " + path.get(i).getSource().id +
                    " to " + path.get(i).getDest().getStop_name() + " id " + path.get(i).getDest().id
                    + " weight " + path.get(i).getWeight());
        }
 
        graph.printGraph();
        
        Dijkstra dijk = new Dijkstra(graph);
        
        dijk.dijkstra(graph.getVertexById("A_1643"),graph.getVertexById("4009379"));
    }
}
