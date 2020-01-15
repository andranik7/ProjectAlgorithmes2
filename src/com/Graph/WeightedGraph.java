package com.Graph;
import java.util.List;
import java.util.ArrayList;

public class WeightedGraph {
    List<Vertex> verticesList;
    List<Edge> edgesList;

    public WeightedGraph()
    {
    }
    
    public WeightedGraph(List<Vertex> vertices, List<Edge> edges) {
        this.edgesList = edges;
        this.verticesList = vertices;
    }

    public void addEdge(Vertex sourceVertex, Vertex destVertex, double weight) {
        edgesList.add(new Edge(sourceVertex, destVertex, weight));
    }

    public void printGraph() {
        for (int i = 0; i < edgesList.size(); i++) {
            System.out.println(edgesList.get(i).getSource().getStop_name() + " id " + edgesList.get(i).getSource().id +
                    " to " + edgesList.get(i).getDest().getStop_name() + " id " + edgesList.get(i).getDest().id
                    + " weight " + edgesList.get(i).getWeight());
        }
    }

    public Vertex getVertexById(String id) {
        for (int i = 0; i < verticesList.size(); i++) {
            if (verticesList.get(i).id.equals(id)) {
                return verticesList.get(i);
            }
        }
        return null;
    }

    public List<Vertex> getVertexNeighbors(Vertex vertex) {
        List<Vertex> listNeighbors = new ArrayList<>();

        for (int i = 0; i < edgesList.size(); i++) {
            if (edgesList.get(i).getSource() == vertex)
                listNeighbors.add(edgesList.get(i).getDest());
        }
        return listNeighbors;
    }

    public Edge getEdge(Vertex src, Vertex dest) {
        for (int i = 0; i < edgesList.size(); i++) {
            if (edgesList.get(i).getSource() == src && edgesList.get(i).getDest() == dest)
                return edgesList.get(i);
        }
        return null;
    }
}
