package branch_and_bound;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DYN_TPS {

	private  int size;
	//private  int last = size -1;
	
	public void run(double[][] input) {
		
		//double[][] input = createInput();
		size = input.length;
		List<Map<Set<Integer>, Double>> D = createRecord();
		List<Map<Set<Integer>, Integer>> P = createRecord();
		
		
		for (int i = 1; i < size; i++) {
			D.get(i).put(set(), input[i][0]);
		}
		
		
			 
	    for (int k = 1; k < size -1; k++) {
				 
			for (int i = 1; i < size; i++) {
				Map<Set<Integer>, Double> d_i = D.get(i);
				Map<Set<Integer>, Integer> p_i = P.get(i);
				List<Set<Integer>> iter = generate_Cnk(size, k,i);
				
					 for (Set<Integer> A : iter) {
						Object[] indice = A.toArray();
						double min = Double.POSITIVE_INFINITY;
						int min_index = -1;
						for (int j = 0; j < indice.length; j++) {
							Integer index = (Integer)indice[j];
							A.remove(index);
							double dist = input[i][index] +  D.get(index).get(A);
							if (dist < min) {
								min = dist;
								min_index = index;
							}
							A.add(index);
						}
						
						d_i.put(A, min);
						p_i.put(A, min_index);
					}						
			 }
		}
		
		double min = Double.POSITIVE_INFINITY;
		int min_index = -1;
		HashSet<Integer> s = fullset();
		
		for (int i = 1; i < size; i++) {
			s.remove(i);
			double v = input[0][i] + D.get(i).get(s);
			if (v < min) {
				min = v;
				min_index = i;
			}
			s.add(i);
		}
		
		System.out.print("0 -> " + min_index + " -> ");
		s.remove(min_index);
		while(true) {			
			if (s.size() > 0) {
				min_index = P.get(min_index).get(s);
				System.out.print(min_index + " -> ");
				s.remove(min_index);
			}
			else {
				break;
			}
		}
		
		System.out.println("0");
		System.out.println("min length: " + min);
	}
	
	private  List<Set<Integer>> generate_Cnk(int n, int k, int exclude){
		List<Set<Integer>> result = new ArrayList<>();
		int[] cache = new int[k];
		populate_set(result, cache, k-1, 1, exclude);
		
		return result;
	}
	
	private  void populate_set(List<Set<Integer>> result, int[] cache, int count, int start, int exclude) {
		if (count == 0) {
			for (int i = start; i < size; i++) {
				if( i != exclude){
					cache[0] = i;
					result.add(set(cache));
				}
			}
		}
		else {
			for (int i = start; i < size - count; i++) {
				if (i != exclude) {
					cache[count] = i;
					populate_set(result, cache, count - 1, i + 1, exclude);
				}
			}
		}
	}
	
	private  HashSet<Integer> fullset() {
		HashSet<Integer> s = set();
		for (int i = 1; i < size; i++) {
			s.add(i);
		}
		return s;
	}
	
	private  HashSet<Integer> set(int[] cache) {
		HashSet<Integer> result = set();
		for (int i = 0; i < cache.length; i++) {
			result.add(cache[i]);
		}
		return result;
	}
	
	private  HashSet<Integer> set() {
		return new HashSet<Integer>();
	}
	
	
	
	
	private  double[][] createInput() {
//		double[][] w = { 
//				{ 0, 2.0, 2, 1 }, 
//				{ 1.0, 0, 3.0, 1 },
//				{ 1.0, 3, 0, 1 }, 
//				{ 1.0, 4, 1, 0 } };
		double[][] w = new TwoDimArrayInputProvider(100).createCompleteInput();
		
		return w;
	}
	
	private   <T> List<Map<Set<Integer>, T>> createRecord() {
		List<Map<Set<Integer>, T>> result =  new ArrayList<Map<Set<Integer>, T>>();
		for (int i = 0; i < size; i++) {
			result.add(new HashMap<Set<Integer>, T>());
		}
		return result;
	}

}
