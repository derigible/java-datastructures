package com.derigible.data.structure.queue;

import java.util.Iterator;

import com.derigible.data.structure.abstracts.Pushable;

/**
 * Created by marcphillips on 5/20/2014.
 * Acts as a List and a Queue, in that you can
 * iterate over all instances withou losing any
 * information, or you can treat it like a Queue,
 * where the pull function takes the first item
 * placed in the list and returns it with removal
 * from the queue.
 */
public class ListQueue<E> implements Pushable<E>{
	@SuppressWarnings("unchecked")
	private E[] list = (E[]) new Object[10];
	private int head = 0;
	private int tail = 0;
	private int current = 0;
	private double capacity = .25;

	@Override
	public void push(E item){
		if(tail == list.length ||
				((double)(tail - head)/(double)list.length < capacity
						&& list.length > 10)){
			resizeArray(list.length * 2);
		}
		list[tail++] = item;
	}

	//    int counted = 0;
	private void resizeArray(int i){
		@SuppressWarnings("unchecked")
		E[] temp = (E[]) new Object[i];
		int pointer = 0;
		for(int j = 0; j < tail - head; j++){
			temp[j] = list[j + head];
			pointer = j;
		}
		//TODO check if copyOfRange and then copyOf is faster than this method
		list = temp;
		head = 0;
		tail = pointer + 1;
		//        System.out.println();
		//        System.out.println("REsize #: " + ++counted + " Array Length: " + list.length);
		//        System.out.println();
	}

	/**
	 * Typical queue behavior of pulling the first item
	 * from the list and removing it.
	 *
	 * @return
	 */
	@Override
	public E pull(){
		E val = null;
		if(head == tail){
			head = 0;
			tail = 0;
			current = 0;
			return val;
		} else {
			val = list[head++];
		}
		if((double)(tail - head)/(double)list.length < capacity && list.length > 10){
			resizeArray(Math.max((int)Math.ceil(list.length * capacity), 10));
		}
		current = head;
		return val;
	}

	@Override
	public void remove(E item){
		if(tail == 0){
			return;
		}
		for(int i = 0; i < tail; i++){
			if(list[i].equals(item)){
				for(int j = i; j <  tail -1; j++){
					list[j] = list[j +1];
				}
				list[tail--] = null;
				return;
			}
		}
	}

	@Override
	public E peek(){
		return list[head];
	}

	@Override
	public int size(){
		return tail - head;
	}

	@Override
	public Iterator<E> iterator(){
		return this;
	}

	@Override
	public boolean hasNext(){
		boolean result = current != tail;
		if(!result){
			current = head;
		}
		return result;
	}

	@Override
	public E next(){
		return list[current++];
	}

	@Override
	public void remove() {
		if(tail == 0){
			return;
		}
		list[--tail] = null;
	}
}
