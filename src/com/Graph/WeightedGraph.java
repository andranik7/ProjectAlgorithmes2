package com.Graph;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class WeightedGraph implements GraphWeightedInterface {
    int vertices;
    // both are related
    LinkedList<Edge>[] adjacencylist; // corresponds to edges list
    List<Vertex> verticesList; // corresponds to vertices

    /**
     * Create new Graph object.
     */
    public WeightedGraph(List<Vertex> liste) {
        // creation of adjecency list
        this.vertices = liste.size();

        adjacencylist = new LinkedList[vertices];
        verticesList = liste;
        //initialize adjacency lists for all the vertices
        
        for (int i = 0; i <vertices ; i++) {
            adjacencylist[i] = new LinkedList<>(); // adding the edges
        }
    }


    @Override
    public void addEgde(int indexSource, int indexDestination, int weight) {
        Edge edgeFromSource = new Edge(indexSource, indexDestination, weight);
        Edge edgeFromDestination = new Edge(indexDestination, indexSource, weight);
        adjacencylist[indexSource].add(edgeFromSource);
        adjacencylist[indexDestination].add(edgeFromDestination);
    }

    @Override
    public void printGraph(){
        for (int i = 0; i <vertices ; i++) {
            LinkedList<Edge> list = adjacencylist[i];
            for (int j = 0; j <list.size() ; j++) {
                System.out.println("vertex-" + i + " "+ verticesList.get(i).getStop_name()+ " is connected to " +
                        list.get(j).destination + " " +verticesList.get(j).getStop_name() + " with weight " +  list.get(j).weight);
            }
        }
    }
    
    // fonction pour renvoyer l'index du vertex à partir de son id
    public int getVertexById(String id) {
    	// looping verticeslist to get it
    	for(int i=0; i<verticesList.size(); i++) {
    		    		if(verticesList.get(i).getStop_id().equals(id)) {
    			return i;
    		}
    	}
		return 0;
    }
    
    // fonction pour renvoyer l'index du vertex à partir de son id
    public List<String> getVertexNeighbors(String id) {
    	List<String> listeNeighbors = new ArrayList<>();
    	// looping verticeslist to get it
    	for(int i=0; i<verticesList.size(); i++) {
    		//System.out.println(verticesList.get(i).getStop_id());
    		if(verticesList.get(i).getStop_id().equals(id)) {
    			for(int j=0; j < adjacencylist[i].size(); j++) {
    				listeNeighbors.add(verticesList.get(adjacencylist[i].get(j).getDestination()).id);
    			}
    			return listeNeighbors;
    		}
    	}
    	return null;
		
    }

}