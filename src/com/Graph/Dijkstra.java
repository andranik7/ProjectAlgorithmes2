package com.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
	private int nbVertices;
	private HashMap<Vertex, Double> weightsFromSrc;
	private HashMap<Vertex, Vertex> parents;
	private List<Vertex> passed;
	PriorityQueue<Node> pqVertices;
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

    public void dijkstra(Vertex src, Vertex dest)
    {
    	ArrayList<Vertex> basePath = new ArrayList<Vertex>();
    	basePath.add(src);
        for (int i = 0; i < nbVertices; i++)
            weightsFromSrc.put(graph.verticesList.get(i), Double.MAX_VALUE);

        // Add source node to the priority queue
        pqVertices.add(new Node(src, 0));

        // Distance to the source is 0
        weightsFromSrc.put(src, 0.);
        while (passed.size() != nbVertices) {

            // remove the minimum distance node
            // from the priority queue
            Vertex firstVertex = pqVertices.remove().baseVertex;

            // adding the node whose distance is
            // finalized
            passed.add(firstVertex);
            
            calculateDistanceNeighbors(graph.getVertexNeighbors(firstVertex), firstVertex); 
        }
        
        LinkedList<Vertex> shortestPath = getPath(dest);
        System.out.println("----- Shortest path from " + src.getStop_name() + " to " + dest.getStop_name() + " -----");

        //System.out.println(shortestPath.getValue().size());
        for(Vertex v : shortestPath)
        {
        	if(v == dest) System.out.println(v.getStop_name());
        	else System.out.print(v.getStop_name() + " -> ");
        }

        System.out.println("--------------------------------------------------");
        System.out.println("Total Weight of the path: " + weightsFromSrc.get(dest));
        
    }

    // Function to process all the neighbours
    // of the passed node
    private void calculateDistanceNeighbors(List<Vertex> neighbors, Vertex currentVertex)
    {
    	double weight;
        double newWeight;
        
        // All the neighbors of v
        for (Vertex vertex : neighbors) {
        	
            // If current node hasn't already been processed
            if (!passed.contains(vertex)) {
            	weight = Math.sqrt(Math.pow((currentVertex.getStop_lat() - vertex.getStop_lat()),2) + Math.pow((currentVertex.getStop_lon() - vertex.getStop_lon()),2));
                newWeight = weightsFromSrc.get(currentVertex) + weight;

                // If new distance is cheaper in cost
                if (newWeight < weightsFromSrc.get(vertex))
                {                	
                	parents.put(vertex, currentVertex);      
                	weightsFromSrc.put(vertex, newWeight);
                	
                    // Add the current node to the queue
                    pqVertices.add(new Node(vertex, weightsFromSrc.get(vertex)));
                }
            }
        }
    }
    
    private LinkedList<Vertex> getPath(Vertex dest){
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
    	return path;
    }

}

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
