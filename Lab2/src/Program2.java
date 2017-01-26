/*
 * Name: Atreya Misra
 * EID: am73676
 */

import java.util.ArrayList;

/* Your solution goes in this file.
 *
 * Please do not modify the other files we have provided for you, as we will use
 * our own versions of those files when grading your project. You are
 * responsible for ensuring that your solution works with the original version
 * of all the other files we have provided for you.
 * 
 * That said, please feel free to add additional files and classes to your
 * solution, as you see fit. We will use ALL of your additional files when
 * grading your solution.
 */

public class Program2 extends VertexNetwork {
    /* DO NOT FORGET to add a graph representation and 
       any other fields and/or methods that you think 
       will be useful. 
       DO NOT FORGET to modify the constructors when you 
       add new fields to the Program2 class. */
    
    Program2() {
        super();
    }
    
    Program2(String locationFile) {
        super(locationFile);
    }
    
    Program2(String locationFile, double transmissionRange) {
        super(locationFile, transmissionRange);
    }
    
    Program2(double transmissionRange, String locationFile) {
        super(transmissionRange, locationFile);
    }

    public ArrayList<Vertex> gpsrPath(int sourceIndex, int sinkIndex) {
        /* This method returns a path from a source at location sourceIndex 
           and a sink at location sinkIndex using the GPSR algorithm. An empty 
           path is returned if the GPSR algorithm fails to find a path. */
        /* The following code is meant to be a placeholder that simply 
           returns an empty path. Replace it with your own code that 
           implements the GPSR algorithm. */
    	Vertex currentVertex;
    	Edge currentEdge;
    	int currentVertexIndex;
    	int x = 0;
    	int y = 0;
    	int i = 0;
    	ArrayList<Vertex> path = new ArrayList<Vertex>();
    	ArrayList<Integer> pathIndices = new ArrayList<Integer>();
    	path.add(location.get(sourceIndex));
    	pathIndices.add(sourceIndex);
    	while(x == 0){
    		currentVertex = path.get(path.size() - 1);
    		currentVertexIndex = pathIndices.get(pathIndices.size() - 1);
    		if(currentVertex.equals(location.get(sinkIndex)))
    			break;
    		ArrayList<Integer> possibleVertices = new ArrayList<Integer>();
    		for(i = 0; i < edges.size(); i++){    			
    			currentEdge = edges.get(i);
    			if(currentVertexIndex == currentEdge.getU())
    				if (transmissionRange > currentVertex.distance(location.get(currentEdge.getV())))
    					possibleVertices.add(currentEdge.getV());
    			if(currentVertexIndex == currentEdge.getV())
    				if (transmissionRange > currentVertex.distance(location.get(currentEdge.getU())))
    					possibleVertices.add(currentEdge.getU());    				
    		}
    		int vertexClosest;
    		if(possibleVertices.isEmpty()){
    			path.clear();
    			break;
    		}
    		else
    			vertexClosest = possibleVertices.get(0);
    		for(i = 0; i < possibleVertices.size(); i++){
    			if(location.get(possibleVertices.get(i)).distance(location.get(sinkIndex)) < location.get(vertexClosest).distance(location.get(sinkIndex)))
    				vertexClosest = possibleVertices.get(i);
    		}
    		if(location.get(vertexClosest).distance(location.get(sinkIndex)) > currentVertex.distance(location.get(sinkIndex))){
    			path.clear();
    			break;
    		}
    		for(i = 0; i < path.size(); i++){
    			if(path.get(i) == location.get(vertexClosest)){
    				path.clear();
    				y = 1;
    				break;
    			}
    				
    		}
    		if(y == 1)
    			break;
    		path.add(location.get(vertexClosest));
    		pathIndices.add(vertexClosest);
    	}    	
        return path;
    }
    
    public ArrayList<Vertex> dijkstraPathLatency(int sourceIndex, int sinkIndex) {
    	/* This method returns a path (shortest in terms of latency) from a source at
           location sourceIndex and a sink at location sinkIndex using Dijkstra's algorithm.
           An empty path is returned if Dijkstra's algorithm fails to find a path. */
        /* The following code is meant to be a placeholder that simply 
           returns an empty path. Replace it with your own code that 
           implements Dijkstra's algorithm. */

    	ArrayList<Double> distances = new ArrayList<Double>();
    	ArrayList<ArrayList<Vertex>> predecessors = new ArrayList<ArrayList<Vertex>>();
    	ArrayList<Integer> unvisited = new ArrayList<Integer>();
    	ArrayList<Vertex> start = new ArrayList<Vertex>();
    	start.add(location.get(sourceIndex));
    	Edge currentEdge;
    	Vertex currentVertex;
    	int x = 0;
    	int i = 0;
		for(i = 0; i < location.size(); i++){    			
			distances.add(Double.POSITIVE_INFINITY);
			unvisited.add(i);
			predecessors.add(start);
		}
		distances.set(sourceIndex, 0.0);
		currentVertex = location.get(sourceIndex);
		int currentVertexIndex = sourceIndex;	
		while(x == 0){
			ArrayList<Integer> possibleVertices = new ArrayList<Integer>();
			ArrayList<Double> possibleEdges = new ArrayList<Double>();
			ArrayList<Vertex> tempPath = new ArrayList<Vertex>();
			tempPath = predecessors.get(currentVertexIndex);
    		for(i = 0; i < edges.size(); i++){    			
    			currentEdge = edges.get(i);
    			if(currentVertexIndex == currentEdge.getU())
    				if (transmissionRange > currentVertex.distance(location.get(currentEdge.getV()))){
    					possibleVertices.add(currentEdge.getV());
    					possibleEdges.add(currentEdge.getW());
    				}
    			if(currentVertexIndex == currentEdge.getV())
    				if (transmissionRange > currentVertex.distance(location.get(currentEdge.getU()))){
    					possibleVertices.add(currentEdge.getU());
    					possibleEdges.add(currentEdge.getW());
    				}
    				
    		}
    		int worked = 1;
    		int z = 0;
    		for(i=0; i < possibleVertices.size(); i++){
    			if(distances.get(possibleVertices.get(i)) > possibleEdges.get(i)){
    				distances.set(possibleVertices.get(i), distances.get(currentVertexIndex) + possibleEdges.get(i));
    				tempPath.add(location.get(possibleVertices.get(i)));
    				worked = 0; 
    				ArrayList<Vertex> temp2 = new ArrayList<Vertex>();
    				for(z = 0; z < tempPath.size(); z++){
    					Vertex fix = tempPath.get(z);
    					temp2.add(fix);
    				}
        			predecessors.set(possibleVertices.get(i), temp2);
//        			if (worked == 0)
//        				tempPath.remove(tempPath.size()-1);
//        			worked = 1;
    			}
    		}
    		int check = unvisited.indexOf(currentVertexIndex);
    		if(check != -1)
    			unvisited.remove(check);
    		check = unvisited.indexOf(sinkIndex);
    		int vertexClosest = 0;
    		if(check == -1)
    			break;
    		if(possibleVertices.isEmpty())
    			break;
    		else
    			vertexClosest = possibleVertices.get(0);
    		
    		int vertexClosestRelativeIndex = 0;
    		int j = 0;
    		int y = 0;
    		for(i = 0; i < possibleVertices.size(); i++){
    			if(possibleEdges.get(vertexClosestRelativeIndex) >= possibleEdges.get(i)){
    				for(j = 0; j < unvisited.size(); j++){
        			  	if(vertexClosest == unvisited.get(j)){
        			  		vertexClosestRelativeIndex = i;
        					vertexClosest = possibleVertices.get(i);
        					y++;
        			  	}
        			 }
    			}
    		}
    		if (y == 0)
    			break;
    		int a = 1;
    		for(j = 0; j < unvisited.size(); j++){
    			if(vertexClosest == unvisited.get(j))
    				a = 0;
    		}
    		if(a == 1){
    			break;
    		}
    		currentVertex = location.get(vertexClosest);
    		currentVertexIndex = vertexClosest;
		}
		if(predecessors.get(sinkIndex) == start)
			predecessors.get(sinkIndex).clear();
        return predecessors.get(sinkIndex);

    	}
    
    public ArrayList<Vertex> dijkstraPathHops(int sourceIndex, int sinkIndex) {
        /* This method returns a path (shortest in terms of hops) from a source at
           location sourceIndex and a sink at location sinkIndex using Dijkstra's algorithm.
           An empty path is returned if Dijkstra's algorithm fails to find a path. */
        /* The following code is meant to be a placeholder that simply 
           returns an empty path. Replace it with your own code that 
           implements Dijkstra's algorithm. */
    	ArrayList<Double> distances = new ArrayList<Double>();
    	ArrayList<ArrayList<Vertex>> predecessors = new ArrayList<ArrayList<Vertex>>();
    	ArrayList<Integer> unvisited = new ArrayList<Integer>();
    	ArrayList<Vertex> start = new ArrayList<Vertex>();
    	start.add(location.get(sourceIndex));
    	Edge currentEdge;
    	Vertex currentVertex;
    	int x = 0;
    	int i = 0;
		for(i = 0; i < location.size(); i++){    			
			distances.add(Double.POSITIVE_INFINITY);
			unvisited.add(i);
			predecessors.add(start);
		}
		distances.set(sourceIndex, 0.0);
		currentVertex = location.get(sourceIndex);
		int currentVertexIndex = sourceIndex;	
		while(x == 0){
			ArrayList<Integer> possibleVertices = new ArrayList<Integer>();
			ArrayList<Vertex> tempPath = new ArrayList<Vertex>();
			tempPath = predecessors.get(currentVertexIndex);
    		for(i = 0; i < edges.size(); i++){    			
    			currentEdge = edges.get(i);
    			if(currentVertexIndex == currentEdge.getU())
    				if (transmissionRange > currentVertex.distance(location.get(currentEdge.getV()))){
    					possibleVertices.add(currentEdge.getV());
    				}
    			if(currentVertexIndex == currentEdge.getV())
    				if (transmissionRange > currentVertex.distance(location.get(currentEdge.getU()))){
    					possibleVertices.add(currentEdge.getU());
    				}
    				
    		}
    		int worked = 1;
    		int z = 0;
    		for(i=0; i < possibleVertices.size(); i++){
    			if(distances.get(possibleVertices.get(i)) > 1){
    				distances.set(possibleVertices.get(i), distances.get(currentVertexIndex) + 1);
    				tempPath.add(location.get(possibleVertices.get(i)));
    				worked = 0; 
    				ArrayList<Vertex> temp2 = new ArrayList<Vertex>();
    				for(z = 0; z < tempPath.size(); z++){
    					Vertex fix = tempPath.get(z);
    					temp2.add(fix);
    				}
        			predecessors.set(possibleVertices.get(i), temp2);
//        			if (worked == 0)
//        				tempPath.remove(tempPath.size()-1);
//        			worked = 1;
    			}
    		}
    		int check = unvisited.indexOf(currentVertexIndex);
    		if(check != -1)
    			unvisited.remove(check);
    		check = unvisited.indexOf(sinkIndex);
    		int vertexClosest = 0;
    		if(check == -1)
    			break;
    		if(possibleVertices.isEmpty())
    			break;
    		else
    			vertexClosest = possibleVertices.get(0);
    		int j = 0;
    		int y = 0;
    		if (y == 0)
    			break;
    		int a = 1;
    		for(j = 0; j < unvisited.size(); j++){
    			if(vertexClosest == unvisited.get(j))
    				a = 0;
    		}
    		if(a == 1){
    			break;
    		}
    		currentVertex = location.get(vertexClosest);
    		currentVertexIndex = vertexClosest;
		}
		if(predecessors.get(sinkIndex) == start)
			predecessors.get(sinkIndex).clear();
        return predecessors.get(sinkIndex);

    }
    
}

