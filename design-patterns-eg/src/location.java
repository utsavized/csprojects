import java.awt.Graphics;

public class location implements shapeDecorator{
	private int x;
	private int y;
	private shape s;
	public location(int thatX, int thatY, shape thatS){
		x = thatX;
		y = thatY;
		s = thatS;
	}
	@Override
	public void draw(Graphics g) {
		boundingBox boundingBox = new boundingBox();
		//boundingBox.setXCord(x);
		//boundingBox.setYCord(y);
		g.translate(x, y);
		s.draw(g);
		//boundingBox.xCord = boundingBox.xCord + x;
		//boundingBox.yCord = boundingBox.yCord + y;
	}
}