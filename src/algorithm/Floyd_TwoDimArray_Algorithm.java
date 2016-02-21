package algorithm;

public class Floyd_TwoDimArray_Algorithm extends Algorithm<double[][]> {

	
	
	@Override
	protected void run_impl(double[][] input) {
		int size = input.length;
		
		double[][] d = input;
		
		for (int k = 0; k < size; k++) {
			double[] d_k = d[k];
			
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					d[i][j] = Math.min(d[i][j], d_k[i] + d_k[j]);
				}				
			}
			
		}
		
		//the shortest path pair is stored in d
		//System.out.println(d.toString());
	}
	
}
