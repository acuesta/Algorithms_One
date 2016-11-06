package com.alcuvi.algorithms.week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

// import org.junit.Assert;

/**
 * Dequeue. A double-ended queue or deque (pronounced "deck") is a
 * generalization of a stack and a queue that supports adding and removing items
 * from either the front or the back of the data structure.
 * 
 * @param <Item> element Deque type.
 * @author alcuvi
 */
public class Deque<Item> implements Iterable<Item> {

	private int size;
	private Node first;
	private Node last;

	private class Node {
		private Item item;
		private Node previous;
		private Node next;
	}

	// construct an empty deque
	public Deque() {
		size = 0;
		first = null;
		last = null;
	}

	// is the deque empty?
	public boolean isEmpty() {
		return size == 0;
	}

	// return the number of items on the deque
	public int size() {
		return size;
	}

	// add the item to the front
	public void addFirst(final Item item) {
		if (item == null) {
			throw new NullPointerException("Cannot add NULL elements.");
		}
		Node newFirst = new Node();
		newFirst.item = item;
		if (size == 0) {
			first = newFirst;
			last = newFirst;
		} else {
			first.previous = newFirst;
			newFirst.next = first;
		}
		first = newFirst;
		size++;
	}

	// add the item to the end
	public void addLast(final Item item) {
		if (item == null) {
			throw new NullPointerException("Cannot add NULL elements.");
		}
		Node newLast = new Node();
		newLast.item = item;
		if (size == 0) {
			first = newLast;
			last = newLast;
		} else {
			last.next = newLast;
			newLast.previous = last;
		}
		last = newLast;
		size++;
	}

	// remove and return the item from the front
	public Item removeFirst() {
		if (size == 0) {
			throw new NoSuchElementException(
					"Cannot remove first element from an empty Deque.");
		}
		Item aux = first.item;
		if (size == 1) {
			first = null;
			last = null;
		} else {
			first = first.next;
			first.previous = null;
		}
		size--;
		return aux;
	}

	// remove and return the item from the end
	public Item removeLast() {
		if (size == 0) {
			throw new NoSuchElementException(
					"Cannot remove last element from an empty Deque.");
		}

		Item aux = last.item;
		if (size == 1) {
			first = null;
			last = null;
		} else {
			last = last.previous;
			last.next = null;
		}
		size--;
		return aux;
	}

	// return an iterator over items in order from front to end
	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item> {
		private Node current = first;

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException(
					"remove method not implemented.");
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException("no next element. ");
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

	// unit testing
	public static void main(String[] args) {

//		final Deque<Integer> emptyDeque = new Deque<Integer>();
//
//		Assert.assertNull(emptyDeque.first);
//		Assert.assertNull(emptyDeque.last);
//		Assert.assertEquals(0, emptyDeque.size);
//
//		// -----------------------------
//
//		final Deque<Integer> dequeAddFirstElement = new Deque<Integer>();
//		dequeAddFirstElement.addFirst(Integer.valueOf(12));
//
//		Assert.assertEquals(1, dequeAddFirstElement.size());
//		Assert.assertEquals(Integer.valueOf(12),
//				dequeAddFirstElement.first.item);
//		Assert.assertEquals(Integer.valueOf(12), dequeAddFirstElement.last.item);
//		Assert.assertNull(dequeAddFirstElement.first.next);
//		Assert.assertNull(dequeAddFirstElement.first.previous);
//
//		// -----------------------------
//
//		final Deque<Integer> dequeAddTwoFirstElements = new Deque<Integer>();
//		dequeAddTwoFirstElements.addFirst(Integer.valueOf(12));
//		dequeAddTwoFirstElements.addFirst(Integer.valueOf(13));
//
//		Assert.assertEquals(2, dequeAddTwoFirstElements.size);
//		Assert.assertEquals(Integer.valueOf(13),
//				dequeAddTwoFirstElements.first.item);
//		Assert.assertEquals(Integer.valueOf(12),
//				dequeAddTwoFirstElements.first.next.item);
//		Assert.assertNull(dequeAddFirstElement.first.previous);
//		Assert.assertEquals(Integer.valueOf(12),
//				dequeAddTwoFirstElements.last.item);
//		Assert.assertEquals(Integer.valueOf(13),
//				dequeAddTwoFirstElements.last.previous.item);
//		Assert.assertNull(dequeAddFirstElement.last.next);
//
//		// -----------------------------
//
//		final Deque<Integer> dequeAddTwoFirstOneLastElements = new Deque<Integer>();
//		dequeAddTwoFirstOneLastElements.addFirst(Integer.valueOf(12));
//		dequeAddTwoFirstOneLastElements.addFirst(Integer.valueOf(13));
//		dequeAddTwoFirstOneLastElements.addLast(Integer.valueOf(3));
//
//		Assert.assertEquals(3, dequeAddTwoFirstOneLastElements.size);
//		Assert.assertEquals(Integer.valueOf(13),
//				dequeAddTwoFirstOneLastElements.first.item);
//		Assert.assertEquals(Integer.valueOf(12),
//				dequeAddTwoFirstOneLastElements.first.next.item);
//		Assert.assertEquals(Integer.valueOf(3),
//				dequeAddTwoFirstOneLastElements.first.next.next.item);
//		Assert.assertEquals(Integer.valueOf(3),
//				dequeAddTwoFirstOneLastElements.last.item);
//		Assert.assertEquals(Integer.valueOf(12),
//				dequeAddTwoFirstOneLastElements.last.previous.item);
//		Assert.assertEquals(Integer.valueOf(13),
//				dequeAddTwoFirstOneLastElements.last.previous.previous.item);
//
//		// -----------------------------
//
//		Deque<Integer> dequeRemoveFirstElement = new Deque<Integer>();
//		dequeRemoveFirstElement.addFirst(Integer.valueOf(1));
//		Integer item = dequeRemoveFirstElement.removeFirst();
//
//		Assert.assertEquals(Integer.valueOf(1), item);
//		Assert.assertEquals(0, dequeRemoveFirstElement.size);
//		Assert.assertNull(dequeRemoveFirstElement.first);
//		Assert.assertNull(dequeRemoveFirstElement.last);
//
//		// -----------------------------
//
//		Deque<Integer> dequeLastRemove = new Deque<Integer>();
//		dequeLastRemove.addFirst(Integer.valueOf(2));
//		dequeLastRemove.addFirst(Integer.valueOf(3));
//		Integer item2 = dequeLastRemove.removeFirst();
//
//		Assert.assertEquals(Integer.valueOf(3), item2);
//		Assert.assertEquals(1, dequeLastRemove.size);
//		Assert.assertNotNull(dequeLastRemove.first);
//		Assert.assertNotNull(dequeLastRemove.last);
//		Assert.assertNull(dequeLastRemove.last.next);
//
//		// --------------------------------
//		Deque<Integer> deque = new Deque<Integer>();
//		deque.addLast(1);
//		deque.addFirst(2);
//		deque.addLast(3);
//		deque.addLast(4);
//		deque.addFirst(5);
//		for (Integer integer : deque) {
//			System.out.print(integer + " ");
//		}
//		System.out.println("  ");
//		Assert.assertEquals(Integer.valueOf(5), deque.removeFirst());
//		for (Integer integer : deque) {
//			System.out.print(integer + " ");
//		}
//		System.out.println("  ");
//		Assert.assertEquals(Integer.valueOf(2), deque.removeFirst());
//		for (Integer integer : deque) {
//			System.out.print(integer + " ");
//		}
//		System.out.println("  ");
//		Assert.assertEquals(Integer.valueOf(4), deque.removeLast());
//		for (Integer integer : deque) {
//			System.out.print(integer + " ");
//		}
//		System.out.println("  ");
//		Assert.assertEquals(Integer.valueOf(3), deque.removeLast());
//
//		for (Integer integer : deque) {
//			System.out.print(integer + " ");
//		}
//		System.out.println("  ");

	}
}

// Testing methods in Deque
// *-----------------------------------------------------------
// Running 16 total tests.
//
// Tests 1-6 make random calls to addFirst(), addLast(), removeFirst(),
// removeLast(), isEmpty(), and size(). The probabilities of each
// operation are (p1, p2, p3, p4, p5, p6), respectively.
//
// Test 1: Calls to addFirst(), addLast(), and size()
// * 5 random calls (0.4, 0.4, 0.0, 0.0, 0.0, 0.2)
// * 50 random calls (0.4, 0.4, 0.0, 0.0, 0.0, 0.2)
// * 500 random calls (0.4, 0.4, 0.0, 0.0, 0.0, 0.2)
// * 1000 random calls (0.4, 0.4, 0.0, 0.0, 0.0, 0.2)
// ==> passed
//
// Test 2: Calls to addFirst(), removeFirst(), and isEmpty()
// * 5 random calls (0.8, 0.0, 0.1, 0.0, 0.1, 0.0)
// * 50 random calls (0.8, 0.0, 0.1, 0.0, 0.1, 0.0)
// * 500 random calls (0.8, 0.0, 0.1, 0.0, 0.1, 0.0)
// * 1000 random calls (0.8, 0.0, 0.1, 0.0, 0.1, 0.0)
// * 5 random calls (0.1, 0.0, 0.8, 0.0, 0.1, 0.0)
// * 50 random calls (0.1, 0.0, 0.8, 0.0, 0.1, 0.0)
// * 500 random calls (0.1, 0.0, 0.8, 0.0, 0.1, 0.0)
// * 1000 random calls (0.1, 0.0, 0.8, 0.0, 0.1, 0.0)
// ==> passed
//
// Test 3: Calls to addFirst(), removeLast(), and isEmpty()
// * 5 random calls (0.8, 0.0, 0.0, 0.1, 0.1, 0.0)
// * 50 random calls (0.8, 0.0, 0.0, 0.1, 0.1, 0.0)
// * 500 random calls (0.8, 0.0, 0.0, 0.1, 0.1, 0.0)
// * 1000 random calls (0.8, 0.0, 0.0, 0.1, 0.1, 0.0)
// * 5 random calls (0.1, 0.0, 0.0, 0.8, 0.1, 0.0)
// * 50 random calls (0.1, 0.0, 0.0, 0.8, 0.1, 0.0)
// * 500 random calls (0.1, 0.0, 0.0, 0.8, 0.1, 0.0)
// * 1000 random calls (0.1, 0.0, 0.0, 0.8, 0.1, 0.0)
// ==> passed
//
// Test 4: Calls to addLast(), removeLast(), and isEmpty()
// * 5 random calls (0.0, 0.8, 0.0, 0.1, 0.1, 0.0)
// * 50 random calls (0.0, 0.8, 0.0, 0.1, 0.1, 0.0)
// * 500 random calls (0.0, 0.8, 0.0, 0.1, 0.1, 0.0)
// * 1000 random calls (0.0, 0.8, 0.0, 0.1, 0.1, 0.0)
// * 5 random calls (0.0, 0.1, 0.0, 0.8, 0.1, 0.0)
// * 50 random calls (0.0, 0.1, 0.0, 0.8, 0.1, 0.0)
// * 500 random calls (0.0, 0.1, 0.0, 0.8, 0.1, 0.0)
// * 1000 random calls (0.0, 0.1, 0.0, 0.8, 0.1, 0.0)
// ==> passed
//
// Test 5: Calls to addLast(), removeFirst(), and isEmpty()
// * 5 random calls (0.0, 0.8, 0.1, 0.0, 0.1, 0.0)
// * 50 random calls (0.0, 0.8, 0.1, 0.0, 0.1, 0.0)
// * 500 random calls (0.0, 0.8, 0.1, 0.0, 0.1, 0.0)
// * 1000 random calls (0.0, 0.8, 0.1, 0.0, 0.1, 0.0)
// * 5 random calls (0.0, 0.1, 0.8, 0.0, 0.1, 0.0)
// * 50 random calls (0.0, 0.1, 0.8, 0.0, 0.1, 0.0)
// * 500 random calls (0.0, 0.1, 0.8, 0.0, 0.1, 0.0)
// * 1000 random calls (0.0, 0.1, 0.8, 0.0, 0.1, 0.0)
// ==> passed
//
// Test 6: Calls to addFirst(), addLast(), removeFirst(),
// removeLast(), isEmpty(), and size().
// * 5 random calls (0.3, 0.3, 0.1, 0.1, 0.1, 0.1)
// * 50 random calls (0.3, 0.3, 0.1, 0.1, 0.1, 0.1)
// * 500 random calls (0.3, 0.3, 0.1, 0.1, 0.1, 0.1)
// * 1000 random calls (0.3, 0.3, 0.1, 0.1, 0.1, 0.1)
// * 5 random calls (0.1, 0.1, 0.3, 0.3, 0.1, 0.1)
// * 50 random calls (0.1, 0.1, 0.3, 0.3, 0.1, 0.1)
// * 500 random calls (0.1, 0.1, 0.3, 0.3, 0.1, 0.1)
// * 1000 random calls (0.1, 0.1, 0.3, 0.3, 0.1, 0.1)
// ==> passed
//
// Test 7: Removing from an empty deque
// * removeFirst()
// * removeLast()
// ==> passed
//
// Test 8: Create multiple deque objects at the same time
// ==> passed
//
// Test 9: Check iterator() after calls only to addFirst()
// ==> passed
//
// Test 10: Check iterator() after intermixed calls to addFirst(), addLast(),
// removeFirst(), and removeLast()
// - student length = 3
// - reference length = 2
// - iterator failed after applying operation 8
// - sequence of dequeue operations was:
// deque.addLast(1)
// deque.addFirst(2)
// deque.addLast(3)
// deque.addLast(4)
// deque.addFirst(5)
// deque.removeFirst() ==> 5
// deque.removeFirst() ==> 2
// deque.removeLast() ==> 4
//
// ==> FAILED
//
// Test 11: Create two nested iterators to same deque
// * N = 10
// * N = 1000
// ==> passed
//
// Test 12: Create two parallel iterators to same deque
// * N = 10
// * N = 1000
// ==> passed
//
// Test 13: Create Deque objects of different parameterized types
// ==> passed
//
// Test 14: Check that addFirst() and addLast() each throw a
// NullPointerException
// when inserting null items
// ==> passed
//
// Test 15: Check that remove() and next() throw the specified exceptions in
// iterator()
// ==> passed
//
// Test 16: Check iterator() when Deque is empty
// ==> passed
//
//
// Total: 15/16 tests passed!