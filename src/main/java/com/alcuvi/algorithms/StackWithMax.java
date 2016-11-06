package com.alcuvi.algorithms;

public interface StackWithMax<T> {
	public void push(T element);
	public T pop();	
	public T max();
}
