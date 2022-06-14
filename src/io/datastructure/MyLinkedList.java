package io.datastructure;

import java.util.NoSuchElementException;

public class MyLinkedList <T> implements Iterable<T> {
	private Node first;
	private Node last;

	private int size;

	public void add(T value) {
		Node node = new Node(value);
		if(first == null) {
			first = node;
			last = node;
			size += 1;
			return;
		}
		
		last.next = node;
		node.previous = last;
		last = node;
		size += 1;
	}

	public void addFirst(T value) {
		Node node = new Node(value);
		if(first == null) {
			first = node;
			last = node;
			size += 1;
			return;
		}
		first.previous = node;
		node.next = first;
		first = node;
		size += 1;
	}

	public void addLast(T value) {
		this.add(value);
	}

	public void add(int index, T value) {
		if(index > size - 1 || index < 0) throw new IndexOutOfBoundsException();
		Node node = new Node(value);
		Node next = first;
		int count = 0;
		while (next.hasNext() && count != index) {
			next = next.next;
			count++;
		}
		node.next = next;
		if(next.previous != null){
			node.previous = next.previous;
			next.previous.next = node;
			next.previous = node;
		}else{
			first = node;
		}
		size += 1;
	}

	public void replace(int index, T value) {
		if(index > size - 1 || index < 0) throw new IndexOutOfBoundsException();
		Node next = first;
		int count = 0;
		while (next.hasNext() && count != index) {
			next = next.next;
			count++;
		}
		next.value = value;
	}

	public void remove() {
		if(last == null) {
			return;
		}
		last = last.previous;
		Node next = first;
		while (next.hasNext()) {
			next = next.next;
		}
		next.previous.next = null;
		size -= 1;
	}

	public void remove(int index) {
		Node next = first;
		int count = 0;
		while (next.hasNext() && count != index) {
			next = next.next;
			count++;
		}
		next.previous.next = next.next;
		next.next.previous = next.previous;
		size -= 1;
	}

	public void remove(T value) {
		Node next = first;
		while (next.hasNext() && next.value != value) {
			next = next.next;
		}
		next.previous.next = next.next;
		next.next.previous = next.previous;
		size -= 1;
	}

	public boolean contains(T value){
		Node next = first;
		while (next.hasNext()) {
			if(next.getValue().equals(value)){
				return true;
			}
			next = next.next;
		}
		return false;
	}

	public int indexOf(T value){
		Node next = first;
		int index = 0;
		while (next != null) {
			if(next.getValue().equals(value)){
				return index;
			}
			next = next.next;
			index++;
		}
		return -1;
	}

	public Node get(int index){
		int count = 0;
		Node next = first;
		while(count != index){
			next = next.next;
		}
		return next;
	}
	
	public String toString() {
		if(first == null) {
			return "";
		}
		StringBuilder value = new StringBuilder("");
		Node next = first;
		do {
			value.append(next.value.toString());
			value.append(" ");
			next = next.next;
		}while(next != null);
		
		return value.toString();
	}

	public int getSize() {
		return size;
	}

	@Override
	public java.util.Iterator<T> iterator() {
		return new Iterator(this);
	}

	private class Iterator implements java.util.Iterator<T> {
		private Node current;
		private Iterator(MyLinkedList<T> list){
			current = list.first;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if(current != null) {
				Node old = current;
				current = current.getNext();
				return old.getValue();
			}
			throw new NoSuchElementException();
		}
	}
	public class Node{
		private Node next;
		private Node previous;
		private T value;
		
		private Node(T value){
			this.value = value;
		}
		
		boolean hasNext(){
			return this.next != null;
		}

		public Node getNext() {
			return next;
		}

		public T getValue() {
			return value;
		}
	}
}
