package com.alcuvi.algorithms;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

import com.alcuvi.algorithms.unionfind.QuickUnionBalancedUF;
import com.alcuvi.algorithms.unionfind.QuickUnionUF;

public class CostCheckerTest {

	// @Test
	// public void tescaCase1() {
	// for (int i = 0; i < 100; i++) {
	// prueba(i);
	// }
	// }
	//
	// public void prueba(int n) {
	// int sum = 0;
	// for (int i = n; i > 0; i--) {
	// for (int j = 0; j < i; j++) {
	// sum++;
	// }
	// }
	//
	// System.out.println("SUMA(" + n + "): " + sum);
	// }

	// @Test
	// public void week2Stacks() {
	// LinkedStack<Integer> stack = new LinkedStack<Integer>();
	// stack.push(0);
	// System.out.println(stack.pop());
	// stack.push(1);
	// System.out.println(stack.pop());
	// stack.push(2);
	// System.out.println(stack.pop());
	// stack.push(3);
	// System.out.println(stack.pop());
	// stack.push(4);
	// System.out.println(stack.pop());
	// stack.push(5);
	// System.out.println(stack.pop());
	// stack.push(6);
	// System.out.println(stack.pop());
	// stack.push(7);
	// System.out.println(stack.pop());
	// stack.push(8);
	// System.out.println(stack.pop());
	// stack.push(9);
	// System.out.println(stack.pop());
	// }

	// @Test
	// public void week2Quees() {
	// Queue<Integer> queue = new LinkedList<Integer>();
	// queue.add(0);
	// System.out.println(queue.poll());
	// queue.add(1);
	// System.out.println(queue.poll());
	// queue.add(2);
	// queue.add(3);
	// queue.add(4);
	// System.out.println(queue.poll());
	// queue.add(5);
	// queue.add(6);
	// queue.add(7);
	// queue.add(8);
	// System.out.println(queue.poll());
	// queue.add(9);
	// }
	// @Test
	// public void testQuickUnionBalancedUF(){
	// QuickUnionBalancedUF qu = new QuickUnionBalancedUF(10);
	// qu.union(0, 1);
	// qu.union(0, 2);
	// qu.union(0, 3);
	// qu.union(0, 4);
	// qu.union(0, 5);
	// qu.union(7, 6);
	// qu.union(8, 9);
	// qu.union(8, 7);
	// qu.union(3, 6);
	// qu.print();
	// }
	//
	// @Test
	// public void QuickUnionTest() {
	// QuickUnionUF qu = new QuickUnionUF(10);
	// qu.union(4, 8);
	// qu.union(4, 7);
	// qu.union(1, 8);
	// qu.union(0, 1);
	// qu.union(0, 5);
	// qu.union(9, 4);
	// qu.print();
	// }

	@Test
	public void testWeights() {
		QuickUnionBalancedUF qu = new QuickUnionBalancedUF(10);
		qu.union(3, 4);
		qu.union(5, 6);
		qu.union(9, 7);
		qu.union(3, 9);
		qu.union(1, 5);
		qu.union(2, 6);
		qu.union(6, 0);
		qu.union(3, 0);
		qu.union(3, 8);
		qu.print();
	}

	// @Test
	// public void testExample(){
	// int sum = 0;
	// for (int i = 1; i <= 10; i++)
	// for (int j = 1; j <= i*i; j++)
	// for (int k = 1; k <= j; k++)
	// sum++;
	// }

}
