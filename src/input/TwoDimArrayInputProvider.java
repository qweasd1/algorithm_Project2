package input;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.omg.CORBA.TypeCodePackage.Bounds;

import utility.Utility;

//generate 2-dimension array representation for graph
public class TwoDimArrayInputProvider extends InputProvider<double[][]> {


	
	

	public TwoDimArrayInputProvider(int size){
		super(size);
	}
	

	@Override
	public double[][] createCompleteInput() {
		double[][] input = new double[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = i + 1; j < size; j++) {
				double weight = Utility.nextDouble();
				input[i][j] = weight;
				input[j][i] = weight;
			}
		}
		
		return input;
	}


	@Override
	public double[][] createSparseInput() {
		double[][] input = Utility.createAdjacantArrayTemplate(size);
		
		List<Integer> candidate_indice = new ArrayList<Integer>();
	    List<Integer> rest_indice = new ArrayList<Integer>();
	    
	    for (int i = 0; i < size; i++) {
			rest_indice.add(i);
		}
		
	    //get first ca node randomly
		Integer firstNode = Utility.nextRemoveIntFrom(rest_indice);
		candidate_indice.add(firstNode);
		
		for (int i = 0; i < size - 1; i++) {
			int node1 = Utility.nextIntFrom(candidate_indice);
			int node2 = Utility.nextRemoveIntFrom(rest_indice);
			
			Double weight = Utility.nextDouble();
			input[node1][node2] = weight;
			input[node2][node1] = weight;
			
			candidate_indice.add(node2);
		}
		
		return input;
	}

}
