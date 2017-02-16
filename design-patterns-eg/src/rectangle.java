import java.awt.Color;
import java.awt.Graphics;

public class rectangle implements shape{
	public int width;
	public int height;
	public int x;
	public int y;
	public Color color;
	
	public rectangle(int thatWidth, int thatHeight){
		boundingBox boundingBox = new boundingBox();
		x = boundingBox.xCord;
		y = boundingBox.xCord;
		width = thatWidth;
		height = thatHeight;
	}
	
	public void draw(Graphics g){
		//System.out.println(x);
		//System.out.println(y);
		
		g.drawRect(x, y, width, height);
		boundingBox(x,y,x+width, y+height);
		boundingBox.draw(g);
	}
	
	public void boundingBox(int lowerLeftX, int lowerLeftY, int upperRightX, int upperRightY){
		if(lowerLeftX > boundingBox.lowerLeftX)
			boundingBox.lowerLeftX = lowerLeftX;
		if(lowerLeftY > boundingBox.lowerLeftY)
			boundingBox.lowerLeftY = lowerLeftY;
		if(upperRightX > boundingBox.upperRightX)
			boundingBox.upperRightX = upperRightX;
		if(upperRightY > boundingBox.upperRightY)
			boundingBox.upperRightY = upperRightY;
				
	}
}