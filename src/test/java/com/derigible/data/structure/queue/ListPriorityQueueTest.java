package com.derigible.data.structure.queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ListPriorityQueueTest {

	static ListPriorityQueue<Integer> ints = new ListPriorityQueue<Integer>(50);

	@Before
	public void setUp() throws Exception {
		for(int i = 0; i < 10; i++){
			ints.push(i);
		}
	}

	@After
	public void tearDown() throws Exception {
		ints = new ListPriorityQueue<Integer>(50);
	}

	@Test
	public final void testPull() {
		assertEquals(ints.pull(), (Integer) 9);
		assertEquals(ints.pull(), (Integer) 8);
		assertEquals(ints.pull(), (Integer) 7);
		assertEquals(ints.pull(), (Integer) 6);
		assertEquals(ints.pull(), (Integer) 5);
		assertEquals(ints.pull(), (Integer) 4);
		assertEquals(ints.pull(), (Integer) 3);
		assertEquals(ints.pull(), (Integer) 2);
		assertEquals(ints.pull(), (Integer) 1);
		assertEquals(ints.pull(), (Integer) 0);
		assertEquals(ints.pull(), null);
	}

	@Test
	public final void testRemoveE() {
		ints.push(54);
		ints.remove(0);
		assertFalse(ints.pull() != 54);
		ints.push(60);
		ints.push(6);
		ints.push(62);
		ints.push(63);
		ints.push(64);
		ints.push(65);
		ints.push(66);
		ints.remove(64);
		boolean removed = false;
		for(Integer i : ints){
			if(i == 64){
				removed = true;
			}
		}
		assertFalse(removed);
	}


	@Test
	public final void testIsEmpty() {
		ints = new ListPriorityQueue<Integer>(50);
		assertFalse(!ints.isEmpty());
	}

	@Test
	public final void testSize() {
		assertEquals(ints.size(), 10);
	}

	@Test
	public final void testPush() {
		ints.push(11);
		assertEquals(ints.size(), 11);
		assertEquals(ints.pull(), (Integer)(11));
		for(int i = 0; i < 300; i++){
			ints.push(i);
		}
		assertEquals(ints.size(), 310);
	}

	@Test
	public final void testPeek() {
		assertEquals(ints.peek(),(Integer) 9);
		ints.push(10);
		assertEquals(ints.peek(),(Integer) 10);
	}

	@Test
	public final void testHasNext() {
		Iterator next = ints.iterator();
		assertFalse(!next.hasNext());
		for(int i = 0; i < 11; i++){
			next.remove();
		}
		assertEquals(0, ints.getLast());
		assertFalse(next.hasNext());
	}

	@Test
	public final void testNext() {
		Iterator next = ints.iterator();
		assertEquals(next.next(), 9);
		assertEquals(next.next(), 8);
		assertEquals(next.next(), 7);
		assertEquals(next.next(), 6);
		assertEquals(next.next(), 5);
		assertEquals(next.next(), 4);
	}

	@Test
	public final void testIterator() {
		int count = 0;
		for(int i : ints){
			count++;
		}
		assertFalse(count != 10);
	}

	@Test
	public final void testRemove() {
		ints.push(54);
		ints.remove(54);
		assertFalse(ints.pull() != 9);
		ints.push(60);
		ints.push(6);
		ints.push(62);
		ints.push(63);
		ints.push(64);
		ints.push(65);
		ints.push(66);
		ints.remove(64);
		boolean removed = false;
		for(Integer i : ints){
			if(i == 64){
				removed = true;
			}
		}
		assertFalse(removed);
	}

}
