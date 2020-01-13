package com.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

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
        
        // Initialize the weightsFromSrc map (set all weights on MAX_VALUE)
        for (int i = 0; i < nbVertices; i++)
            weightsFromSrc.put(graph.verticesList.get(i), Double.MAX_VALUE);
        
        parents = new HashMap<Vertex, Vertex>();
        passed = new ArrayList<Vertex>();
        pqVertices = new PriorityQueue<Node>(nbVertices, new Node());
        this.graph = graph;
    }

    public void dijkstra(Vertex src)
    {
    	

        // Add source node to the priority queue
        pqVertices.add(new Node(src, 0));

        // Distance to the source is 0
        weightsFromSrc.put(src, 0.);
        while (passed.size() != nbVertices) {

            // remove the minimum distance vertex from the priority queue
            Vertex currentVertex = pqVertices.remove().baseVertex;

            // adding the vertex whose distance is being calculated
            passed.add(currentVertex);
            
            double weight;
            double newWeight;
            
            // Process all the neighbours of the passed vertex
            for (Vertex vertex : graph.getVertexNeighbors(currentVertex)) {
            	
                // If current vertex hasn't already been processed
                if (!passed.contains(vertex)) {
                	weight = currentVertex.distanceTo(vertex);
                	newWeight = weightsFromSrc.get(currentVertex) + weight;

                    // If new distance is less
                    if (newWeight < weightsFromSrc.get(vertex))
                    {                	
                    	parents.put(vertex, currentVertex);      
                    	weightsFromSrc.put(vertex, newWeight);
                    	
                        // Add the current vertex to the priotity queue
                        pqVertices.add(new Node(vertex, weightsFromSrc.get(vertex)));
                    }
                }
            }
        }
    }

    
    public Pair<Double, LinkedList<Vertex>> getWeightedPath(Vertex dest){
    	LinkedList<Vertex> path = new LinkedList<Vertex>();
    	
    	if(parents.get(dest) == null)
    		return null;
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
