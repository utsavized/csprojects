package stringandarrays;
import java.util.ArrayList;


public class MinimumPathInTriangle {
	
	/*Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

	For example, given the following triangle

	[
	     [2],
	    [3,4],
	   [6,5,7],
	  [4,1,8,3]
	]
	The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

	Note:
	Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.*/
	
	
	public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        if(triangle.size() < 1)
            return 0;
        
        if(triangle.size() == 1)
            return triangle.get(0).get(0);
            
        return min(triangle, triangle.get(0).get(0), Integer.MAX_VALUE, 0, 1);
        
    }
    
    private int min(ArrayList<ArrayList<Integer>> triangle, int curMin, int min, int col, int row) {
        
        if(row == triangle.size())
            return Math.min(curMin, min);
        
        ArrayList<Integer> thisRow = triangle.get(row);
        for(int j = col; j <= col + 1; j++) {
            min = min(triangle, curMin + thisRow.get(j), min, j, row + 1);  
        }
        
        return min;
    }
}
