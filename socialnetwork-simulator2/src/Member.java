import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The member object
 * @author Utsav Pandey
 */
public class Member{
	private int ID;
	private double price;
	private double discount;
	private List<Object> inNetworkOf;
	
	/*
	 * Overloaded constructors
	 */
	
	public Member(int ID){
		this.ID = ID;
		price = (double) 500.0;
		discount = (double) 1.0;
		inNetworkOf = new ArrayList<Object>();
	}
	
	public Member(int ID, double price, double discount){
		this.ID = ID;
		this.price = price;
		this.discount = discount;
	}
	
	/*
	 * Accessors are mutators
	 */
	
	/**
	 * @return Member ID
	 */
	public int getID(){
		return ID;
	}
	
	/**
	 * @return Price
	 */
	public double getGrice(){
		return price;
	}
	
	/**
	 * @return Discount
	 */
	public double getGiscount(){
		return discount;
	}
	
	/**
	 * @return Networks where members belongs to
	 */
	public List<Object> getNetworkOf(){
		return Collections.unmodifiableList(inNetworkOf);
	}
	
	/*
	 * Worker modules
	 */
	
	/**
	 * Add networks member belings to
	 * @param Member ID
	 */
	public void add(Object memberID) {
		inNetworkOf.add(memberID);
	}
}
