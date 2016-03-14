package P3;

public class NQueue_Backtracking {
	private static int n = 4;
	private static int[] col = new int[n];
	
	private static int count = 0;
	
	public static void main(String[] args) {
		queues(0);
		System.out.println(count);
	}
	
	private static void queues(int row_index) {
		if (row_index == n) {
			count++;			
		}
		else {
			for (int i = 0; i < n; i++) {
				col[row_index] = i;
				if (promising(row_index)) {
					queues(row_index + 1);
				}
			}
		}
	}
	
	private static boolean promising(int i){
		boolean isSuccess = true;
		for(int k = 0; k < i; k++){
			if(col[k] == col[i] ||  Math.abs(col[k] - col[i]) == i - k){
				isSuccess = false;
				break;
			}
		}
		return isSuccess;
	}
	
	private static void print() {
		for (int i = 0; i < col.length; i++) {
			System.out.print("("+ i + "," + col[i] + ") ");
		}
	}

}
