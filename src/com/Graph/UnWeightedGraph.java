package com.Graph;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class UnWeightedGraph implements GraphUnWeightedInterface {
    int vertices;
    // both are related
    LinkedList<Edge>[] adjacencylist; // corresponds to edges list
    List<Vertex> verticesList = new ArrayList<>(); // corresponds to vertices

    /**
     * Create new Graph object.
     */
    public UnWeightedGraph(int vertices) {
        // creation of adjecency list
        this.vertices = vertices;

        adjacencylist = new LinkedList[vertices];
        //initialize adjacency lists for all the vertices
        for (int i = 0; i <vertices ; i++) {
            Vertex v = new Vertex("Rue de la Pompe", 48.122, 2.3912, "i");
            verticesList.add(v); // adding the vertex
            adjacencylist[i] = new LinkedList<>(); // adding the edges
        }
    }



    @Override
    public void addEgde(int indexSource, int indexDestination) {
        Edge edgeFromSource = new Edge(indexSource, indexDestination, 1);
        Edge edgeFromDestination = new Edge(indexDestination, indexSource, 1);
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

}