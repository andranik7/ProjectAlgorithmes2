//package com.Graph;
//
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.HashMap;
//import java.util.List;
//import java.util.PriorityQueue;
//
//public class Dijkstra {
//	private int nbVertices;
//	private HashMap<String, Integer> distances;
//	private List<String> passed;
//	PriorityQueue<Node> pqVertices;
//	WeightedGraph graph;
//
//	public Dijkstra(int vertices, WeightedGraph graph)
//    {
//        this.nbVertices = vertices;
//        distances = new HashMap<String, Integer>(vertices);
//        passed = new ArrayList<String>();
//        pqVertices = new PriorityQueue<Node>(vertices, new Node());
//        this.graph = graph;
//    }
//
//    public void AStarPathFinder(Vertex src)
//    {
//        for (int i = 0; i < nbVertices; i++)
//            distances.put(graph.verticesList.get(i).id, Integer.MAX_VALUE);
//
//        // Add source node to the priority queue
//        pqVertices.add(new Node(src.id, 0));
//
//        // Distance to the source is 0
//        distances.put(src.id, 0);
//        while (passed.size() != nbVertices) {
//
//            // remove the minimum distance node
//            // from the priority queue
//            String uid = pqVertices.remove().vertexUID;
//
//            // adding the node whose distance is
//            // finalized
//            passed.add(uid);
//
//            calculateDistanceNeighbors(graph.getVertexNeighbors(uid), uid); //TODO: utiliser fonction d'andranik
//        }
//    }
//
//    // Function to process all the neighbours
//    // of the passed node
//    private void calculateDistanceNeighbors(List<String> neighbors, String currentVertexUID)
//    {
//        int edgeDistance = -1;
//        int newDistance = -1;
//
//        Vertex currentVertex = graph.verticesList.get(graph.getVertexById(currentVertexUID));
//
//        // All the neighbors of v
//        for (String nUID : neighbors) {
//        	Vertex neighbour = graph.verticesList.get(graph.getVertexById(nUID));
//        	int weight = (int)Math.sqrt(Math.pow((currentVertex.getStop_lat() - neighbour.getStop_lat()),2) + Math.pow((currentVertex.getStop_lon() - neighbour.getStop_lon()),2));
//
//            Node v = new Node(nUID, weight);
//
//            // If current node hasn't already been processed
//            if (!passed.contains(v.vertexUID)) {
//                edgeDistance = v.weight;
//                newDistance = distances.get(v.vertexUID) + edgeDistance;
//
//                // If new distance is cheaper in cost
//                if (newDistance < distances.get(v.vertexUID))
//                    distances.put(v.vertexUID, newDistance);
//
//                // Add the current node to the queue
//                pqVertices.add(new Node(v.vertexUID, distances.get(v.vertexUID)));
//            }
//        }
//    }
//
//}
//
//class Node implements Comparator<Node> {
//    public String vertexUID;
//    public int weight;
//
//    public Node()
//    {
//    }
//
//    public Node(String vertexUID, int weight)
//    {
//        this.vertexUID = vertexUID;
//        this.weight = weight;
//    }
//
//    @Override
//    public int compare(Node node1, Node node2)
//    {
//        if (node1.weight < node2.weight)
//            return -1;
//        if (node1.weight > node2.weight)
//            return 1;
//        return 0;
//    }
//}
