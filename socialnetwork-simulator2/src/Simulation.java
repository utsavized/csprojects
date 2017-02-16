public class Simulation {
	private final static int SIZE = 1000;
	public static void main(String[] args){
		//size, intersection, discount, price
		BuildNetwork newNetwork = new BuildNetwork(SIZE, 10.0);
		newNetwork.addMembers();
		newNetwork.buildNetwork();
		newNetwork.addPice();
		newNetwork.updateDiscount();
	}
}
