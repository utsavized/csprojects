package simulator;

public class Limit {
	public int friends = 150;
	public int comm = 10;
	float limit = (float) 0.0;
	public Limit(){
		
	}
	
	public void calculate(){
		for(int i = 1; i <=  friends; i++){
			limit = (float) 10 * ( 2 * i - 1)/i;
			System.out.println("Friend: " + i + " | Disc: " + limit);
		}
	}
}
