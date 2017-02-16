package amz;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class DegreesOfSeparation {
	
/*
 * Given: List<String> getActors(String actorName) 
 * This function takes an actor and returns all the 
 * actors they acted with in their career. Use this
 * function to calculate the degrees of separation 
 * between one actor to another.
 */
	
	public int getDegreesOfSeparation(String actorA, String actorB) {
	
		HashSet<String> visited = new HashSet<String>();    //Track visited actors
		Queue<String> q = new LinkedList<String>();         //Queue for BFS
		int degree = 0;                                     //Keep tract of degree of separation
		q.add(actorA);                                      //Add first actor to queue
		
		//While Queue is not empty
		while(!q.isEmpty()) {
		    String curActor = q.remove();                   //Get actor from queue
		    visited.add(curActor);                          //Mark as visited
		    
		    //Find all neighboring actors
		    //Check if destination actor is found, if so, return degree
		    //Else, add all unvisited actors to queue
		    ArrayList<String> neighbors = getActors(curActor);
		    for(String a : neighbors) {
		        if(!visited.contains(a)) {
		            q.add(a);
		        }
		        if(a.equals(actorB))
		            return degree;
		    }
		    degree++;                                        //Increment degree by 1 level
		
		}
		
		//If degree as not been returned by now
		//It must mean that there is NO connection between
		//Actor A and B
	    return -1;
	}
	
	/**
	 * Dummy function
	 * @param actorName
	 * @return List of neighboring actors
	 */
	private ArrayList<String> getActors(String actorName) {
		String s = "";
		return new ArrayList<String>();
	}


}
