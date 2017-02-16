import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Color;

public class draw extends Applet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void paint(Graphics g){
		shape s = new group(
				new foreground(Color.BLUE, new rectangle(20, 10)),
				new location(100, 100, new group(
				new background(Color.RED, new circle(5)),
				new circle(10),
				new circle(15)
				)) // closes Group and Location for 3 circles
		); // closes outer Group for Shape s
	//	boundingBox.xCord = 5;
	//	System.out.println(boundingBox.xCord);

		s.draw(g);
	}
}
