
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Main {
	
    public static void main(String[] args) {
    	
    	// -------------------------------
    	// Initialing graph from json data
    	
    	WeightedGraph graph = new WeightedGraph();
    	try {
    		
	        ParserJSON parser = new ParserJSON();
	        List<Vertex> verticesList = parser.getAllStations();
	        graph = new WeightedGraph(verticesList, new ArrayList<>());
	        parser.getAllEdges(graph);
    	
    	} catch(Exception e) { e.printStackTrace(); }
    	
    	// ------------
    	// Begin Tests:
    	
        Vertex src = graph.getVertexById("A_1643"); // Sartrouville (RER A)
        Vertex dest = graph.getVertexById("1768"); // Notre-Dame-Des-Champs (Metro 12)
        
        // --------------------------------------
        // Defining the shortest path with a BFS:
        /*
        BFS adjDiGraph = new BFS(graph);
        List<Vertex> shortestPathBFS = adjDiGraph.shortestPath(src, dest);
        
        System.out.println("----- Shortest path from " + src + " to " + dest + " according to BFS: -----");
        for(Vertex v : shortestPathBFS)
        {
        	if(v == dest) System.out.println(v);
        	else System.out.print(v + " -> ");
        }
        System.out.println("--------------------------------------------------");
        System.out.println("Amount of stops: " + shortestPathBFS.size());
        System.out.println("--------------------------------------------------");
        */
        
        
        // -----------------------------------------
        // Defining the shortest path with Dijkstra:
    	
        Dijkstra dijk = new Dijkstra(graph);
        /*HashMap<Vertex, Double> map = dijk.dijkstra(src);
        
        /*for(Vertex v : map.keySet())
        	System.out.println(v+" weight: "+map.get(v));
        
        /*
        Pair<Double, LinkedList<Vertex>> weightedPath = dijk.getWeightedPath(src, dest);

        LinkedList<Vertex> shortestPathDjikstra = weightedPath.getValue();
        System.out.println("----- Shortest path from " + src + " to " + dest + " according to Dijkstra: -----");

        for(Vertex v : shortestPathDjikstra)
        {
        	if(v == dest) System.out.println(v);
        	else System.out.print(v + " -> ");
        }

        System.out.println("--------------------------------------------------");
        System.out.println("Total Weight of the path: " + weightedPath.getKey());
        System.out.println("--------------------------------------------------");
        */
        System.out.println(dijk.calculateDiameter());
    }
}
