import java.awt.Color;
import java.awt.Graphics;

public class foreground implements shapeDecorator{
	private Color c;
	private shape s;
	
	
	public foreground(Color thatC, shape thatS){
		c = thatC;
		s = thatS;
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(c);
		s.draw(g);
		g.setColor(Color.BLACK);
	}
	
}
