package input;


public class Node {
	public int index;
	public double weight;
	public Node next;
	
	// insert a new node after the current Node and return the inserted Node
	public Node append(int index, double weight) {
		Node newNode = new Node();
		newNode.index = index;
		newNode.weight = weight;
		newNode.next = this.next;
		this.next = newNode;
		return newNode;
	}
	
	@Override
	public String toString(){
		String fragment =  String.format("(%d,%f)", index,weight);
		
		if (next != null) {
			return fragment + "->" + next.toString();
		}
		else {
			return fragment;
		}
	}
	
	public Node clone() {
		Node newNode = new Node();
		newNode.index = index;
		newNode.weight = weight;
		if (next != null) {
			newNode.next = next.clone();
		}
		return newNode;
	}
}
