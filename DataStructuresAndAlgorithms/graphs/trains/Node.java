package graphs.trains;

public class Node {
	public String name;
	public boolean visited;
	
	public Node(String name) {
		this.name = name;
		this.visited = false;
	}
	
	@Override
	public boolean equals(Object b) {
		if (b == null || b.getClass() != getClass()) {
	        return false;
	    }
		Node bx = (Node)b;
		return this.name.equals(bx.name);
	}
	
	@Override
	public int hashCode() {
		if(this.name == null) return 0;
		return this.name.hashCode();
	}
}
