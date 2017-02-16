import java.util.*;
import java.io.*;

/**
 * Class that represents a data set with attributes and examples
 * @author Michael Thompson
 */
class ExampleSet
{
    /** 2D LinkedLists of attributes and examples represented as strings */
    private LinkedList<LinkedList<String>> attributes, examples;

    /**
     * Constructor from LinkedLists
     * @param attributes attribute LinkedList
     * @param examples example LinkedList
     */
    public ExampleSet(LinkedList<LinkedList<String>> attributes, LinkedList<LinkedList<String>> examples)
    {
	this.attributes = attributes;
	this.examples = examples;
    }

    /**
     * Constructor from file - assumes both files exist and correspond in a valid manner
     * Also assumes no odd characters like '_' or any numbers that would put a value in .nval
     * @param attrFile path of the attribute file
     * @param exFile path of the examples file
     */
    public ExampleSet(String attrFile, String exFile) throws IOException
    {
	StreamTokenizer st;

	//start by reading in attribute file
	attributes = new LinkedList<LinkedList<String>>();
	st = new StreamTokenizer(new FileReader(attrFile));
	st.eolIsSignificant(true);
	st.slashSlashComments(true);
	st.slashStarComments(true);
	
	st.nextToken();

	while(st.ttype != StreamTokenizer.TT_EOF)
	{
	    //new line, add attribute
	    attributes.add(new LinkedList<String>());
	    while(st.ttype != StreamTokenizer.TT_EOL)
	    {
		attributes.getLast().add(st.sval);
		
		st.nextToken();
	    }

	    st.nextToken();
	}

	//read in examples
	examples = new LinkedList<LinkedList<String>>();
	st = new StreamTokenizer(new FileReader(exFile));
	st.eolIsSignificant(true);
	st.slashSlashComments(true);
	st.slashStarComments(true);
	
	st.nextToken();

	while(st.ttype != StreamTokenizer.TT_EOF)
	{
	    //new line, add example
	    examples.add(new LinkedList<String>());
	    while(st.ttype != StreamTokenizer.TT_EOL)
	    {
		examples.getLast().add(st.sval);
		st.nextToken();
	    }

	    st.nextToken();
	}
    }

    /**
     * Generates string with attributes and examples
     * @return string suitable for printing
     */
    public String toString()
    {
	String s = "Attributes:\n";

	for(int i = 0; i < attributes.size(); i++)
	{
	    for(int j = 0; j < (attributes.get(i)).size(); j++)
	    {
		s += (attributes.get(i)).get(j) + "\t";
	    }
	    
	    s += "\n";
	}

	s += "\nExamples:\n";

	for(int i = 0; i < examples.size(); i++)
	{
	    for(int j = 0; j < (examples.get(i)).size(); j++)
	    {
		s += (examples.get(i)).get(j) + "\t";
	    }
	    
	    s += "\n";
	}

	return s;
    }

    /**
     * Returns a list of the attributes of the data set
     * @return a LinkedList with the names of the attributes
     */
    public LinkedList<String> getAttributes()
    {
	LinkedList<String> l = new LinkedList<String>();

	for(int i = 0; i < attributes.size(); i++)
	{
	    l.add((attributes.get(i)).getFirst());

	}

	return l;
    }

    
    /**
     * Returns a list of possible values for a given attribute
     * @param attribute the name of the attribute to find possible values for
     * @return a LinkedList with the possible values
     */
    public LinkedList<String> getPossibleValues(String attribute)
    {
    
    	if(attributes.size()<=1)
    		System.out.println("Attribute SIze Error!");
    	
    	LinkedList<String> l = new LinkedList<String>();
	int found = -1;
    
	//search for attribute
	for(int i = 0; i < attributes.size(); i++)
	{
	    if(attribute.equals((attributes.get(i)).getFirst()))
	    {
		found = i;
	    }
	}

	if(found != -1)
	{
	    //get values for attribute - element 0 is attribute name, so skip
	    for(int i = 1; i < (attributes.get(found)).size(); i++)
	    {
		l.add((attributes.get(found)).get(i));
	    }
	}

	return l;
    }

   /************************************************************************************
    * Any part of this file below this point:
    * 
    * @author Utsav Pandey
    * Date Created: Nov 28, 2007
    * Date Last Edited: Dec 3, 2007
    * Homework 3
    ************************************************************************************/
    
      
    /**
     * Gets the decision attribute with the best
     * remainder from the data set
     * @param None
     * @return String
     */
    
    public String getDecisionAttr()
    {
    	//To store the attribute with the best remainder
    	String bestRemainder = getAttributes().get(1);
    	//Attribute name
    	String getAttrName = null;
    	//To compare different remainders
    	Double previousRemainder = 1.00;
    	//To compare different remainders
    	Double currentRemainder = 0.00;
    	//Create a linked list of attribute names
    	LinkedList<String> attrNames = getAttributes();
    	
    	for(int i=1;i<attrNames.size();i++)
    	{
    		//Extract each attribute name
    		getAttrName = attrNames.get(i);
    		//Calculate remainder
    		currentRemainder = Remainder(getAttrName);

    		if(currentRemainder < previousRemainder)
    		{
    			//Store new remainder
    			previousRemainder = currentRemainder;
    			//Store the attribute for the best remainder
    			bestRemainder = attrNames.get(i);
    		}
    	}
    	//Return the attribute name with the best remainder
    	//System.out.println(bestRemainder);
    	return bestRemainder;
    }
    
    /**
     * Calculates remainder and returns it to getDecisionAttr
     * @param String
     * @return Double
     */
    
    public Double Remainder(String Name)
    {
    	String subAttribute = null;
    	Double Remainder = 0.00;
    	Double subRemainder = 0.00;
    	for(int i=1;i<attributes.size();i++)
    	{
    		if(((attributes.get(i)).get(0)).equals(Name))
    		{
    			for(int j=1;j<attributes.get(i).size();j++)
    			{
    				subAttribute = (attributes.get(i)).get(j);
    				subRemainder = calcRemainder(subAttribute,i);
    				Remainder = Remainder + subRemainder;
    			}
    		}
    	}
    	return Remainder;
    }
    
    /**
     * Calculates remainder for each value of attribute set and returns
     * it to remainder()
     * @param subAttribute
     * @param index
     * @return Double
     */
    
    public Double calcRemainder(String subAttribute,int index)
    {
    	String positive = (examples.get(0)).get(0);
    	Double subRemainder = null;
    	double n = 0;
    	double p = 0;
    	double ni = 0;
    	double pi = 0;
    	double pIC;
        double nIC;
        double IC;
    	
		for(int i=0;i<examples.size();i++)
    	{
    		if(((examples.get(i)).get(index)).equals(subAttribute))
    		{
    			if((examples.get(i).get(0)).equals(positive))
    			{
    				pi++;
    			}
    			else
    			{
    				ni++;
    			}
    		}
    		if((examples.get(i).get(0)).equals(positive))
    			p++;
    		else
    			n++;
       	}
		
		pIC = pi/(ni+pi);
		nIC = ni/(ni+pi);
		
		IC = (-(nIC/(nIC+pIC))*(Math.log(nIC/(nIC+pIC))/Math.log(2))) - ((pIC/(nIC+pIC))*Math.log(pIC/(nIC+pIC))/Math.log(2));
			
    	if(p>0&&pi>0&&n>0&&ni>0)
    		subRemainder = ((pi+ni)/(p+n)) * IC;
		else
			subRemainder = 0.00001;
    	return subRemainder;
    }
    
    /**
     * Returns the entire examples
     * @return 2D Linked List
     */
    
    public LinkedList<LinkedList<String>> getExamples()
    {
    	return examples;
    }
    
    /**
     * Returns the entire attributes
     * @return 2D Linked List
     */
       
    public LinkedList<LinkedList<String>> getAllAttributes()
    {
    	return attributes;
    }
    
    /**
     * Filters the attribute and example set
     * Crops the attribute name / possible values from the
     * attributes list that equal decisionAttr
     * Crops all other example values from examples that are
     * not equal to attrVal (corresponding to attribute name decisionAttr)
     * @param decisionAttr
     * @param attrVal
     * @return ExampleSet
     */
    
    public ExampleSet filter(String decisionAttr, String attrVal)
    {
    	LinkedList<LinkedList<String>> newAttributes = new LinkedList<LinkedList<String>>();
    	LinkedList<LinkedList<String>> newExamples = new LinkedList<LinkedList<String>>();
    	
      	ExampleSet newExample = new ExampleSet(newAttributes, newExamples);
    	
    	for(int i=0; i<attributes.size(); i++)
    	{
    		if(attributes.get(i).getFirst().equals(decisionAttr))
    		{	
    			int j = 0;
    			while(j<examples.size())
    			{
    				
    				if(examples.get(j).get(i).equals(attrVal))
    				{
    					LinkedList<String> temp = new LinkedList<String>();
    					
    					for(int k=0; k<examples.get(j).size(); k++)
    					{
    						if(!(examples.get(j).get(k).equals(attrVal)&&k==i))
    						{
    							temp.add(examples.get(j).get(k));
    						}
    						
    							
    					}
    					newExamples.add(temp);
    				}
    				j++;
    			}
    					
    		}
    		else
				newAttributes.add(attributes.get(i));
    	}
       	return newExample;
    }
    
    /**
     * Checks if all examples for decision attributes are same
     * or already classified
     * @param DS
     * @param bestDecisionAttr
     * @return True/False
     */
    
    public String isExampleSame()
    {
    	boolean value;
    	//int count = 0;
    	int p = 0;
    	int n = 0;
    	String check = getExamples().get(0).getFirst();
    	for(int i=0; i<getExamples().size();i++)
    	{
    		if(getExamples().get(i).getFirst().equals(check))
    		{
    			p++;
    		}
    		else
    		{
    			n++;
    		}
    	}
    	if(p==getExamples().size()||n==getExamples().size())
			return check;
		else
			return null;
    }
    
    /**
     * Returns the goal decision attribute value with the majority occurrence
     * @return String
     */
    
    public String majVal()
    {
    	int p = 0;
    	int n = 0;
    	String Class1 = getExamples().get(0).getFirst();
    	String Class2 = null;
    	String returnVal = null;
    	for(int i=0; i<getExamples().size();i++)
    	{
    		if(getExamples().get(i).getFirst()==Class1)
    		{
    			p++;
    		}
    		else
    		{
    			n++;
    			Class2 = getExamples().get(i).getFirst();
    		}
    	}
    	if(p>n)
			returnVal = Class1;
		else if(p<n)
			returnVal = Class2;
		else if(p==n)
			returnVal = Class1; //incase of 50-50
    	
    	//System.out.println(attributes);
    	
    	return returnVal;
    	
    }


// End of Class ExampleSet
}






