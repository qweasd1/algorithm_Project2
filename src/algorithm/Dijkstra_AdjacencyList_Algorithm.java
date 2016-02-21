package algorithm;

import java.util.Arrays;

import utility.Utility;
import input.Node;

public class Dijkstra_AdjacencyList_Algorithm extends Algorithm<Node[]> {

	@Override
	protected void run_impl(Node[] input) {
		//Utility.print_adjacency_list(input);
		//System.out.println();
		
		int size = input.length;
		
		double[][] d = new double[size][];
		
		for (int i = 0; i < input.length; i++) {
			boolean[] isSelected = new boolean[size];
			isSelected[i] = true;
			
			double[] d_i = createCache(input, size, i);
			
			for (int j = 1; j < size; j++) {
				int min_index = getMinIndex(d_i, isSelected);
				isSelected[min_index] = true;
				
				double[] cache = createCache(input, size, min_index);
				for (int k = 0; k < size; k++) {
					if (!isSelected[k]) {
						d_i[k] = Math.min(d_i[k], cache[k] + d_i[min_index]);
					}
				}
			}
			
			d[i] = d_i;
		}
		
		//Utility.print_two_dim(d);
	}

	private double[] createCache(Node[] input, int size, int i) {
		double[] d_i = new double[size];
		Arrays.fill(d_i, Double.POSITIVE_INFINITY);
		d_i[i] = 0;
		
		Node cursor = input[i];
		
		while (true) {
			d_i[cursor.index] = cursor.weight;
			if (cursor.next != null) {
				cursor = cursor.next;
			}
			else {
				break;
			}
		}
		
		return d_i;
	}
	
	private int getMinIndex(double[] d_i, boolean[] isSelected) {
		double min = Double.POSITIVE_INFINITY;
		int min_index = 0;
		for (int k = 1; k < d_i.length; k++) {
			if (!isSelected[k] && d_i[k] < min) {
				min_index = k;
				min = d_i[k];
			}
		}
		
		return min_index;
	}

}
