package com.Graph;

import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


    public class AdjacencyDiGraph<graph> {
        protected Set<Vertex> vertices = new HashSet<Vertex>();
        protected Set<Edge> edges = new HashSet<Edge>();
        protected Map<Vertex, List<Edge>> vertexToEdges = new HashMap<Vertex, List<Edge>>();
        private Map<Edge, Vertex> edgeToSrc = new HashMap<Edge, Vertex>();
        private Map<Edge, Vertex> edgeToDest = new HashMap<Edge, Vertex>();
        private Map<String, Vertex> nameToVertex = new HashMap<String, Vertex>();

        public AdjacencyDiGraph() {
        }

        public void addVertex(Vertex v) {
            if (!vertices.contains(v)) {
                vertices.add(v);
                vertexToEdges.put(v, new ArrayList<Edge>());
            }
        }

        public List<Vertex> getVertices() {
            return new ArrayList<Vertex>(vertices);
        }

        public List<Edge> getEdges() {
            return new ArrayList<Edge>(edges);
        }

        public List<Vertex> getAdjacentVertices(Vertex src) {
            List<Vertex> res = new ArrayList<Vertex>();
            for (Edge e : vertexToEdges.get(src)) {
                res.add(edgeToDest.get(e));
            }
            return res;
        }

        public void nameVertex(String name, Vertex v) {
            nameToVertex.put(name, v);
        }

        public Vertex getVertexByName(String name) {
            return nameToVertex.get(name);
        }

        // should use java.util.Optional
        public String getNameOrNullByVertex(Vertex v) {
            for (Map.Entry<String, Vertex> e : nameToVertex.entrySet()) {
                if (e.getValue().equals(v)) {
                    return e.getKey();
                }
            }
            return null;
        }

        public List<String> getNames() {
            return new ArrayList<String>(nameToVertex.keySet());
        }


        public boolean areConnected(Vertex src, Vertex dest) {
            Map<Vertex, Vertex> parent = BFS(src);
            return parent.get(dest) != null;
        }

        public boolean areConnected(String src, String dest) {
            return areConnected(getVertexByName(src), getVertexByName(dest));
        }


        public  List<Vertex> shortestPath(Vertex src, Vertex dest) {

            List<Vertex> path = new ArrayList<Vertex>();
            path.add(dest) ;

            Map<Vertex, Vertex > parent = BFS(src) ;

            Vertex precedent = parent.get(dest);
            while(precedent != null) {
                path.add(0, precedent);
                precedent = parent.get(precedent);
            }
            return path;
        }


        public Map<Vertex, Vertex> BFS(Vertex src) {

            Map<Vertex,Vertex> parent = new HashMap<Vertex, Vertex>();

            List<Vertex> explored = new ArrayList<Vertex>();
            explored.add(src);

            List<Vertex> priorityQueue = new ArrayList<Vertex>();
            priorityQueue.add(src);

            while(!priorityQueue.isEmpty()) {
                int n=0 ;
                Vertex actual = priorityQueue.get(n);

                for (Vertex ver : getAdjacentVertices(actual)) {
                    if (!explored.contains(ver)) {
                        parent.put(ver,actual);
                        priorityQueue.add(ver);
                        explored.add(ver);
                    }
                }
                priorityQueue.remove(actual);
                n++ ;
            }
            return parent ;
        }

        public List<Vertex> shortestPath(String src, String dest) {
            return shortestPath(getVertexByName(src), getVertexByName(dest));
        }


}
