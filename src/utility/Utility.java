package utility;

import input.Node;

import java.util.List;
import java.util.Random;

public class Utility {
	
	public static Random random = new Random();
	
	public static int nextRemoveIntFrom(List<Integer> intList) {
		 int nextIndex = random.nextInt(intList.size());
		 Integer result = intList.get(nextIndex);
		 intList.remove(nextIndex);
		 return result;
	}
	
	public static int nextIntFrom(List<Integer> intList) {
		 int nextIndex = random.nextInt(intList.size());
		 Integer result = intList.get(nextIndex);
		 return result;
	}
	
	public static double nextWeight() {
		 boolean isNoWeight = random.nextBoolean();
		 if (isNoWeight) {
			return Double.POSITIVE_INFINITY;
		}
		 else {
			return random.nextDouble();
		}
	}
	
	public static double nextDouble() {
		return random.nextInt(9) + 1;
	}
	
	public static int nextInt(int size) {
		return random.nextInt(size);
	}
	
	public static double[][] createAdjacantArrayTemplate(int size) {
		double[][] result = new double[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				result[i][j] = Double.POSITIVE_INFINITY;
			}
		}
		
		for (int i = 0; i < size; i++) {
			result[i][i]= 0; 
		}
		
		return result;
	}
	
	public static void print_adjacency_list(Node[] adj_list) {
		int size = adj_list.length;
		
		Double[][] matrix = new Double[size][size];
		
		for (int i = 0; i < size; i++) {
			Node cursor = adj_list[i];
			
			while (cursor != null) {
				matrix[i][cursor.index] = cursor.weight;
				cursor = cursor.next;
			}
		}
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size - 1; j++) {
				System.out.print((matrix[i][j] == null ? null : matrix[i][j] + " ")  + ","); 
			}
			System.out.println(matrix[i][size - 1]);
		}
		
	}
	
	public static void print_one_dim(double[] input) {
		int size = (int) Math.floor(Math.sqrt(input.length * 2));
		
		double[][] matrix = new double[size][size];
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j <= i; j++) {
				matrix[i][j] = matrix[j][i] = input[(1 + i) * i /2 + j];
			}
		}
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size - 1; j++) {
				System.out.print((matrix[i][j] == Double.POSITIVE_INFINITY ? null : matrix[i][j] + " ")  + ","); 
			}
			System.out.println(matrix[i][size - 1] == Double.POSITIVE_INFINITY ? null : matrix[i][size - 1]);
		}
	}
	
	public static void print_two_dim(double[][] input) {
		int size = input.length;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size - 1; j++) {
				System.out.print((input[i][j] == Double.POSITIVE_INFINITY ? null : input[i][j] + " ")  + ","); 
			}
			System.out.println(input[i][size - 1] == Double.POSITIVE_INFINITY ? null : input[i][size - 1]);
		}
	}
}
