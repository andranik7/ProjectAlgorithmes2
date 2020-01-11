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

    public Edge getEdge(Vertex src, Vertex dest){
        for (int i = 0; i < edgesList.size(); i++){
            if (edgesList.get(i).getSource() == src && edgesList.get(i).getDest() == dest)
                return  edgesList.get(i);
        }
        return null;
    }

    private Vertex parentFinder(Vertex vertex, List<List<Vertex>> parents){
        for (List<Vertex> parent : parents){
            if (parent.get(1) == vertex)
                return parent.get(0);
        }
        return null;
    }

   //dijkstra algorithm
    public List<Edge> AStarPathFinder(Vertex src, Vertex dest) {
        List<Vertex> priorityQueue = new ArrayList<>();
        List<Vertex> explored = new ArrayList<>();
        List<Edge> path = new ArrayList<>();
        List<List<Vertex>> parents = new ArrayList<>();
        List<Vertex> list = new ArrayList<>();
        list.add(null);
        list.add(src);
        parents.add(list);
        Vertex currentVertex = src;
        Vertex nextVertex;
        priorityQueue.add(src);

        src.setHeuritic(0);
        do{
            priorityQueue.remove(currentVertex);
            explored.add(currentVertex);
            for (Vertex vertex : getVertexNeighbors(currentVertex)) {
                if (vertex == dest){
                    list.set(0,currentVertex);
                    list.set(1,dest);
                    parents.add(list);
                    nextVertex = vertex;
                    while (currentVertex != null){
                        path.add(getEdge(currentVertex,nextVertex));
                        nextVertex = currentVertex;
                        currentVertex = parentFinder(currentVertex, parents);
                    }
                    return path;
                }
                if (!priorityQueue.contains(vertex) && !explored.contains(vertex)) {
                    vertex.setHeuritic(currentVertex.getHeuritic() + currentVertex.distanceTo(vertex));
                    priorityQueue.add(vertex);
                }
            }
            quickSort(priorityQueue,0,priorityQueue.size()-1,dest);
            nextVertex = priorityQueue.get(priorityQueue.size()-1);
            list.set(0,currentVertex);
            list.set(1,nextVertex);
            parents.add(list);
            currentVertex = nextVertex;
        } while (!priorityQueue.isEmpty());
        return null;
    }

    //here to sort the priorityqueue
    public void quickSort(List<Vertex> list, int begin, int end,Vertex dest) {
        if (list.get(begin).isLessTo(list.get(end),dest)) {
            int partitionIndex = partition(list, begin, end, dest);

            quickSort(list, begin, partitionIndex-1, dest);
            quickSort(list, partitionIndex+1, end, dest);
        }
    }

    //used by quicksort
    private int partition(List<Vertex> list, int begin, int end, Vertex dest) {
        Vertex pivot = list.get(end);
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if (list.get(j).isLessTo(pivot, dest)) {
                i++;
                Vertex swapTemp = list.get(i);
                list.set(i,list.get(j));
                list.set(j,swapTemp);
            }
        }
        Vertex swapTemp = list.get(i+1);
        list.set(i+1,list.get(end));
        list.set(end,swapTemp);
        return i+1;
    }
}