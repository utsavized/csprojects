import java.io.IOException;
import java.util.*;

/**
 * The class that executes either Decision Trees or Naive Bayes
 * @author Utsav Pandey
 * Date Created: Nov 28, 2007
 * Date Last Edited: Dec 3, 2007
 */

public class HW3 
{
	//To store the decision value given by the leaf node of a decision tree
	//Used when comparing with Testing and Training
	public String leafValue;
	
    /**
     * Main function -- Reads in arguments (as external files) and executes DT or NB
     * @param args
     * @throws IOException
     */
	
	public static void main(String[] args) throws IOException
    {
		
		HW3 demo = new HW3();
        
    	if(args.length != 4)
        {
            System.out.println("You must supply the attribute, train, and test file names."); 
            System.exit(0);
        }

        //If DT then execute Decision Tree Process
    	if(args[3].equals("DT"))
        {
            ExampleSet train = new ExampleSet(args[0], args[1]);
            ExampleSet test = new ExampleSet(args[0], args[2]); 
            
            LinkedList<LinkedList<String>> attributes = train.getAllAttributes();
            LinkedList<LinkedList<String>> examples = train.getExamples();
            
            //Begin Creating Nodes (Default Value for classification -- Yes)
            InteriorNode Solution = demo.DecisionTree(train, "Yes");
            System.out.println("Decision Tree:");
            //Print Decision Tree
            demo.printDecisionTree(Solution,0);
            
            System.out.println("\n\n");
            
            //Calculate start time in milliseconds
            long startTime = System.currentTimeMillis();
            //Compare with testing data
            demo.CompareTest(test, "Testing", Solution);
            //Compare with training data
            demo.CompareTest(train, "Training", Solution);
            //Calculate end time in milliseconds
            long endTime = System.currentTimeMillis();
            
            System.out.println("Total Time Taken: " + (endTime-startTime) + " ms");
            
        }
        //If NB, execute Naive Bayes Process
        else if(args[3].equals("NB"))
        {
            ExampleSet train = new ExampleSet(args[0], args[1]); 
            ExampleSet test = new ExampleSet(args[0], args[2]);
            
            //Call Naive Bayes Module
            demo.NaiveBayes(train, test);
        }
        else
            System.out.println ("Invalid entry. Argument 4 is either NB or DT.");
    }
    
	
	
	/**
     * Creates the Decision Tree
     * @return Interior Node
     * @param DS, defaultchoice is Yes
     */
    
    public InteriorNode DecisionTree(ExampleSet DS, String defaultChoice) 
    {
    	//Check if Examples is empty
    	//If true return default classification as a leaf node
    	if(isExampleEmpty(DS))
    	{
            InteriorNode Node = new InteriorNode(DS, null, "", defaultChoice, "Leaf");
            return Node;
        }
        
    	//Check if all goal decision values for examples are same
    	//Return the consistent classification as a leaf node
    	String isConsistent = DS.isExampleSame();
    	if(isConsistent != null)
    	{
    		InteriorNode Node = new InteriorNode(DS, null, "", isConsistent,"Leaf");
            return Node;
        }
        
    	//Check if attributes is empty
    	//Return the majority classification of goal decision attribute as a leaf node
    	if(isAttrEmpty(DS))
    	{
        	InteriorNode Node = new InteriorNode (DS, null, "", DS.majVal(),"Leaf");
            return Node;
        }
    	
    	//Get best attribute by calculating remainder       
        String bestDecisionAttr = DS.getDecisionAttr();
        //Get possible values (for example Yes, No) for the best decision attribute
        LinkedList<String> Attr = DS.getPossibleValues(bestDecisionAttr);
        
        //Create a new node, which will be an interior node
        InteriorNode tree = new InteriorNode(DS, Attr, bestDecisionAttr, "", "Interior"); 
        
        //Find majority classification
        String maj = DS.majVal();
        
        //Get the number of possible values (example Yes/No) for the best decision attribute
        int sizeofAttr = DS.getPossibleValues(bestDecisionAttr).size();
        
        for(int i =0; i< sizeofAttr; i++)
        {
        	//Get each value for the given best decision attribute
        	String attrVal = DS.getPossibleValues(bestDecisionAttr).get(i);
        	
        	//Create a child (which is an interior node)
        	InteriorNode child = new InteriorNode(DS, Attr, bestDecisionAttr, "", "Interior"); 
        	
        	//Filter the exampleSet with the best decision attribute and each possible value
        	ExampleSet filtered = DS.filter(bestDecisionAttr, attrVal);
        	
        	//Recurse to create a new tree
        	child = DecisionTree(filtered, maj);
        	
        	//Add the tree that will be recursively created to the RootNode
        	tree.addChildren(child);
        }
        return tree;
    }
        
    /**
     * Calculates probability by Naive Bayes 
     * @param train
     * @param test
     */
    
    public void NaiveBayes(ExampleSet train, ExampleSet test)
    {
    	 //Variables for the calculation of accuracy of classification
    	 double classification = 0;
    	 int p=0;
    	 int n=0;
    	     	     	 
    	 LinkedList<LinkedList<String>> examples;
    	 
         examples = test.getExamples();
         
         //Similar to Positive and Negative; Like / Dislike
         String Class1, Class2, decisionAttr;
         
         decisionAttr = train.getAttributes().get(0);
          
         Class1 = train.getPossibleValues(train.getAttributes().get(0)).get(0);
         Class2 = train.getPossibleValues(train.getAttributes().get(0)).get(1);
         
         int numExamples = examples.size(); 
              
         long startTime = System.currentTimeMillis();
         
         //loop through examples         
         for(int i = 0; i<test.getExamples().size(); i++)
         {
             ExampleSet filtered = train.filter(decisionAttr, Class1);
             
             int num1, num2; 
             
             num1 = filtered.getExamples().size();
             num2 = numExamples - num1;
             
             //Calculate probability for Class1
             double probClass1 = (double)num1/numExamples; 
             
             for(int j=1; j<train.getAttributes().size(); j++)
             {
                 int numAttr;
                 numAttr = filtered.filter(train.getAttributes().get(j),examples.get(i).get(j)).getExamples().size(); 
                 
                 if(numAttr == 0)
                     probClass1 = probClass1 * 0.0001;
                 else
                     probClass1 = probClass1 * (double)numAttr/num1;
                  
             }

             //Calculate probability for Class2
             double probClass2 = (double)num2/numExamples;
             
             filtered = train.filter(decisionAttr, Class2);
              
             for(int j=1; j<train.getAttributes().size(); j++)
             {
                 int numAttr;
                 numAttr = filtered.filter(train.getAttributes().get(j),examples.get(i).get(j)).getExamples().size(); 
                 
                 if(numAttr == 0)
                     probClass2 = probClass2 * 0.0001;
                 else
                     probClass2 = probClass2 * (double)numAttr/num2;
             }
             
             String Class;
                     
             if(probClass1>probClass2)
                 Class = Class1;
             else
                 Class = Class2;

                       
             //Check with testing data
             if(Class.equals(examples.get(i).get(0)))
            	 p++;
             else
            	 n++;
             
                     
         }
         //Calculate end time in milliseconds
         long endTime = System.currentTimeMillis();
              
         //Print the accuracy of classification
         classification = (p/(double)(p+n))*100;
         
         System.out.println("Naive Bayes:");
         System.out.println("");
         System.out.println("Testing data classified " + classification + " correctly.");
         System.out.println("Total Time Taken: " + (endTime-startTime) + " ms");
    }

    /**
     * Compares the training / testing data with the decision tree learned from the training data
     * @param test
     * @param Data
     * @param Solution
     */
    
    public void CompareTest(ExampleSet test, String Data, InteriorNode Solution)
    {	
    	//To keep track of each example
    	int count=0;
    	//Goal decision possibility 1
    	int p = 0;
    	//Goal decision possibility 2
    	int n = 0;
    	//Classification
    	double classification;
    	
    	for(int i=0;i<test.getExamples().size();i++)
    	{
    		//To do the actual comparison
    		String treeSolution = Test(test,Solution,count);
    		//each value of leaf node (or the decision value for each example from testing or training data)
    		treeSolution = leafValue;
    		String testSolution = test.getExamples().get(i).getFirst();
    		count++;
    	  		
    		if(treeSolution.equals(testSolution))
    		{
    			p++;
    			
    		}
    		else
    		{
    			n++;
    			
    		}
    	}
    	
    	classification = p/(double)(p+n)*100;
      	System.out.println(Data + " data classified " + classification + " correctly.");
    }

    /**
     * Does all the work for the comparison between the decision tree and the training or testing data
     * @param test
     * @param Solution
     * @param count
     * @return
     */
    
    public String Test(ExampleSet test, InteriorNode Solution, int count)
    {
    	String Result;
    	//Get Tree Node (or Parent)
    	LinkedList<String> Compare = Solution.getVal();
    	//If the node is a Leaf
    	if(Compare.get(2).equals("Leaf"))
    	{
    		//Store the decision
    		leafValue = Compare.get(1);
    	}
    	//If the node is an interior node
    	if(Compare.get(2).equals("Interior"))
    	{
    		for(int i=0;i<test.getAttributes().size();i++)
    		{
    			//Compare with attribute of testing / training data
    			if(Compare.get(0).equals(test.getAttributes().get(i)))
    			{
    				//For each child for the given attribute
    				for(int j=0; j<Solution.children.size();j++)
    				{
    					String Value = Solution.Attr.get(j);
    					//Check with value for child (For example Yes or No for WAIT) is present
    					//in testing / training data
    					if(Value.equals(test.getExamples().get(count).get(i)))
    					{	
    						//If found recurse with that value of child until leaf node is found
    						Result = Test(test,Solution.children.get(j),count);
    						
    					}
    				}
    			}
    	    	
    		}
    	}
    	
    	//Return default   
    	return Compare.get(1);
    }
    		
    /**
     * Prints the decision tree
     * @param solution
     * @param offset
     */ 
    
    public void printDecisionTree(InteriorNode solution, int offset)
    {
    	solution.print(offset);
    	offset++;

    	for(int i = 0; i< solution.children.size(); i++)
    	{
    		String value = solution.Attr.get(i);
    		
    		System.out.println("");
    		for(int j=0; j<offset; j++)
    		{
    			System.out.print("    ");
    		}
    		System.out.print(value);
    		printDecisionTree(solution.children.get(i),offset);
    	}
    }

    
    /**
     * Checks if Examples is empty 
     * @param DS
     * @return True/False
     */  
    
    public boolean isExampleEmpty(ExampleSet DS)
    {
        boolean value;
        int size = DS.getExamples().size();
        if(size==0)
            value = true;
        else
            value = false;
        
        return value;
    }
    
    /**
     * Checks if Attribues is empty
     * @param DS
     * @return True/False
     */
    
    public boolean isAttrEmpty(ExampleSet DS) 
    {
        boolean value;
        int size = DS.getAllAttributes().size();
        if(size==1)
            value = true;
        else
            value = false;
        
        return value; 
    }
    
    /**
     * Checks if all examples for decision attributes are same
     * or already classified
     * @param DS
     * @param bestDecisionAttr
     * @return True/False
     */
    
    public boolean isExampleSame(ExampleSet DS, String bestDecisionAttr)
    {
        boolean value;
        int count = 0;
        for(int i=0; i<DS.getAttributes().size();i++)
        {
            if( DS.getAllAttributes().get(i).getFirst()==bestDecisionAttr)
            {
                LinkedList<String> possibleAttr = DS.getPossibleValues(bestDecisionAttr);
                String check = possibleAttr.get (0);
                for(int j=0;j<DS.getExamples().get(j).size();j++)
                {
                    if(DS.getExamples().get(j).get(i)==check)
                        count++;
                }
            }
        }
        if(count>0)
            value = false;
        else
            value = true;
        
        return value;
    }
    
    /**
     * Prints the default message 
     */
 

    //END of Class HW3
    
    
}
     
 