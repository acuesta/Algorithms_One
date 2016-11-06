package com.alcuvi.algorithms;

import java.util.Iterator;

public class LinkedStack<T> implements Iterable<T>{
	
	private Node first = null;

	public boolean isEmpty() {
		return first == null;
	}

	public void push(T item) {
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
	}

	public T pop() {
		T item = first.item;
		first = first.next;
		return item;
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedStackIterator();
	}
	
	private class Node {
		T item;
		Node next;
	}
	
	private class LinkedStackIterator implements Iterator<T>{

		private Node current = first;
		
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			T item = current.item;
			current = current.next;
			return item;
		}
		
	}
}