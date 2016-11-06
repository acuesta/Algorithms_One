package com.alcuvi.algorithms;

public class FixedCapacityStack<T> {
	private T[] s;
	private int N = 0;

	@SuppressWarnings("unchecked")
	public FixedCapacityStack(int capacity) {
		s = (T[]) new Object[capacity];
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public void push(T item) {
		s[N++] = item;
	}

	public T pop() {
		return s[--N];
	}
}