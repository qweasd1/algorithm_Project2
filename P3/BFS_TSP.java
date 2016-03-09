package branch_and_bound;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import javax.swing.text.AsyncBoxView.ChildLocator;

public class BFS_TSP {

	public  int size;
	public  double[][] input;

	public void run(double[][] input) {

		PriorityQueue<Node> pq = new PriorityQueue<>(100,
				new Comparator<Node>() {

					@Override
					public int compare(Node o1, Node o2) {
						if (o1.bound > o2.bound) {
							return 1;
						} else if (o1.bound < o2.bound) {
							return -1;
						}
						return 0;
					}
				});

		this.input =  input;
		size = input.length;

		Node root = new Node(0, null, size);
		calBound(root);
		pq.add(root);
		
		double min = Double.POSITIVE_INFINITY;
		Node   min_node = null;
		
		while (true) {
			Node current = pq.poll();
			
			if (current.isSolution()) {
				double v = length(current);
				if (v < min) {
					min = v;
					min_node = current;
				}
			}
			else {
				for (int cindex : current.children()) {
					if (input[current.index][cindex] != Double.POSITIVE_INFINITY) {
						Node child = new Node(cindex, current, size);
						calBound(child);
						pq.add(child);
					}
				}
			}
			
			
			if (pq.isEmpty()) {
				break;
			}
		}
		
		System.out.println(min);
		print_path(min_node);
	}

	public  void calBound(Node node) {
		double sum = 0;

		List<Integer> paths = node.paths();
		for (int i = 1; i < paths.size(); i++) {
			sum += input[paths.get(i)][paths.get(i-1)];
		}
		
		sum += partial_min(0, paths);
		paths.remove(node.index);
		for (Integer cindex : node.children()) {
			paths.add(cindex);
			sum += partial_min(cindex, paths);
			paths.remove(cindex);
		}
		
		node.bound = sum;
	}

	public  double partial_min(int end, List<Integer> excludes) {
		double min = Double.POSITIVE_INFINITY;
		
			for (int i = 0; i < size; i++) {
				if (!excludes.contains(i)) {
					double v = input[i][end];
					if (v < min) {
						min = v;
					}
				}
			}
		
		return min;
	}

	public  double length(Node node) {
		double sum = 0;
		List<Integer> paths = node.paths();
		for (int i = 1; i < paths.size(); i++) {
			sum += input[paths.get(i)][paths.get(i-1)];
		}
		int n_2 = paths.get(0);
		int n_1 = node.children().get(0);
		sum += input[n_2][n_1];
		sum += input[n_1][0];
		return sum;
	}

	public  void print_path(Node node) {
		
		List<Integer> paths = node.paths();
		for (int i = paths.size() -1; i >= 0; i--) {
			System.out.print(paths.get(i) + " -> ");
		}
		
		int n_1 = node.children().get(0);
		System.out.println(n_1 + " -> 0");
		

	}
	
	
	private  double[][] createInput() {
		double[][] w = { 
				{ 0, 2.0, 2, 1 }, 
				{ 1.0, 0, 3.0, 1 },
				{ 1.0, 3, 0, 1 }, 
				{ 1.0, 4, 1, 0 } };

		return w;
	}
}
