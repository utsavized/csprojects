package stacks;

import stacks.implementations.LinkedStack;


public class HanoiStack {
	LinkedStack<Integer> from = new LinkedStack<Integer>();
	LinkedStack<Integer> to = new LinkedStack<Integer>();
	LinkedStack<Integer> spare = new LinkedStack<Integer>();
	
	public HanoiStack() {
		this.from.push(3);
		this.from.push(2);
		this.from.push(1);
	}
	
	public void move() throws Exception {
		System.out.println(this.from);
		System.out.println(this.to);
		System.out.println(this.spare);
		
		while(this.from.size() > 0) {
			to.push(from.pop());
			spare.push(from.pop());
			spare.push(to.pop());
			to.push(from.pop());
			
			from.push(spare.pop());
			to.push(spare.pop());
			to.push(from.pop());
		}
		
		System.out.println(this.from);
		System.out.println(this.to);
		System.out.println(this.spare);
	}
}
