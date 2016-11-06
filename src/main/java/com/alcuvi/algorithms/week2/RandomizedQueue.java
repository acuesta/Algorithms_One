package com.alcuvi.algorithms.week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Randomized queue. A randomized queue is similar to a stack or queue, 
 * except that the item removed is chosen uniformly at random from items in the data structure. 
 * @param <Item> element type.
 * 
 * @author alcuvi
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

	private static final int RESIZE_FACTOR = 2;

	private int size;

	@SuppressWarnings("unchecked")
	private Item[] items = (Item[]) new Object[2];

	// construct an empty randomized queue
	public RandomizedQueue() {
		this.size = 0;
	}

	// is the queue empty?
	public boolean isEmpty() {
		return this.size == 0;
	}

	// return the number of items on the queue
	public int size() {
		return this.size;
	}

	// add the item
	public void enqueue(final Item item) {
		if (item == null) {
			throw new NullPointerException("Cannot add NULL elements.");
		}

		if (this.items.length == this.size) {
			this.resize(this.size * RESIZE_FACTOR);
		}

		this.items[this.size] = item;
		this.size++;
	}

	// resize the underlying array holding the elements
	private void resize(int capacity) {

		@SuppressWarnings("unchecked")
		Item[] temp = (Item[]) new Object[capacity];

		for (int i = 0; i < this.size; i++) {
			temp[i] = this.items[i];
		}
		this.items = temp;
	}

	// remove and return a random item
	public Item dequeue() {
		if (this.isEmpty()) {
			throw new NoSuchElementException(
					"Cannot dequeue from an empty queue.");
		}

		int indexToDelete = StdRandom.uniform(this.size);
		Item itemToDelete = this.items[indexToDelete];
		this.items[indexToDelete] = this.items[this.size - 1];
		this.items[this.size - 1] = null;
		this.size--;

		if (this.size > 0 && this.size <= this.items.length / 4) {
			this.resize(this.items.length / RESIZE_FACTOR);
		}

		return itemToDelete;
	}

	// return (but do not remove) a random item
	public Item sample() {
		if (this.isEmpty()) {
			throw new NoSuchElementException(
					"Cannot get sample from an empty queue.");
		}
		return this.items[StdRandom.uniform(this.size)];
	}

	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}

	private class RandomizedQueueIterator implements Iterator<Item> {

		int current;
		Item[] randomOrderItems;;

		@SuppressWarnings("unchecked")
		public RandomizedQueueIterator() {
			randomOrderItems = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
            	randomOrderItems[i] = items[i];
            }
            StdRandom.shuffle(randomOrderItems);
            current = 0;
		}

		@Override
		public boolean hasNext() {
			return current < size;
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException("No elements to iterate.");
			}
			Item item = randomOrderItems[current];
			current++;
			return item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("Remove operation not supported.");
		}
	}

    /**
     * Some testing
     */
    public static void main(String[] args) {
    }
}

// Testing methods in RandomizedQueue
// *-----------------------------------------------------------
// Running 18 total tests.
//
// Tests 1-4 make random calls to enqueue(), dequeue(), sample(),
// isEmpty(), and size(). The probabilities of each operation are
// (p1, p2, p3, p4, p5), respectively.
//
// Test 1: Calls to enqueue() and size().
// * 5 random calls (0.8, 0.0, 0.0, 0.0, 0.2)
// * 50 random calls (0.8, 0.0, 0.0, 0.0, 0.2)
// * 500 random calls (0.8, 0.0, 0.0, 0.0, 0.2)
// * 1000 random calls (0.8, 0.0, 0.0, 0.0, 0.2)
// ==> passed
//
// Test 2: Calls to enqueue() and dequeue().
// * 5 random calls (0.7, 0.1, 0.0, 0.1, 0.1)
// * 50 random calls (0.7, 0.1, 0.0, 0.1, 0.1)
// * 500 random calls (0.7, 0.1, 0.0, 0.1, 0.1)
// * 1000 random calls (0.7, 0.1, 0.0, 0.1, 0.1)
// * 5 random calls (0.1, 0.7, 0.0, 0.1, 0.1)
// * 50 random calls (0.1, 0.7, 0.0, 0.1, 0.1)
// * 500 random calls (0.1, 0.7, 0.0, 0.1, 0.1)
// * 1000 random calls (0.1, 0.7, 0.0, 0.1, 0.1)
// ==> passed
//
// Test 3: Calls to enqueue(), sample(), and size().
// * 5 random calls (0.8, 0.0, 0.1, 0.0, 0.1)
// * 50 random calls (0.8, 0.0, 0.1, 0.0, 0.1)
// * 500 random calls (0.8, 0.0, 0.1, 0.0, 0.1)
// * 1000 random calls (0.8, 0.0, 0.1, 0.0, 0.1)
// * 5 random calls (0.1, 0.0, 0.8, 0.0, 0.1)
// * 50 random calls (0.1, 0.0, 0.8, 0.0, 0.1)
// * 500 random calls (0.1, 0.0, 0.8, 0.0, 0.1)
// * 1000 random calls (0.1, 0.0, 0.8, 0.0, 0.1)
// ==> passed
//
// Test 4: Calls to enqueue(), dequeue(), sample(), isEmpty(), and size().
// * 5 random calls (0.6, 0.1, 0.1, 0.1, 0.1)
// * 50 random calls (0.6, 0.1, 0.1, 0.1, 0.1)
// * 500 random calls (0.6, 0.1, 0.1, 0.1, 0.1)
// * 1000 random calls (0.6, 0.1, 0.1, 0.1, 0.1)
// * 5 random calls (0.1, 0.1, 0.6, 0.1, 0.1)
// * 50 random calls (0.1, 0.1, 0.6, 0.1, 0.1)
// * 500 random calls (0.1, 0.1, 0.6, 0.1, 0.1)
// * 1000 random calls (0.1, 0.1, 0.6, 0.1, 0.1)
// ==> passed
//
// Test 5: dequeue() and sample() from an empty randomized queue
// * dequeue()
// * sample()
// ==> passed
//
// Test 6: Create multiple randomized queue objects at the same time
// ==> passed
//
// Test 7: Check that iterator() returns correct items after a sequence of
// enqueue() operations
// ==> passed
//
// Test 8: Check that iterator() returns correct items after sequence of
// enqueue()
// and dequeue() operations
// ==> passed
//
// Test 9: Create two nested iterators over same randomized queue
// * N = 10
// * N = 1000
// ==> passed
//
// Test 10: Create two parallel iterators over same randomized queue
// * N = 10
// * N = 1000
// ==> passed
//
// Test 11: Create two iterators over different randomized queues
// ==> passed
//
// Test 12: Create RandomizedQueue objects of different parameterized types
// ==> passed
//
// Test 13: Check randomness of sample() by enqueueing strings, repeatedly
// calling
// sample(), and counting the frequency of each value.
// * Enqueue strings A to C and sampling 3000 times
// * Enqueue strings A to E and sampling 5000 times
// * Enqueue strings A to H and sampling 8000 times
// * Enqueue strings A to J and sampling 10000 times
// ==> passed
//
// Test 14: Check randomness of dequeue() by enqueueing items, repeatedly
// calling
// dequeue() until a specific enqueued string appears.
// * Enqueue strings A to C and call dequeue() until C is dequeued; repeat 3000
// times
// * Enqueue strings A to E and call dequeue() until A is dequeued; repeat 5000
// times
// * Enqueue strings A to H and call dequeue() until F is dequeued; repeat 8000
// times
// * Enqueue strings A to J and call dequeue() until G is dequeued; repeat 10000
// times
// ==> passed
//
// Test 15: Check randomness of iterator() by enqueueing strings, getting an
// iterator()
// and repeatedly calling next() until a specific enqueued string appears.
// * Enqueue strings A to C, create iterator(), and call next() until B is
// returned;
// Repeat 3000 times
// * Enqueue strings A to E, create iterator(), and call next() until E is
// returned;
// Repeat 5000 times
// * Enqueue strings A to H, create iterator(), and call next() until G is
// returned;
// Repeat 8000 times
// * Enqueue strings A to J, create iterator(), and call next() until D is
// returned;
// Repeat 10000 times
// ==> passed
//
// Test 16: Check that NullPointerException is thrown when inserting null items
// ==> passed
//
// Test 17: Check that remove() and next() throw the specified exceptions in
// iterator()
// ==> passed
//
// Test 18: Check iterator() when RandomizedQueue is empty
// ==> passed
//
//
// Total: 18/18 tests passed!
