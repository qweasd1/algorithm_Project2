package test;

import static org.junit.Assert.*;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

import input.AdjacencyListInputProvider;
import input.OneDimInputProvider;
import input.TwoDimArrayInputProvider;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import utility.Utility;
import algorithm.Algorithm;
import algorithm.Floyd_AdjacencyList_Algorithm;
import algorithm.Floyd_OneDimArray_Algorithm;
import algorithm.Floyd_TwoDimArray_Algorithm;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Floyd_Algorithmn_Test {

	private int size = 500;
	MemoryMXBean mxBean = ManagementFactory.getMemoryMXBean();
	long memory_pre;
	
	@Test
	public void TwoDimArray_Complete() {
		recordStartMemory();
		TwoDimArrayInputProvider provider = new TwoDimArrayInputProvider(size);
		Floyd_TwoDimArray_Algorithm algorithm = new Floyd_TwoDimArray_Algorithm();
		
		runAlgorithm(algorithm, provider.createCompleteInput(), "TwoDimArray_Complete");
	}
	
	@Test
	public void TwoDimArray_Sparse() {
		recordStartMemory();
		TwoDimArrayInputProvider provider = new TwoDimArrayInputProvider(size);
		Floyd_TwoDimArray_Algorithm algorithm = new Floyd_TwoDimArray_Algorithm();
		
		runAlgorithm(algorithm, provider.createSparseInput(), "TwoDimArray_Sparse");
	}
	
	@Test
	public void AdjacencyList_Complete() throws Exception {
		recordStartMemory();
		AdjacencyListInputProvider provider = new AdjacencyListInputProvider(size);
		Floyd_AdjacencyList_Algorithm algorithm = new Floyd_AdjacencyList_Algorithm();
		runAlgorithm(algorithm, provider.createCompleteInput(),"AdjacencyList_Complete");
		
	}
	
	@Test
	public void AdjacencyList_Sparse() throws Exception {
		recordStartMemory();
		AdjacencyListInputProvider provider = new AdjacencyListInputProvider(size);
		Floyd_AdjacencyList_Algorithm algorithm = new Floyd_AdjacencyList_Algorithm();
		runAlgorithm(algorithm, provider.createSparseInput(),"AdjacencyList_Sparse");
	}
	
	
	@Test
	public void OneDim_Complete() throws Exception {
		recordStartMemory();
		OneDimInputProvider provider = new OneDimInputProvider(size);
		Floyd_OneDimArray_Algorithm algorithm = new Floyd_OneDimArray_Algorithm();
		runAlgorithm(algorithm, provider.createCompleteInput(),"OneDim_Complete");
		
	}
	
	@Test
	public void OneDim_Sparse() throws Exception {
		recordStartMemory();
		OneDimInputProvider provider = new OneDimInputProvider(size);
		Floyd_OneDimArray_Algorithm algorithm = new Floyd_OneDimArray_Algorithm();
		runAlgorithm(algorithm, provider.createSparseInput(),"OneDim_Sparse");
	}
	
	
	private void recordStartMemory() {
		memory_pre = mxBean.getHeapMemoryUsage().getUsed() + mxBean.getNonHeapMemoryUsage().getUsed();
	}
	
	private <Input> void runAlgorithm(Algorithm ag, Input input, String testName) {		 
		 
		 long consumingTime = ag.run(input);
		 long memory_post = mxBean.getHeapMemoryUsage().getUsed() + mxBean.getNonHeapMemoryUsage().getUsed();
		 double memory_consume = (memory_post - memory_pre) / 1024.0 /1024.0;
		 memory_consume = Math.round(memory_consume);
		 System.out.println(testName + " [time: " + consumingTime + ", memory: " + memory_consume + "mb]");
	}

}
