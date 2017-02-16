import java.awt.Graphics;
//import java.awt.Graphics2D;

public class boundingBox{
	public int xCord = 0;
	public int yCord = 0;
	
	public static int lowerLeftX;
	public static int lowerLeftY;
	public static int upperRightX;
	public static int upperRightY;
	
	public static void draw(Graphics g) {
		g.drawRect(lowerLeftX, upperRightY, upperRightX - lowerLeftX, upperRightY - lowerLeftY);
	}
	
	public void setXCord(int value){
		xCord = value;
	}
	
	public void setYCord(int value){
		yCord = value;
	}
	
	public int getXCord(){
		return xCord;
	}
	
	public int getYCord(){
		return yCord;
	}
	

}
