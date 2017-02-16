package graphs.trains;
import java.util.ArrayList;
import java.util.Hashtable;

public class Routes {
	public Hashtable<Node, Edge> routeTable;
	
	public Routes() {
		this.routeTable = new Hashtable<Node, Edge>();
	}
	
	/*
	 * Calculates the distance of a specific path
	 */
	public int distanceBetween(ArrayList<Node> cities) throws Exception {
		/*There is no distance between
		 * no cities or 1 city*/
		if(cities.size() < 2)
			return 0;
		int distance, depth, i;
		distance = depth = i = 0;
		
		/* For each city in the list,
		 * we check if entry exists in our
		 * hash table.
		 */
		while(i < cities.size() - 1) {
			if(this.routeTable.containsKey(cities.get(i))) {
				Edge route = this.routeTable.get(cities.get(i));
				/*If key exists, we check if route from key to next
				 * city exists. We add the distance, and maintain a
				 * depth count
				 */
				while(route != null) {
					if(route.destination.equals(cities.get(i + 1))) {
						distance += route.weight;
						depth++;
						break;
					}
					route = route.next;
				}
			}
			else
				throw new Exception("NO SUCH ROUTE");
			i++;
		}
		/*If edge depth is not equal to vertex - 1,
		 * then it is safe to assume that one ore more
		 * routes do not exist
		 */
		if(depth != cities.size() - 1)
			throw new Exception("NO SUCH ROUTE");
		
		return distance;
	}
	
	/*
	 * Number of stops;
	 * Wrapper for recursive function
	 */
	public int numStops(Node start, Node end, int maxStops) throws Exception{
		//Wrapper to maintain depth of traversal
		return findRoutes(start, end, 0, maxStops);
	}
	
	/*
	 * Finds number of stops from start to end,
	 * with a maximum of maxStops and the depth
	 * limit.
	 */
	private int findRoutes(Node start, Node end, int depth, int maxStops) throws Exception{
		int routes = 0;
		//Check if start and end nodes exists in route table
		if(this.routeTable.containsKey(start) && this.routeTable.containsKey(end)) {
			/*
			 * If start node exists then traverse all possible
			 * routes and for each, check if it is destination
			 * If destination, and number of stops within 
			 * allowed limits, count it as possible route.
			 */
			depth++;
			if(depth > maxStops)		//Check if depth level is within limits
				return 0;
			start.visited = true;		//Mark start node as visited
			Edge edge = this.routeTable.get(start);
			while(edge != null) {
				/* If destination matches, we increment route
				 * count, then continue to next node at same depth
				 */
				if(edge.destination.equals(end)) {
					routes++;
					edge = edge.next;
					continue;
				}
				/* If destination does not match, and
				 * destination node has not yet been visited,
				 * we recursively traverse destination node
				 */
				else if(!edge.destination.visited) {
					routes += findRoutes(edge.destination, end, depth, maxStops);
					depth--;
				}
				edge = edge.next;
			}
		}
		else
			throw new Exception("NO SUCH ROUTE");
		
		/*
		 * Before exiting this recursive stack level,
		 * we mark the start node as visited.
		 */
		start.visited = false;
		return routes;
	}
	
	/*
	 * Shortest route;
	 * Wrapper for recursive function
	 */
	public int shortestRoute(Node start, Node end) throws Exception {
		//Wrapper to maintain weight
		return findShortestRoute(start, end, 0, 0);
		
	}
	
	/*
	 * Finds the shortest route between two nodes
	 */
	private int findShortestRoute(Node start, Node end, int weight, int shortestRoute) throws Exception{
		//Check if start and end nodes exists in route table
		if(this.routeTable.containsKey(start) && this.routeTable.containsKey(end)) {
			/*
			 * If start node exists then traverse all possible
			 * routes and for each, check if it is destination
			 */
			start.visited = true;		//Mark start node as visited
			Edge edge = this.routeTable.get(start);
			while(edge != null) {
				//If node not already visited, or is the destination, increment weight
				if(edge.destination == end || !edge.destination.visited)
					weight += edge.weight;
				
				/* If destination matches, we compare
				 * weight of this route to shortest route
				 * so far, and make appropriate switch
				 */
				if(edge.destination.equals(end)) {
					if(shortestRoute == 0 || weight < shortestRoute)
						shortestRoute = weight;
					start.visited = false;
					return shortestRoute; 			//Unvisit node and return shortest route
				}
				/* If destination does not match, and
				 * destination node has not yet been visited,
				 * we recursively traverse destination node
				 */
				else if(!edge.destination.visited) {
					shortestRoute = findShortestRoute(edge.destination, end, weight, shortestRoute);
					//Decrement weight as we backtrack
					weight -= edge.weight;
				}
				edge = edge.next;
			}
		}
		else
			throw new Exception("NO SUCH ROUTE");
		
		/*
		 * Before exiting this recursive stack level,
		 * we mark the start node as visited.
		 */
		start.visited = false;
		return shortestRoute;
	
	}
	
	/*
	 * Shortest route;
	 * Wrapper for recursive function
	 */
	public int numRoutesWithin(Node start, Node end, int maxDistance) throws Exception {
		//Wrapper to maintain weight
		return findnumRoutesWithin(start, end, 0, maxDistance);
	}
	
	/*
	 * Finds the shortest route between two nodes
	 */
	private int findnumRoutesWithin(Node start, Node end, int weight, int maxDistance) throws Exception{
		int routes = 0;
		//Check if start and end nodes exists in route table
		if(this.routeTable.containsKey(start) && this.routeTable.containsKey(end)) {
			/*
			 * If start node exists then traverse all possible
			 * routes and for each, check if it is destination
			 */
			Edge edge = this.routeTable.get(start);
			while(edge != null) {
				weight += edge.weight; 
				/* If distance is under max, keep traversing
				 * even if match is found until distance is > max
				 */
				if(weight <= maxDistance) {
					if(edge.destination.equals(end)) {
						routes++;
						routes += findnumRoutesWithin(edge.destination, end, weight, maxDistance);
						edge = edge.next;
						continue;
					}
					else {
						routes += findnumRoutesWithin(edge.destination, end, weight, maxDistance);
						weight -= edge.weight;	//Decrement weight as we backtrack
					}
				}
				else 
					weight -= edge.weight;
				
				edge = edge.next;
			}
		}
		else
			throw new Exception("NO SUCH ROUTE");
		
		return routes;
	
	}	
}
