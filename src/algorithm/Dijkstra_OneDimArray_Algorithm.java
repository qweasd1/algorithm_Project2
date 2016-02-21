package algorithm;

import java.util.Arrays;

import utility.Utility;

public class Dijkstra_OneDimArray_Algorithm extends Algorithm<double[]> {

	@Override
	protected void run_impl(double[] input) {
		int size = (int)Math.floor(Math.sqrt(input.length * 2));
		
		
		double[][] d = new double[size][];
		
		for (int i = 0; i < size; i++) {
			boolean[] isSelected = new boolean[size];
			isSelected[i] = true;
			
			double[] d_ref = createCache(input,size, i);
			
			
			double[] d_i = Arrays.copyOf(d_ref, size);
			
			
			for (int j = 1; j < size; j++) {
				int min_index = getMinIndex(d_i, isSelected);
				isSelected[min_index] = true;
				
				
				double[] min_index_cache = createCache(input, size, min_index);
				
				for (int k = 0; k < size; k++) {
					if (!isSelected[k]) {
						d_i[k] = Math.min(d_i[k], min_index_cache[k] + d_i[min_index]);
					}
				}
			}
			
			d[i] = d_i;
		}
		
		//Utility.print_two_dim(d);
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
	
	private double[] createCache(double[] input, int size, int k) {
		double[] d_k = new double[size];
		
		int base_k = k * (1 + k) / 2;
		for (int i = 0; i <=k; i++) {
			d_k[i] = input[base_k + i];
		}
		
		int cursor = base_k + k;
		for (int i = k + 1; i < size; i++) {
			cursor += i;
			d_k[i] = input[cursor];
		}
		
		return d_k;
	}

}
