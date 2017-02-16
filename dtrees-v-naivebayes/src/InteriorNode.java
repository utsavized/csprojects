/**
 * Class that creates the Interior / Leaf Nodes
 * @author Utsav Pandey
 * Date Created: Nov 28, 2007
 * Date Last Edited: Dec 3, 2007
 */

import java.util.*;



public class InteriorNode extends TreeNode {
    private ExampleSet DS;
    private String bestDecisionAttr;
    private String decision;
    private String nodeVal;
    public LinkedList<String> Attr;
    
    public LinkedList<InteriorNode> children; 
    //private LinkedList<LinkedList<String>>childrenValues = null; 
    
    /*
     * Interior node constructor
     */
    
    public InteriorNode(ExampleSet DS, LinkedList<String> Attr, String bestDecisionAttr, String decision, String nodeVal)
    {
        //Interior Nodes of Children
    	this.children = new LinkedList<InteriorNode>();
    	//Best Decision Attribute returned after calculating Remainder
    	this.bestDecisionAttr = bestDecisionAttr;
    	//Decision Value when Node is a Leaf
    	this.decision = decision;
    	//ExampleSet
        this.DS = DS;
        //Node Type (Either Interior or Leaf)
        this.nodeVal = nodeVal;
        //Possible Values for a given attributes (to create child nodes)
        this.Attr = Attr;
    }
    
    /**
     * Prints the the nodes as recursed by the module that prints decision tree
     * @input Integer offset (top align the tree)
     */
    
    public void print(int offset)
    {
        if(nodeVal.equals("Leaf"))
        {
        	System.out.print(" = " + decision);
        }
        else
        {
        	System.out.println("");
        	for(int i=0; i<offset; i++)
        	{
        		System.out.print("     ");
        	}
        	
        	System.out.print(bestDecisionAttr);
        }
    }
    
    /**
     * Returns attribute / final decision values from the decision tree
     * to compare with the testing / training data.
     * @return LinkedList
     */
    
    public LinkedList<String> getVal()
    {
    	LinkedList<String> Compare = new LinkedList<String>();
    	
    	Compare.add(0, bestDecisionAttr);
    	Compare.add(1, decision);
    	Compare.add(2, nodeVal);
    	
    	return Compare;
    }
    
    /**
     * Adds child to the node
     * @param child
     */    
    
    public void addChildren(InteriorNode child)
    { 
        children.add(child);
    }

//End of Class Interior Node

}

