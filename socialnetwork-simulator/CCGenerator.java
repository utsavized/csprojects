/**
 * Clustering Coefficient Generator
 * Generates CC for each nodes with friends with valid mapping
 * @author Utsav
 * @date 01/09/2011
 */

package simulator;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CCGenerator {
	private Map<String,String> nodemapper;		//Dictionary Map
	private String nodeFilename;				//Input Filename for nodemappings
	private String ccFilename;					//Output Filename to write Clustering Coefficients
	private String chunkPath;					//Path for reading chunks
	
	/**
	 * CCGenerator Constructor
	 * @param filename
	 */
	CCGenerator(String nodeFilename, String ccFilename){
		nodemapper = new HashMap<String,String>();
		this.nodeFilename = nodeFilename;
		this.ccFilename = ccFilename;
		chunkPath = "C:\\Users\\Rooney\\Desktop\\SSN\\Data\\Chunks\\UniSG\\";
	}
	
	/**
	 * Accessor for nodemapper
	 * @return Dictionary of Mapping
	 */
	private Map<String, String> getNodeMapper(){
		return nodemapper;
	}
	
	/**
	 * Accessor for filename
	 * @param arg is either node or cc; node return node filename, cc return cc filename
	 * @return respective filename with path
	 */
	public String getFilename(String arg){
		if(arg.equals("node"))
			return nodeFilename;
		else if(arg.equals("cc"))
			return ccFilename;
		return null;
	}
	
	/**
	 * Creates the mapping dictionary
	 * @param filename
	 * @throws IOException
	 */
	private void createMap(String filename) throws FileNotFoundException{
		System.out.print("Adding node mapping to memory...");
		Scanner fileScanner = new Scanner(new FileReader(filename));	//Open file
		try{
			//Read each line
			while(fileScanner.hasNext()){
				Scanner lineScanner = new Scanner(fileScanner.nextLine());
			    lineScanner.useDelimiter(", ");
			    //Read each string separated by delimiter
			    if (lineScanner.hasNext())
			    	//System.out.println(lineScanner.next().trim());
			    	//Add mapping to dictionary
			    	getNodeMapper().put(lineScanner.next().trim(), lineScanner.next().trim());
			}
		}
		finally{
			fileScanner.close();	//Close file
		}	
		System.out.println("Done.");
		System.out.println("--------------------------");
	}
	
	/**
	 * Calculates the Clustering Coefficient for each node
	 * @throws IOException 
	 * @output nodecc.txt
	 */
	public void generateCC() throws IOException{
		try{
			//Create node mapping dictionary
			createMap(getFilename("node"));
		} catch (IOException e){
			e.printStackTrace();
		}
		
		FileWriter fstream = new FileWriter(getFilename("cc"));
		BufferedWriter out = new BufferedWriter(fstream);
		//ArrayLists to store Friends of nodes and friends of frind of node
		//Used array lists cause nested loops/conditions alone got too confusing :)
		ArrayList<String> friendsOfNode, friendsOfFriendOfNode;
		
		for(Map.Entry<String, String> entry: getNodeMapper().entrySet()){
			String node = entry.getKey();			//Get node ID
			System.out.println("Node: " + node);
			int connections = 0;					//Number of connections between friends
			float node_cc = 0;						//Clustering Coefficient
			friendsOfNode = getFriends(node);		//Friends of a given node
			int found = -1;	//Whether a friend with a valid mapping was found (-1 = not found: default)
			
			/*
			 * THE UNMAPPED FRIENDS (NODES) PROBLEM IN THE DATASET
			 * 
			 * If no chunk file mapping for node is present,
			 * empty list will be returned by getFriends(), in which case CC
			 * cannot be computed. Hence for loop below will loop through
			 * the entire friends list to see if any friend has valid map.
			 * If valid map exists for any friend, "found" will be
			 * updated with that index. If no friends have valid mapping, 
			 * no CC will be computed (will be recorded as -1 in output file).
			 */
			
			//Loop through all friends of node
			for(int x = 0; x<friendsOfNode.size(); x++){
				//See if each friend has valid mapping
				friendsOfFriendOfNode = getFriends(friendsOfNode.get(x));
				//If valid map found
				if(friendsOfFriendOfNode.size()!=0){
					found = x;	//Update found with the index
					break;		//Exit loop to avoid further iteration
				}
			}
			
			if(found!=-1){ //If mapping was present and friend list was returned
				//Get the friends of that friend of original node
				friendsOfFriendOfNode = getFriends(friendsOfNode.get(found));
				//For each friend of node
				for(int i = 0; i < friendsOfNode.size(); i++){
					//For each friend of friend of node
					for(int j = 0; j < friendsOfFriendOfNode.size(); j++){
						//Check for any existing connection
						if(friendsOfNode.get(i).equals(friendsOfFriendOfNode.get(j))){
							connections++;
							break;	//If connection found, exit loop to avoid further iteration
						}
					}
				}
				//Calculate Clustering Coefficient for the given node
				node_cc = (float)2*connections/(friendsOfNode.size()*(friendsOfNode.size()-1));
				//Print some useful infotmation
				System.out.println("Friends List Size: " + friendsOfNode.size());
				System.out.println("Connections: " + connections);
				System.out.println("CC: " + node_cc);
				System.out.println("--------------------------");
				//Write CC output to file
				out.write(node + ", " + node_cc + "\r\n");
				//Clear all lists and variable for new iteration
				node_cc = 0;
				connections = 0;
				friendsOfNode.clear();
				friendsOfFriendOfNode.clear();
			}
			else{	//If mapping wasn't present and empty list was returned
				System.out.println("All " + friendsOfNode.size() + " Friends Unmapped.");
				System.out.println("--------------------------");
				//out.write(node + ", " + "-1" + "\r\n");		//Write CC as -1
			}
		}
		//Close output streams
		out.close();
	}

	/**
	 * Returns friends of a given node
	 * @param node
	 * @return ArrayList of friends
	 * @throws FileNotFoundException
	 */
	private ArrayList<String> getFriends(String node) throws FileNotFoundException{
		//To store friends of node
		ArrayList<String> friends = new ArrayList<String>();
		//Get chunk filename associated with the node
		String file = getNodeMapper().get(node);
		/*
		 * Not all nodes have mappings. Therefore,
		 * necessary to check so that we don't try to read
		 * from a null mapping and result in null pionter 
		 * exception If condition used to check for any 
		 * unmapped friends (or null returns from node mapper).  
		 */
		
		if(file!=null){	//If chunk file mapping exists
			//Begin reading chunk
			Scanner readLine = new Scanner(new FileReader(chunkPath+file));
			try{
				//Search for the node
				while(readLine.hasNext()){
					Scanner readFriends = new Scanner(readLine.nextLine());
					readFriends.useDelimiter(" ");
					if(readFriends.hasNext()){
						if(readFriends.next().equals(node)){
							readFriends.next(); //Skip num of times sampled (refer to dataset)
							//When node found, read in friends of node
							while(readFriends.hasNext())
								//Add friends to ArrayList
								friends.add(readFriends.next().trim());
						}
						else
							//If node not found, move to next line
							readFriends.nextLine();
					}
				}
			}
			finally{
				//Close read stream
				readLine.close();
			}
			return friends;	//return list of friends of node
		}
		else{	//If chunk file mapping does not exist (and null is returned by node mapper)
			return new ArrayList<String>();	//return empty list
		}
	}
}
