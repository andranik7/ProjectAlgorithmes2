//package com.Graph;
//
//import java.util.Set;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.HashMap;
//import java.util.List;
//import java.util.ArrayList;
//
//
//    public class AdjacencyDiGraph<graph> {
//        protected Set<Vertex> vertices = new HashSet<Vertex>();
//        protected Set<Edge> edges = new HashSet<Edge>();
//        protected Map<Vertex, List<Edge>> vertexToEdges = new HashMap<Vertex, List<Edge>>();
//        private Map<Edge, Vertex> edgeToSrc = new HashMap<Edge, Vertex>();
//        private Map<Edge, Vertex> edgeToDest = new HashMap<Edge, Vertex>();
//        private Map<String, Vertex> nameToVertex = new HashMap<String, Vertex>();
//        public WeightedGraph graph;
//
//        public AdjacencyDiGraph(WeightedGraph graph) {
//            this.graph = graph;
//        }
//
//
//        public Vertex getVertexByName(String name) {
//            return nameToVertex.get(name);
//        }
//
//
//        /*public boolean areConnected(Integer src, Integer dest) {
//            Map<Integer, Integer> parent = BFS(list, src);
//            return parent.get(dest) != null;
//        }
//
//        public boolean areConnected(String src, String dest) {
//            return areConnected(getVertexByName(src), getVertexByName(dest));
//        }*/
//
//
//        public List<String> shortestPath(String src, String dest) {
//
//            List<String> path = new ArrayList<String>();
//            path.add(dest);
//
//            Map<String, String> parent = BFS(list, src);
//
//            String precedent = parent.get(dest);
//            while (precedent != null) {
//                path.add(0, precedent);
//                precedent = parent.get(precedent);
//            }
//            return path;
//        }
//
//        List<Vertex> list;
//
//        public Map<String, String> BFS(List<Vertex> list, String id) {
//
//            Map<String, String> parent = new HashMap<String, String>();
//
//            List<String> explored = new ArrayList<String>();
//            explored.add(id);
//
//            List<String> priorityQueue = new ArrayList<String>();
//            priorityQueue.add(id);
//
//
//            while (!priorityQueue.isEmpty()) {
//                int n = 0;
//                String actual = priorityQueue.get(n);
//
//
//                List<Integer> adjacents = graph.getVertexNeighbors(String.valueOf(id));
//                List<String> newList = new ArrayList<String>(adjacents.size());
//                for (Integer myInt : adjacents) {
//                    newList.add(String.valueOf(myInt));
//
//                    for (String ver : newList) {
//                        if (!explored.contains(ver)) {
//                            parent.put(ver, actual);
//                            priorityQueue.add(ver);
//                            explored.add(ver);
//                        }
//                    }
//                    priorityQueue.remove(actual);
//                    n++;
//                }
//                return parent;
//            }
//            return parent;
//        /*public List<Vertex> shortestPath(String src, String dest) {
//            return shortestPath(getVertexByName(src), getVertexByName(dest));
//        }
//
//
//        }
//    }
