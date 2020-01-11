package com.Graph;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class WeightedGraph {
    List<Vertex> verticesList;
    List<Edge> edgesList;

    public WeightedGraph(List<Vertex> vertices, List<Edge> edges) {
        this.edgesList = edges;
        this.verticesList = vertices;
    }

    public void addEdge(Vertex sourceVertex, Vertex destVertex, double weight) {
        edgesList.add(new Edge(sourceVertex, destVertex, weight));
    }

    public void printGraph(){
        for (int i = 0; i < edgesList.size(); i++){
            System.out.println(edgesList.get(i).getSource().getStop_name() + " id " + edgesList.get(i).getSource().id +
                    " to " + edgesList.get(i).getDest().getStop_name() + " id " + edgesList.get(i).getDest().id
                    + " weight " + edgesList.get(i).getWeight());
        }
    }

    public Vertex getVertexById(String id) {
    	for(int i = 0; i < verticesList.size(); i++) {
    	    if(verticesList.get(i).id.equals(id)) {
    			return verticesList.get(i);
    		}
    	}
		return null;
    }

    public List<Vertex> getVertexNeighbors(Vertex vertex) {
    	List<Vertex> listNeighbors = new ArrayList<>();

    	for(int i = 0; i < edgesList.size(); i++){
    	    if (edgesList.get(i).getSource() == vertex)
    	        listNeighbors.add(edgesList.get(i).getDest());
        }
    	return listNeighbors;
    }

    public List<Edge> AStarPathFinder(Vertex src, Vertex dest) {
        List<Vertex> priorityQueue = new ArrayList<>();
        List<Vertex> explored = new ArrayList<>();
        List<Vertex> path = new ArrayList<>();
        src.setHeuritic(0);
        Vertex prevVertex = src;

        do{
            for (Vertex vertex : getVertexNeighbors(prevVertex)) {
                if (vertex.equals(dest)){
                    return path;
                }
                if (!priorityQueue.contains(vertex)) {
                    vertex.setHeuritic(prevVertex.getHeuritic() + prevVertex.distanceTo(vertex));
                    priorityQueue.add(vertex);
                }
            }
        } while (!priorityQueue.isEmpty());

    }

    private void quickSort(int arr[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex-1);
            quickSort(arr, partitionIndex+1, end);
        }
    }

    private int partition(int arr[], int begin, int end) {
        int pivot = arr[end];
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }
        int swapTemp = arr[i+1];

        arr[i+1] = arr[end];
        arr[end] = swapTemp;

        return i+1;
    }
}