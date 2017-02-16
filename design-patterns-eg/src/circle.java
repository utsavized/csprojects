import java.awt.Graphics;

public class circle implements shape{
	public int radius;
	public int x;
	public int y;
	
	public circle(int thatRadius){
		boundingBox boundingBox = new boundingBox();
		x =	boundingBox.xCord;
		y = boundingBox.yCord;
		radius = thatRadius;
	}
	
	public void draw(Graphics g){
		System.out.println(x);
		System.out.println(y);
		x = x/2-radius;
		y = y/2-radius;
		g.drawOval(x, y, radius*2, radius*2);
		boundingBox(x,y,x+(radius * 2), y+ (radius*2));
		//System.out.println(x+y);
		boundingBox.draw(g);
		
	}
	
	public void boundingBox(int lowerLeftX, int lowerLeftY, int upperRightX, int upperRightY){
		//double pythagoras = Math.pow((double)(upperRightY - lowerLeftY), 2) + Math.pow((double)(upperRightX - lowerLeftX), 2);
		//double boundingBoxSize = Math.sqrt(pythagoras);
		
		if(lowerLeftX < boundingBox.lowerLeftX)
			boundingBox.lowerLeftX = lowerLeftX;
		if(lowerLeftY < boundingBox.lowerLeftY)
			boundingBox.lowerLeftY = lowerLeftY;
		if(upperRightX < boundingBox.upperRightX)
			boundingBox.upperRightX = upperRightX;
		if(upperRightY < boundingBox.upperRightY)
			boundingBox.upperRightY = upperRightY;
		
	}
	
}
