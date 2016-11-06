package com.alcuvi.algorithms;

public class QueueByTwoStacks implements Queue<Double> {

	private DoubleStackWithMax enqueueStack = new DoubleStackWithMax(100);
	private DoubleStackWithMax dequeueStack = new DoubleStackWithMax(100);

	public void enqueue(Double element) {
		if (!dequeueStack.isEmpty()){
			for (Double aux : dequeueStack) {
				enqueueStack.push((aux));
			}
			dequeueStack = new DoubleStackWithMax(100);
		}
		enqueueStack.push(element);
	}

	public Double dequeue() {
		if (!enqueueStack.isEmpty()){
			for (Double aux : enqueueStack) {
				dequeueStack.push((aux));
			}
			enqueueStack = new DoubleStackWithMax(100);
		}
		
		return dequeueStack.pop();
	}

}
