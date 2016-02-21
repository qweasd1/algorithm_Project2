package algorithm;

import input.InputProvider;

public abstract class Algorithm<TInput> {

	// run algorithm and return consuming time
	public long run(TInput input) {
		long start = System.currentTimeMillis();
		
		run_impl(input);
		
		long end = System.currentTimeMillis();
		
		return end - start;

	}
	
	protected abstract void run_impl(TInput input);
}
