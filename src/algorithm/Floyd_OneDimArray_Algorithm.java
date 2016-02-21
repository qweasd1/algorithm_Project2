package algorithm;

import java.util.Arrays;

import utility.Utility;

public class Floyd_OneDimArray_Algorithm extends Algorithm<double[]> {

	@Override
	protected void run_impl(double[] input) {
		double[] d = Arrays.copyOf(input, input.length);
		int size = (int)Math.floor(Math.sqrt(input.length * 2));
		
		for (int k = 0; k < size; k++) {
			
			double[] d_k = createCache(d, size, k);
			
			for (int i = 0; i < size; i++) {
				int base = i * (i + 1) / 2;
				for (int j = 0; j <= i; j++) {
					d[base + j] = Math.min(d[base + j], d_k[i] + d_k[j]);
				}
			}			
		}
		
		//Utility.print_one_dim(d);
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
