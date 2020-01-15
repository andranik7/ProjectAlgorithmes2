package com.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

import javafx.util.Pair;

public class Dijkstra {
	private int nbVertices;
	private HashMap<Vertex, Double> weightsFromSrc;
	private HashMap<Vertex, Vertex> parents;
	private List<Vertex> passed;
	private PriorityQueue<Node> pqVertices;
	WeightedGraph graph;

	public Dijkstra(WeightedGraph graph)
    {
        this.nbVertices = graph.verticesList.size();
        weightsFromSrc = new HashMap<Vertex, Double>(nbVertices);
        parents = new HashMap<Vertex, Vertex>();
        passed = new ArrayList<Vertex>();
        pqVertices = new PriorityQueue<Node>(nbVertices, new Node());
        this.graph = graph;
    }

    // TODO : when going through the graph, increment the "nbSortestPaths" parameter in Edge for cluster recognition
    // TODO : then implement a graph recognizer algorithm, then print those.
    // TODO : to achieve the counting of nbShortestPath we need to find the shortest path between each pair of vertices
    public HashMap<Vertex, Double> dijkstra(Vertex src)
    {
        // Initialize the weightsFromSrc map (set all weights on MAX_VALUE)
        for (Vertex v : graph.verticesList)
            weightsFromSrc.put(v, Double.MAX_VALUE);
    	
        
        // Add source node to the priority queue
        pqVertices.add(new Node(src, 0));
        
        // Distance to the source is 0
        weightsFromSrc.put(src, 0.);
        
        while (passed.size() != nbVertices) {
            // get the minimum weight vertex from the priority queue
        	// and remove it
            Vertex currentVertex = pqVertices.remove().baseVertex;

            // adding the vertex whose weight is being calculated
            passed.add(currentVertex);
            
            processNeighbours(currentVertex);
        }
        return weightsFromSrc;
    }

    private void processNeighbours(Vertex currentVertex) {
        double weight = -1;
        double newWeight = -1;

    	// Process all the neighbours of the passed vertex
        for (Vertex vertex : graph.getVertexNeighbors(currentVertex)) {
        	
            // If current neighbour hasn't already been processed
            if (!passed.contains(vertex)) {
            	weight = currentVertex.distanceTo(vertex);
            	newWeight = weightsFromSrc.get(currentVertex) + weight;
                // If new distance is less
                if (newWeight < weightsFromSrc.get(vertex))
                {                	    
                	weightsFromSrc.put(vertex, newWeight);
                	parents.put(vertex, currentVertex);                
                	
                    // Add the current neighbour to the priotity queue
                    pqVertices.add(new Node(vertex, weightsFromSrc.get(vertex)));    	
                }
            }
        }
    }
    
    public Pair<Double, LinkedList<Vertex>> getWeightedPath(Vertex src, Vertex dest){
    	LinkedList<Vertex> path = new LinkedList<Vertex>();
    	
    	dijkstra(src);
    	
    	if(parents.get(dest) == null) {System.out.println("No data for: "+dest); return null;}
    	
    	path.add(dest);
    	Vertex step = dest;
    	while(parents.get(step) != null) {
    		step = parents.get(step);
    		path.add(step);
    	}
    	Collections.reverse(path);
    	
    	double weight = weightsFromSrc.get(dest);
    	return new Pair<Double, LinkedList<Vertex>>(weight, path);
    }
    
    public double calculateDiameter() {
    	HashMap<Vertex, Double> vertexToWeights = new HashMap<Vertex, Double>();
    	List<Double> minWeights = new ArrayList<Double>();
    	
    	for(Vertex v : graph.verticesList) {
    		vertexToWeights.putAll(dijkstra(v));
    		
    		ValueComparator vComp = new ValueComparator(vertexToWeights);
    		TreeMap<Vertex, Double> sortedMap = new TreeMap<Vertex, Double>(vComp);
    		sortedMap.putAll(vertexToWeights);
    		sortedMap.remove(v);
    		if(sortedMap.get(sortedMap.firstKey()) != Double.MAX_VALUE)
    		    minWeights.add(sortedMap.get(sortedMap.firstKey()));
    	}
    	Collections.sort(minWeights);
    	return minWeights.get(minWeights.size()-1);
    }

}

// -----------------------------------------------------------------
// Class implemented in order to use the PriorityQueue object
// @param: 
// - baseVertex : vertex concerned by the node object
// - weight : weight of the shortest path from the src to baseVertex
// -----------------------------------------------------------------
class Node implements Comparator<Node> {
    public Vertex baseVertex;
    public double weight;

    public Node()
    {
    }
    
    public Node(Vertex baseVertex, double weight)
    {
        this.baseVertex = baseVertex;
        this.weight = weight;
    }

    @Override
    public int compare(Node node1, Node node2)
    {
        if (node1.weight < node2.weight)
            return -1;
        if (node1.weight > node2.weight)
            return 1;
        return 0;
    }
}

class ValueComparator implements Comparator<Vertex> {

	Map<Vertex, Double> map;
	
	public ValueComparator(Map<Vertex, Double> map) {
		this.map = map;
	}
	
	@Override
	public int compare(Vertex v1, Vertex v2) {
		if (map.get(v1) < map.get(v2))
			return -1;
		if (map.get(v1) > map.get(v2))
			return 1;
		return 0;
	}
	
}
