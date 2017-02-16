package simulator;
/**
 * NodeMapper
 * Maps nodes with respective file chunks
 * @author Utsav
 * @date 01/09/2011
 */

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class NodeMapper {
	private String filename;	//Filename to write output to
	public String path;			//Path to chunk files
	
	/**
	 * Constructor
	 * @param filename
	 */
	public NodeMapper(String filename){
		this.filename = filename;
		path = "C:\\Users\\Rooney\\Desktop\\SSN\\Data\\Chunks\\UniSG\\";
	}
	
	/**
	 * Accessor for filename
	 * @return filename
	 */
	public String getFilename(){
		return filename;
	}
	
	/**
	 * Creates Mapping
	 * Sample format: 12345, unisg_1.txt
	 * @throws IOException
	 */
	public void createMap() throws IOException{
		//Open output filestream
		FileWriter fstream = new FileWriter(getFilename());
		BufferedWriter out = new BufferedWriter(fstream);
		String name = "unisg_";	//Chunk filename prefix
		String ext = ".txt";	//Chick file extension
		System.out.print("Adding maps...");
		//For all 197 chunks
		for(int i = 1; i < 198; i++){
			//Read each node in chunk
			Scanner readLine = new Scanner(new FileReader(path+name+i+ext));
			try{
				//Read each delimited word
				while(readLine.hasNext()){
					Scanner readID = new Scanner(readLine.nextLine());
					readID.useDelimiter(" ");
					if(readID.hasNext()){
						//Read first word (i.e. Node ID) and add to output file
						//with chunk filename
						out.write(readID.next() + ", " + name + i + ext + "\r\n");
						//Once first word (i.e. Node ID is read, skip to next line
						readID.nextLine();
					}
				}
			}
			finally{
				//Close read stream
				readLine.close();
			}
		}
		//Close write stream
		out.close();
		System.out.println("Done.");
	}
}
