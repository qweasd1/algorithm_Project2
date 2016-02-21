package input;

public abstract class InputProvider<TInput> {
	
	 protected int size;
	
	 InputProvider(int size){
		 this.size = size;
	 }
	
	 public abstract TInput createCompleteInput();
	 public abstract TInput createSparseInput();
}
