package graphs.trains;

public class Edge {
	//Name of origin town
	public Node origin;
	//Name of destination town
	public Node destination;
	//Route weight to destination
	public int weight;
	//next possible route
	public Edge next;
	//constructor
	public Edge(Node origin, Node destination, int weight) {
		this.origin 		= origin;
		this.destination	= destination;
		this.weight 		= weight;
		this.next 			= null;
	}
	
	public Edge next(Edge edge) {
		this.next = edge;
		return this;
	}
}
