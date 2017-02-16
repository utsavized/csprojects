package simulator;
/**
 * NodeMapper
 * Maps nodes with respective file chunks
 * @author Utsav
 * @date 01/09/2011
 */

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
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
		FileOutputStream fstream = new FileOutputStream(getFilename());
		PrintStream out = new PrintStream(fstream);
		String name = "unisg_";	//Chunk filename prefix
		String ext = ".txt";	//Chick file extension
		System.out.print("Adding maps...");
		
		
		//For all 197 chunks
		for(int i = 1; i < 198; i++){
			//Read each node in chunk
			BufferedReader readLine = new BufferedReader(new FileReader(path+name+i+ext)); 
			try{
				//Read each delimited word
				String line = readLine.readLine();
				while(line!=null){
					Scanner readID = new Scanner(line);
					readID.useDelimiter(" ");
					if(readID.hasNext()){
						//Read first word (i.e. Node ID) and add to output file
						//with chunk filename
						out.print(readID.next() + ", " + name + i + ext + "\r\n");
						//Once first word (i.e. Node ID is read, skip to next line
						line = readLine.readLine();
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
