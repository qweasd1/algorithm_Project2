package branch_and_bound;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class test {

	public static void main(String[] args) {
		TwoDimArrayInputProvider inputProvider = new TwoDimArrayInputProvider(15);
		double[][] input = inputProvider.createCompleteInput();
		
		
		BFS_TSP bfs = new BFS_TSP();
		DYN_TPS dyn = new DYN_TPS();
		
		start();
		//bfs.run(input);
		end("Bread first search TPS run: ");
		
		start();
		dyn.run(input);
		end("dynamic programming search TPS run: ");
		
		
//		System.out.println(d.get(test));
//		test.remove(2);
//		System.out.println(d.get(test));
	}
	
	
	private static long startTime = -1;
	
	private static void start() {
		startTime = new Date().getTime();
	}
	
	private static void end(String message) {
		long due = new Date().getTime() -startTime;		
		System.out.println(message + due / 1000.0 + "s");
		System.out.println();
	}

}
