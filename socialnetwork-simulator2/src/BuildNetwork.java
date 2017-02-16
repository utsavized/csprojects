import java.util.ArrayList;
import java.util.List;

/**
 * Class to perform analyze functions
 * @author Utsav Pandey
 */
public class BuildNetwork{
	private List<Object> bathan;
	private int size;
	private double intersection;
	
	public BuildNetwork(int size, double intersection)
	{
		bathan = new ArrayList<Object>();
		this.size = size;
		this.intersection = intersection;
	
	}
	/**
	 * Adds members to the system based on size
	 */
	public void addMembers(){
		for(int i = 0; i < size; i++){
			bathan.add(new Member(i));
			System.out.println((i+1) + " members added.");
		}
	}
	
	/**
	 * Builds the network
	 */
	public void buildNetwork(){
		/* CONFUSING ALGO HERE */
		System.out.println("Network built with intersection of " + intersection);
	}
	
	/**
	 * Adds price
	 */
	public void addPice(){
		/* Price implementation */
		System.out.println("Price added.");
	}
	
	/**
	 * Updates discount
	 */
	public void updateDiscount(){
		/* Update discount */
		System.out.println("Disciunts updated.");
	}
}