package algorithm;

import utility.Utility;
import input.Node;

public class Floyd_AdjacencyList_Algorithm extends Algorithm<Node[]> {

	@Override
	protected void run_impl(Node[] input) {
		int size = input.length;
		
		Node[] d = copy_adjacency_list(input);
		
		
		
		for (int k = 0; k < size; k++) {
			
			
			
			// create a cache for d(i,k)
			Double[] d_k = create_k_cache(d[k], size);
			
			for (int i = 0; i < d.length; i++) {
				Node cursor = d[i];
				Node tail = null;
				boolean[] exist_indice = new boolean[size];
				
				while (true) {
					if (d_k[i] != null && d_k[cursor.index] != null) {
						cursor.weight = Math.min(cursor.weight, d_k[i] + d_k[cursor.index]);
					}
					
					exist_indice[cursor.index] = true;
					
					
					if (cursor.next != null) {
						cursor = cursor.next;
					}
					else {
						tail = cursor;
					    break;
					}
				}
				
				
				
				if (d_k[i] != null) {
					for (int j = 0; j < size; j++) {
						Double d_k_j = d_k[j];
						
						if (d_k_j != null && !exist_indice[j]) {
							tail = tail.append(j, d_k[i] + d_k_j);
						}	
						
					}
				}
				
			}
			
			//System.out.println(k + ":");
			//Utility.print_adjacency_list(d);
		}
		
		//Utility.print_adjacency_list(d);
		
	}
	
	private Node[] copy_adjacency_list(Node[] adj_list) {
		int size = adj_list.length;
		
		Node[] copy = new Node[size];
		for (int i = 0; i < adj_list.length; i++) {
			copy[i] = adj_list[i].clone();
		}
		
		return copy;
	}
	
	private Double[] create_k_cache(Node k_head, int size){
		Double[] k_cache = new Double[size];
		
		Node k_cursor = k_head;
		
		do {
			k_cache[k_cursor.index] = k_cursor.weight;
			k_cursor = k_cursor.next;
			
		} while (k_cursor!= null);
		
		return k_cache;
	}

}
