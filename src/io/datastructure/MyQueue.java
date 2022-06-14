package io.datastructure;


public class MyQueue<T> {
	
	private Node first;
	private Node last;
	private int size = 0;
	private int counter = 0;
	
	MyQueue(){
		
	}
	
	MyQueue(int size){
		this.size = size;
	}
	
	void add(T value) {
		if(size != 0 && counter == size) {
			throw new IllegalArgumentException();
		}
		Node node = new Node(value);
		if(first == null) {
			first = last = node;
			return;
		}
		node.previous = last;
		last.next = node;
		last = node;
		counter++;
	}
	
	boolean offer(T value) {
		try {
			add(value);
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	
	T poll() {
		try{
			return remove();
		}catch(Exception e) {
			return null;
		}
	}
	
	T remove() {
		if(last == null) {
			throw new IllegalArgumentException();
		}
		T value = last.value;
		last = last.previous;
		if(last != null && last.hasNext()) {
			last.next = null;	
		}
		counter--;
		return value;
	}
	
	boolean contains(T value) {
		Node current = first;
		do{
			if(current.value.equals(value)) {
				return true;
			}
			current = current.next;
		}while(current != null);
		return false;
	}
	
	public String toString() {
		if(first == null) {
			return "";
		}
		StringBuilder value = new StringBuilder("[");
		Node next = first;
		do {
			value.append(next.value.toString());
			value.append(", ");
			next = next.next;
		}while(next != null);
		value.append("]");
		return value.toString();
	}
	
	private class Node{
		Node next;
		Node previous;
		T value;
		
		Node(T value){
			this.value = value;
		}
		
		boolean hasNext(){
			return next != null;
		}
	}
}
