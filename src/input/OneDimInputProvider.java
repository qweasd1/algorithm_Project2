package input;

import java.util.List;
import java.util.ArrayList;

import utility.Utility;

public class OneDimInputProvider extends InputProvider<double[]> {

	public OneDimInputProvider(int size) {
		super(size);
	}

	@Override
	public double[] createCompleteInput() {
		int inputLength = (1 + size) * size / 2;
		
		double[] input = new double[inputLength];
		
		int index = 0;
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j <= i; j++) {
				if (i == j) {
					input[index] = 0;
				}
				else {
					input[index] = Utility.nextDouble();
				}
				
				index++;
			}
		}
		
		return input;
	}

	@Override
	public double[] createSparseInput() {
		List<Integer> selected = new ArrayList<>();
		List<Integer> rest = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			rest.add(i);
		}
		
		int head = Utility.nextInt(size);
		selected.add(head);
		rest.remove(head);
		
		int inputLength = (1 + size) * size / 2;
		double[] input = new double[inputLength];
		
		for (int i = 0; i < input.length; i++) {
			input[i] = Double.POSITIVE_INFINITY;
		}
		
		int base = -1;
		for (int i = 1; i <= size; i++) {
			base += i;
			input[base] = 0;
		}
		
		for (int i = 1; i < size; i++) {
			int node_0 = Utility.nextIntFrom(selected);
			int node_1 = Utility.nextRemoveIntFrom(rest);
			
			double weight = Utility.nextDouble();
			
			int min = Math.min(node_0, node_1);
			int max = Math.max(node_0, node_1);
			
			int index =  max * (max + 1) / 2 + min;
			input[index] = weight;
			
			selected.add(node_1);
		}
		
		
		
		return input;
	}

	

}
