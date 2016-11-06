package com.alcuvi.algorithms;

import junit.framework.Assert;

import org.junit.Test;

public class QueueWithTwoStacksTest {

	
	@Test
	public void test1() {
		
		Queue queue = new QueueByTwoStacks();
		queue.enqueue(20D);
		queue.enqueue(13D);
		queue.enqueue(15D);
		Assert.assertEquals(20D,queue.dequeue());
		Assert.assertEquals(13D,queue.dequeue());
		Assert.assertEquals(15D,queue.dequeue());
	}
	
}
