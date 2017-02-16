import java.awt.Graphics;


public class group implements shape {
	private shape[] s;
	public group(shape...thatS){
		s = thatS;
	}
	
	@Override
	public void draw(Graphics g) {
		for(shape sh: s){
			sh.draw(g);
		}
	}
}
