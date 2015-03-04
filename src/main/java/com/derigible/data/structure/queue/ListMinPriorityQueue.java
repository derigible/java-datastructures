package com.derigible.data.structure.queue;

/**
 * Created by marcphillips on 5/21/2014.
 */
public class ListMinPriorityQueue<E extends Comparable<E>> extends ListPriorityQueue<E>{

	@SuppressWarnings("unchecked")
	public ListMinPriorityQueue(){
		list = (E[]) new Comparable[10];
	}

	@SuppressWarnings("unchecked")
	public ListMinPriorityQueue(int capacity){
		list = (E[]) new Comparable[capacity + 1];
	}

	public ListMinPriorityQueue(E[] given){
		list = given;
	}

	@Override
	public E pull(){
		E val = super.pull();
		current = last;
		return val;
	}

	@Override
	public void remove(E item){
		super.remove(item);
		current = last;
	}

	@Override
	public void remove() {
		super.remove();
		current = last;
	}

	@Override
	public void push(E item){
		super.push(item);
		current = last;
	}

	@Override
	void swim(int key){
		while(key > 1 && greater(key / 2, key)){
			exch(key, key / 2);
			key = key / 2;
		}
	}

	@Override
	void sink(int key){
		while(2*key <= last){
			int j = 2*key;
			if(j < last && greater(j, j + 1)){
				j++;
			}
			if(!greater(key, j)){
				break;
			}
			exch(key, j);
			key = j;
		}
	}

	private boolean greater(int i, int key){
		return list[i].compareTo(list[key]) > 0;
	}

	@Override
	public boolean hasNext(){
		if(current == 1 && last <= 0){
			return false;
		} else if(current < 1){
			current = last;
			return false;
		}
		return true;
	}

	@Override
	public E next(){
		return current < 0 ? null: sorted[current--];
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(E val : this){
			sb.append(val.toString());
			sb.append(" | ");
		}
		sb.delete(sb.length() - 3, sb.length() - 1);
		return sb.toString();
	}
}
