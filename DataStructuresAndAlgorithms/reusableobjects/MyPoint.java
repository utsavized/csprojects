package reusableobjects;
import java.awt.Color;
import java.awt.Point;

public class MyPoint extends Point{
	private static final long serialVersionUID = 1L;
	public Color color;
	public MyPoint(int x, int y, Color color) {
			super.x = x;
			super.y = y;
			this.color = color;
	}
}
