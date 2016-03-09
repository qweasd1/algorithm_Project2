package branch_and_bound;

import java.util.ArrayList;
import java.util.List;

public class Node {
	public int level;
	public double bound;
	public Integer index;	
	public Node parent;
	private int size;
	
	public Node(int index, Node parent,int size){
		this.index = index;
		this.parent = parent;
		this.size = size;
		if (this.parent != null) {
			this.level = parent.level + 1;
			
		}
		else {
			this.level = 0;
		}
		
	}
	
	public List<Integer> children() {
		List<Integer> result = new ArrayList<>();
		for (int i = 1; i < this.size; i++) {
			result.add(i);
		}
		Node cursor = this;
		while (true) {
			result.remove(cursor.index);
			if (cursor.parent != null) {
				cursor = cursor.parent;
			}
			else {
				break;
			}
		}
		return result;
	}
	
	public List<Integer> paths() {
		List<Integer> result = new ArrayList<>();
		Node cursor = this;
		while (true) {
			result.add(cursor.index);
			if (cursor.parent != null) {
				cursor = cursor.parent;
			}
			else {
				break;
			}
		}
		
		
		return result;
	}
	
	public boolean isSolution() {
		return this.level == this.size - 2;
	}
}
