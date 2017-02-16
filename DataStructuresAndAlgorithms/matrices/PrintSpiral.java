package matrices;

public class PrintSpiral {
	/**
	 * Print matrix in spiral form
	 * @param matrix
	 */
	public void printSpiral(int[][] matrix)
	{
		printSpiral(matrix, 0);
	}

	private void printSpiral(int[][] matrix, int depth)
	{
		/*
		 * This solution works as follows
		 * 1. print from top left to right top
		 * 2. print from right top to right bottom
		 * 3. print from right bottom to left bottom
		 * 4. print from left bottom to left top
		 * then call the recursive function for inner layer
		 * continue till layer=min(rows,cols)/2
		 */
		if (matrix == null || matrix.length == 0)
			return;
		
		int rows = matrix.length;
		int cols = matrix[0].length;
		
		if (2 * depth > Math.min(rows, cols))
			return;
		
		for (int i = depth; i < cols - depth - 1; ++i)
			System.out.print(matrix[depth][i] + ",");
		
		for (int i = depth; i < rows - depth - 1; ++i)
			System.out.print(matrix[i][cols - depth - 1] + ",");
		
		for (int i = rows - depth; i > depth; --i)
			System.out.print(matrix[rows - depth - 1][i] + ",");
		
		for (int i = rows - depth - 1; i > depth; --i)
			System.out.print(matrix[i][depth] + ",");

		printSpiral(matrix, ++depth);
	}
	
	
}
