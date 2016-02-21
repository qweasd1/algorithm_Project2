package algorithm;

import java.util.Arrays;

import utility.Utility;

public class Dijkstra_TwoDimArray_Algorithm extends Algorithm<double[][]> {

	@Override
	protected void run_impl(double[][] input) {
		int size = input.length;
		
		double[][] d = new double[size][];
		
		
		
		for (int i = 0; i < size; i++) {
			boolean[] isSelected = new boolean[size];
			isSelected[i] = true;
			
			
			double[] d_i = Arrays.copyOf(input[i], size);
			
			for (int j = 1; j < size; j++) {
				int min_index = getMinIndex(d_i, isSelected);
				isSelected[min_index] = true;
				
				double[] cache = input[min_index];
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
