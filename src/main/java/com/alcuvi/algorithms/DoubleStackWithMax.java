package com.alcuvi.algorithms;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleStackWithMax implements StackWithMax<Double>, Iterable<Double>{
	private Double[] stack;
	private Double[] max;
	private int stackIndex = 0;
	public DoubleStackWithMax(int stackSize){
		stack = new Double[stackSize];
		max = new Double[stackSize];
	}
	@Override
	public void push(Double element) {
		stack[stackIndex] = element;
		if (stackIndex == 0 || element >= max[stackIndex-1]){
			max[stackIndex] = element; 
		} else {
			max[stackIndex] = max[stackIndex-1];
		}
		stackIndex++;
	}	
	@Override
	public Double pop(){
		return stack[--stackIndex];
	}
	@Override
	public Double max() {
		return max[stackIndex-1];
	}
	
	public boolean isEmpty() {
		return stackIndex == 0;
	}
	
	 private class ArrayIterator implements Iterator<Double> {
	        private int current;
	        private Double[] itQueue;

	        public ArrayIterator() {
	            itQueue = new Double[100];

	            for (int i = 0; i < 100; i++) {
	                itQueue[i] = stack[i];
	            }
	            current = stackIndex;
	        }

	        public boolean hasNext() {
	            return itQueue[current] != null;
	        }

	        public Double next() {
	            if (!hasNext())
	                throw new NoSuchElementException();

	            Double item = itQueue[current];
	            current--;

	            return item;
	        }

	        public void remove() {
	            throw new UnsupportedOperationException();
	        }
	    }
	@Override
	public Iterator<Double> iterator() {
		return new ArrayIterator();
	}
	
}
