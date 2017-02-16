import java.awt.Color;
import java.awt.Graphics;

public class background implements shapeDecorator{
	private Color c;
	private shape s;
		
	public background(Color thatC, shape thatS){
		c = thatC;
		s = thatS;
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(c);
		if(s instanceof circle)
			g.fillOval((((circle) s).x/2 - ((circle) s).radius), (((circle) s).y/2 - ((circle) s).radius), ((circle) s).radius * 2, ((circle) s).radius * 2);
		else if(s instanceof rectangle)
			g.fillRect(((rectangle) s).x,((rectangle) s).y,((rectangle) s).width,((rectangle) s).height);
		g.setColor(Color.BLACK);
	}
}
