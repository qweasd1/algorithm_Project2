package test;
import java.util.Random;

public class RandomNum {
	private int n;
	private double[][] sparseNu2D;
	private double[][] completeNu2D;

	public RandomNum(int a) {
		this.n = a;
		sparseNu2D = new double[n][n];
		completeNu2D= new double[n][n];
		InitialSparse();
		Print(sparseNu2D);
		InitialComplete();
		Print(completeNu2D);
		RandomSparse();
		Print(sparseNu2D);
		RandomComplete();
		Print(completeNu2D);
	}
	public void InitialSparse(){
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(i==j){
					sparseNu2D[i][j]=0.0;
					
				}else{
					sparseNu2D[i][j]=Double.POSITIVE_INFINITY;	
				}							
			}
		}
	}
	public void InitialComplete(){
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				completeNu2D[i][j]=0;				
			}
		}
	}
	public void RandomSparse(){
		Random ran = new Random();
		for (int i=1;i<n;i++){
			int p= ran.nextInt(i-1);
			sparseNu2D[i][p]=ran.nextInt(20)+1;
		}
		for(int i=1;i<n;i++){
			for(int j=i+1;j<n-1;j++){
				sparseNu2D[i][j]=sparseNu2D[j][i];
			}
		}
	}
	public void RandomComplete(){
		Random ran = new Random();
		for(int i=1;i<n;i++){
			for(int j=i+1;j<n;j++){
				completeNu2D[i][j]=ran.nextInt(20)+1;
				completeNu2D[j][i]=completeNu2D[i][j];
			}
		}
	}


	public double[][] Sparse2D(){
		return sparseNu2D;		
	}

	public double[] Sparse1D(){
		double[] sparseNu1= new double[n*(n-1)/2];
		for(int i=0;i<sparseNu1.length;i++){
			for(int j=1;j<sparseNu1.length;j++){
				for(int k=0;k<j;k++){
					sparseNu1[i]=(int)sparseNu2D[j][k];
				}
			}
		}
		return sparseNu1;		
	}
//	public int[] SparseLinkList(){
//		return null;
		
//	}
	public double[][] Complete2D(){
		return completeNu2D;		
	}

	public double[] Complete1D(){
		double[] completeNu1= new double[n*(n-1)/2];
		for(int i=0;i<completeNu1.length;i++){
			for(int j=1;j<completeNu1.length;j++){
				for(int k=0;k<j;k++){
					completeNu1[i]=completeNu2D[j][k];
				}
			}
		}
		return completeNu1;		
	}
//	public int[] CompleteLinkList(){
//		return null;		
//	}

	public void Print(double[][] a){
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				System.out.print(a[i][j]+" ");				
			}
			System.out.println();
		}		
	}


}
