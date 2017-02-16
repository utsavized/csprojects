package recursion.backtracking;

public class ReplaceSimilarConnectedNumbers {
	public void replace(int [][] matrix, int toReplace, int replaceWith, int cellX, int cellY) {
		int maxRows = matrix.length;
		int maxCols = matrix[0].length;
		
		replace(matrix, cellX, cellY, maxRows, maxCols, toReplace, replaceWith);
		
		
	}
	
	private void replace(int [][] matrix, int row, int column, int maxRows, int maxCols, int toReplace, int replaceWith) {
		if(row == maxRows || row < 0 || column == maxCols || column < 0)
			return;
		
		if(matrix[row][column] != toReplace)
			return;
		
		matrix[row][column] = replaceWith;
		
		//Left
		replace(matrix, row, column - 1, maxRows, maxCols, toReplace, replaceWith);
		//Top
		replace(matrix, row - 1, column, maxRows, maxCols, toReplace, replaceWith);
		//Right
		replace(matrix, row, column + 1, maxRows, maxCols, toReplace, replaceWith);
		//Down
		replace(matrix, row + 1, column, maxRows, maxCols, toReplace, replaceWith);

	}

}
