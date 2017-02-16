/**
 * Main
 * @author Utsav
 * @date 01/09/2011
 */

package simulator;

import java.io.IOException;

public class Run {
	public static void main(String[] args) throws IOException{
		/*String nodeFilename = "C:\\Users\\Rooney\\Desktop\\SSN\\Data\\nodemap.txt";
		String ccFilename = "C:\\Users\\Rooney\\Desktop\\SSN\\Data\\nodecc.txt";
		String simDataPath = "C:\\Users\\Rooney\\Desktop\\SSN\\Data\\Chunks\\UniSG\\";
		String simOutputPath = "C:\\Users\\Rooney\\Desktop\\SSN\\Data\\";
		String inputConfigFile = "C:\\Users\\Rooney\\Desktop\\SSN\\Data\\inputConfig.txt";*/
	
		//NodeMapper n = new NodeMapper(nodeFilename);
		//n.createMap();
		//CCGenerator g = new CCGenerator(nodeFilename, ccFilename);
		//g.generateCC();
		
		/*Simulator sim = new Simulator(simDataPath, simOutputPath, nodeFilename, ccFilename, inputConfigFile);
		sim.Simulate();
		System.out.println("Done!");*/
		
		Limit l = new Limit();
		l.calculate();
	}
}
