package com.alcuvi.algorithms;

import junit.framework.Assert;

import org.junit.Test;

public class DoubleStackWithMaxTest {

	@Test
	public void test1() {
		DoubleStackWithMax stack = new DoubleStackWithMax(200);
		stack.push(13D);
		Assert.assertEquals(13D, stack.pop());
	}
	
	@Test
	public void test2() {
		DoubleStackWithMax stack = new DoubleStackWithMax(200);
		stack.push(13D);
		stack.push(20D);
		stack.push(15D);
		
		Assert.assertEquals(20D, stack.max());
		Assert.assertEquals(15D, stack.pop());
		Assert.assertEquals(20D, stack.max());
		Assert.assertEquals(20D, stack.pop());
		Assert.assertEquals(13D, stack.max());
		Assert.assertEquals(13D, stack.pop());
		
	}
	
	
}
