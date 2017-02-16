import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The network object
 * @author Utsav Pandey
 */
public class Network{
	private int ID;
	private List<Object> friends;
	
	public Network(int ID){
		this.ID = ID;
		friends = new ArrayList<Object>();
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
	 * @return Member's friends
	 */
	public List<Object> getFriends(){
		return Collections.unmodifiableList(friends);
	}
	
	/*
	 * Worker modules
	 */
	
	/**
	 * Adds friends
	 * @param Member ID
	 */
	public void add(Object memberID) {
		friends.add(memberID);
	}
}
