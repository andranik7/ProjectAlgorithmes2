package com.Graph;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


    public class BFS {
    	private List<Vertex> explored;
    	private List<Vertex> priorityQueue;
    	Map<Vertex, Vertex> parent;
        public WeightedGraph graph;

        public BFS(WeightedGraph graph) {
            this.graph = graph;
            explored = new ArrayList<Vertex>();
            priorityQueue = new ArrayList<Vertex>();
            parent = new HashMap<Vertex, Vertex>();
        }

        public List<Vertex> shortestPath(Vertex src, Vertex dest) {

            List<Vertex> path = new ArrayList<Vertex>();

            Map<Vertex, Vertex> parent = bfs(src);
            
            Vertex precedent = dest;
      
            while (precedent != null) {
            	
                path.add(precedent);
                precedent = parent.get(precedent);
                
            }
            Collections.reverse(path);
            return path;
        }

        public Map<Vertex, Vertex> bfs(Vertex src) {

            priorityQueue.add(src);

            List<Vertex> adjacents;
            Vertex actual;

            while (!priorityQueue.isEmpty()) {
            	
                actual = priorityQueue.get(0); priorityQueue.remove(actual);
                
                explored.add(actual);
                
                adjacents = graph.getVertexNeighbors(actual);

                for (Vertex v : adjacents) {
                	
                    if (!explored.contains(v)) {
                    	
                        parent.put(v, actual);
                        
                        if(!priorityQueue.contains(v)) priorityQueue.add(v);
                                           
                    }
                }
            }
            for(Vertex v : priorityQueue) {
            	System.out.println(v);
            }
            
            return parent;
        }
    }
