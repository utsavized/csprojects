package simulator;
import java.util.*;
import java.io.*;

public class Simulator {
	static HashMap<Integer, String> NodeMapper;
	static HashMap<Integer, Float> NodeCC;
	static HashMap<Integer, Float> TotalPurchases;
	//static HashMap<Integer, Float> TotalDiscounts; 
	static HashMap<Integer, Float> BuyProbabilities;
	
	String dataPath; //where the chunks are
	String outputPath; //where the output files get written
	String nodeMapperFile; 
	String nodeccFile;
	String inputConfigFile; //where the input arguments are (each line except the first is an input set)
	
	int totalNodes;
	int numPeriods;
	float purchasePriceMean;
	float purchasePriceStddev;
	float discount;
	float superDiscount;
	float ccThreshold;
	float initialBuyProb;
	float buyProbThreshold;
	String outputFile;
	String[] tokens;
	
	
	
	public Simulator(String dataPath, String outputPath, String nodeMapperFile, String nodeccFile, String inputConfigFile){
		try {
			NodeMapper = new HashMap<Integer, String>(totalNodes);
			NodeCC = new HashMap<Integer, Float>(totalNodes);
			TotalPurchases = new HashMap<Integer, Float>(totalNodes);
			//TotalDiscounts = new HashMap<Integer, Float>(totalNodes); 
			BuyProbabilities = new 	HashMap<Integer, Float>(totalNodes);
			
			this.dataPath = dataPath; //where the chunks are
			this.outputPath = outputPath; //where the output files get written
			this.nodeMapperFile = nodeMapperFile; 
			this.nodeccFile = nodeccFile;
			this.inputConfigFile = inputConfigFile; //where the input arguments are (each line except the first is an input set)
			
			totalNodes = 1000000;
			
			BufferedReader inputConfigReader = new BufferedReader(new FileReader(inputConfigFile));
			String line = inputConfigReader.readLine(); // skip the first line, which is just the header
			while((line = inputConfigReader.readLine()) != null) {
				tokens = line.split(","); // each line in the input config file is comma-delimited
				// fill the inputs
				numPeriods = myParseInt(tokens[0]);
				purchasePriceMean = Float.parseFloat(tokens[1]);
				purchasePriceStddev = Float.parseFloat(tokens[2]);
				discount = Float.parseFloat(tokens[3]);
				superDiscount = Float.parseFloat(tokens[4]);
				ccThreshold = Float.parseFloat(tokens[5]);
				initialBuyProb = Float.parseFloat(tokens[6]);
				buyProbThreshold = Float.parseFloat(tokens[7]);
				outputFile = outputPath + tokens[8]; //make sure this one is unique in the input config file to avoid file overwrite
			}
		}
		catch (IOException ioe){
			//handle or swallow
			System.out.println(ioe.toString());
			
		}

	}	
	
	public void Simulate() throws IOException 
	{		
		System.out.println(dataPath);
		String[] datafiles = new File(dataPath).list();
		System.out.println(datafiles[0].toString());
		BufferedReader chunkReader; //, nodeccReader
		BufferedWriter writer;
		String line;
		String[] tokens;
		float randomPurchase;
		float totalDiscount, fractionalDiscount;
		
		/*nodeccReader = new BufferedReader(new FileReader(nodeccFile));
		while((line = nodeccReader.readLine()) != null) {
			tokens = line.split(",");
			int ID = Integer.parseInt(tokens[0].trim());
			float cc = Float.parseFloat(tokens[1].trim());
			NodeCC.put(ID, cc);
		}*/
		
		for(int i = 0; i < numPeriods; i++) {
			// Random buy stage
			if (i == 0) { //Read the nodes from the chunk files first time around
				for(String chunk : datafiles) {
					
					chunkReader = new BufferedReader(new FileReader(dataPath + chunk));
					int ID;
					while((line = chunkReader.readLine()) != null) {
						ID = myParseInt(line.substring(0, line.indexOf(" ")));
						randomPurchase = getRandomPurchase(purchasePriceMean, purchasePriceStddev, initialBuyProb);
						TotalPurchases.put(ID, randomPurchase); //put all first time around even if randomPurchase == 0
						if (randomPurchase == 0)
							BuyProbabilities.put(ID, updateBuyProbability(initialBuyProb));
					}
					chunkReader.close();
				}
				//System.out.println("Total nodes: " + TotalPurchases.size());
			} else {
				for(int ID : TotalPurchases.keySet()) {
					if (TotalPurchases.get(ID) == 0) { //purchase if no prior purchase
						randomPurchase = getRandomPurchase(purchasePriceMean, purchasePriceStddev, BuyProbabilities.get(ID));
						if (randomPurchase > 0) {
							TotalPurchases.put(ID, randomPurchase);
						} else {
							BuyProbabilities.put(ID, updateBuyProbability(BuyProbabilities.get(ID)));
						}
					}
				}				
			}

			// Discount calculation stage (do this at the last period to avoid double-counting)			
			if ( i == numPeriods - 1) {
				int ID;
				writer = new BufferedWriter(new FileWriter(outputFile));
				for(String chunk : datafiles) {	
					chunkReader = new BufferedReader(new FileReader(dataPath + chunk));	
					while((line = chunkReader.readLine()) != null) {
						tokens = line.split("\\s+");
						ID = myParseInt(tokens[0]);
						totalDiscount = 0;
						if(TotalPurchases.get(ID) > 0) {
							// Calculate discounts buy looking at how much each friends bought							
							for(int j = 2; j < tokens.length; j++) {
								int friendID = Integer.parseInt(tokens[j]);
								if(TotalPurchases.get(friendID) != null && TotalPurchases.get(friendID) > 0) {
									fractionalDiscount = TotalPurchases.get(ID)/(TotalPurchases.get(ID)+TotalPurchases.get(friendID));
									//totalDiscount += fractionalDiscount*(NodeCC.get(ID) > ccThreshold ? superDiscount : discount);
									totalDiscount += fractionalDiscount* discount;
								}
 							}
						}
						//Write ID,#friends,totalPurchases,totalDiscount 
						writer.write(ID + "," + (tokens.length - 2) + 
								     "," + TotalPurchases.get(ID) + "," + totalDiscount + "\n");
						
					}
				}
				writer.flush();
				writer.close();
			}
			
		}
		
	}

	// Tosses a biased coin and returns a random purchase if the coin comes up head -- returns 0.0 otherwise.
	private float getRandomPurchase(float purchasePriceMean,
		float purchasePriceStddev, float buyProb) {
		boolean head = new Random().nextFloat() <= buyProb; // toss a biased coin to make a buying decision
		if (head) {
			// pick a purchase price from a normal distribution with parameters (purchasePriceMean, purchasePriceStddev)
			return (float) new Random().nextGaussian() * purchasePriceStddev + purchasePriceMean;
		}
		return 0;
	}
	
	// TODO: needs a more accurate function (incorporate threshold as well)
	// Needs to be a function of how many friends bought and how much they spent
	private float updateBuyProbability(float currentBuyProb) {
		return (float)1.01*currentBuyProb;
	}

	/*private static float updateBuyProbability(float currentBuyProb, ArrayList<float> friendPurchases) {
		return (float)1.01*currentBuyProb; 	
	}*/
	
	private int myParseInt(String numStr) {
		boolean success = false;
		int num = -1;
		int i = 0;
		while (!success) {
			try {
				num = Integer.parseInt(numStr.substring(i));
				success = true;
			} catch(NumberFormatException nfe) {
				success = false;
				i++;
			}
		}
		return num;
	}
	
}
	//doesn't seem useful at all
	class SocialNode {
		int ID;
		ArrayList<SocialNode> Friends;
		float BuyProb;
		double TotalDiscount;
		double TotalPurchase;
		
		public SocialNode(int id) {
			this.ID = id;
		}
	}