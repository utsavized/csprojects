package recursion.backtracking;

import java.awt.Color;

import reusableobjects.MyPoint;


public class FillPaint {
	final int LEFT = -1;
	final int RIGHT = 1;
	final int UP = 2;
	final int DOWN = -2;
	final int ALL = 3;
	
	public MyPoint [][]  paintFill(MyPoint point, MyPoint [][] screen, Color newC) throws Exception {
		int M = screen[0].length;
		int N = screen.length;
		int m = point.y;
		int n = point.x;
		return paintFill(screen, m, n, M, N, point.color, newC);
	}
	
	private MyPoint [][] paintFill(MyPoint [][] screen, int m, int n, int M, int N, Color current, Color newC) throws Exception {
		if(outOfBounds(screen, m, n, M, N, current, ALL))
			return screen;
		
		if(!outOfBounds(screen, m, n, M, N, current, UP)) {
			m--;
			screen[m][n] = fill(screen[m][n], newC);
			screen = paintFill(screen, m, n, M, N, current, newC);
		}
		
		if(!outOfBounds(screen, m, n, M, N, current, DOWN)) {
			m++;
			screen[m][n] = fill(screen[m][n], newC);
			screen = paintFill(screen, m, n, M, N, current, newC);
		}
		
		if(!outOfBounds(screen, m, n, M, N, current, LEFT)) {
			n--;
			screen[m][n] = fill(screen[m][n], newC);
			screen = paintFill(screen, m, n, M, N, current, newC);
		}
		
		if(!outOfBounds(screen, m, n, M, N, current, RIGHT)) {
			n++;
			screen[m][n] = fill(screen[m][n], newC);
			screen = paintFill(screen, m, n, M, N, current, newC);
		}
		return screen;
	}
	
	private MyPoint fill(MyPoint point, Color c) {
		point.color = c;
		return point;
	}

	private boolean outOfBounds(MyPoint [][] screen, int m, int n, int M, int N, Color current, int direction) throws Exception {
		switch(direction) {
			case LEFT:
				if(n > 0 && screen[m][n-1].color == current)
					return false;
				else
					return true;
			case RIGHT:
				if(n < N - 1 && screen[m][n+1].color == current)
					return false;
				else
					return true;
			case UP:
				if(m > 0 && screen[m-1][n].color == current)
					return false;
				else
					return true;
			case DOWN:
				if(m < M - 1 && screen[m+1][n].color == current)
					return false;
				else
					return true;
			case ALL:
				if(outOfBounds(screen, m, n, M, N, current, LEFT) && outOfBounds(screen, m, n, M, N, current, RIGHT) && outOfBounds(screen, m, n, M, N, current, UP) && outOfBounds(screen, m, n, M, N, current, DOWN))
					return true;
				else
					return false;
			default:
				throw new Exception("Invalid direction.");
		}
	}
}
