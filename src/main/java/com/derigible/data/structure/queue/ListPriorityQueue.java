package com.derigible.data.structure.queue;

import java.util.Arrays;
import java.util.Iterator;

import com.derigible.data.structure.abstracts.Pushable;

/**
 * Created by marcphillips on 5/21/2014.
 */

public class ListPriorityQueue<E extends Comparable<E>> implements Pushable<E>{

	protected E[] list = null;
	protected E[] sorted = null;
	protected int last = 0;
	protected int current = 1;

	@SuppressWarnings("unchecked")
	public ListPriorityQueue(){
		list = (E[]) new Comparable[10];
	}

	@SuppressWarnings("unchecked")
	public ListPriorityQueue(int capacity){
		list = (E[]) new Comparable[capacity + 1];
	}

	public ListPriorityQueue(E[] given){
		list = given;
	}

	@Override
	public E pull(){
		if(last == 0){
			return null;
		}
		E max = list[1];
		exch(1, last--);
		sink(1);
		list[last + 1] = null;
		return max;
	}

	@Override
	public void remove(E item){
		for(int i = 1; i <= last; i++){
			if(item.compareTo(list[i]) == 0){
				exch(i,last--);
				sink(i);
				return;
			}
		}
	}

	void swim(int key){
		while(key > 1 && less(key / 2, key)){
			exch(key, key / 2);
			key = key / 2;
		}
	}

	void exch(int key, int i){
		E temp = list[i];
		list[i] = list[key];
		list[key] = temp;
	}

	private boolean less(int i, int key){
		return list[i].compareTo(list[key]) < 0;
	}

	void sink(int key){
		while(2*key <= last){
			int j = 2*key;
			if(j < last && less(j, j +1)){
				j++;
			}
			if(!less(key, j)){
				break;
			}
			exch(key, j);
			key = j;
		}
	}

	//    int counted = 0;
	private void resizeArray(int i){
		list = Arrays.copyOf(list, i);
	}

	public boolean isEmpty(){
		return last == 0;
	}

	Comparable<E>[] sort(Comparable<E>[] vals, int last){
		for(int i = last/2; i >= 1; i--){
			sinkSort(vals, i, last);
		}
		while(last > 1){
			exchSort(vals, 1, last);
			sinkSort(vals, 1, --last);
		}
		return vals;
	}

	@SuppressWarnings("unchecked")
	private boolean lessSort(Comparable<E>[] vals, int i, int j){
		return vals[j].compareTo((E) vals[i]) < 0;
	}

	private void exchSort(Comparable<E>[] vals, int key, int length){
		Comparable<E> temp = vals[length];
		vals[length] = vals[key];
		vals[key] = temp;
	}

	private void sinkSort(Comparable<E>[] vals, int key, int length){
		while(2*key <= length){
			int j = 2*key;
			if(j < length && lessSort(vals, j, j + 1)){
				j++;
			}
			if(!lessSort(vals, key, j)){
				break;
			}
			exchSort(vals, key, j);
			key = j;
		}
	}

	@Override
	public int size(){
		return last;
	}

	@Override
	public void push(E item){
		if(last + 1 == list.length){
			resizeArray(list.length * 2);
		}
		list[++last] = item;
		swim(last);
	}

	@Override
	public E peek(){
		return list[1];
	}

	@Override
	@SuppressWarnings("unchecked")
	public Iterator<E> iterator(){
		sorted = (E[]) sort(this.list.clone(), last);
		return this;
	}

	@Override
	public boolean hasNext(){
		if(current == 1 && last == 0){
			return false;
		} else if(current > last){
			current = 1;
			return false;
		}
		return true;
	}

	@Override
	public E next(){
		return current > last ? null : sorted[current++];
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

	@Override
	public void remove() {
		if(last > 0){
			list[last--] = null;
		}
	}

	public int getLast(){
		return last;
	}
}
