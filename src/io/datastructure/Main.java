package io.datastructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Main {
	public static void main(String[] args) {
		testMyHashMap();
//		testLinkedList();
	}

	private static void testLinkedList(){
		MyLinkedList<String> myList = new MyLinkedList<>();
		myList.add("first value");
		myList.add("Second Value");
		myList.add("Third Value");
		myList.add("Forth Value");

//		myList.remove();
		myList.add(1, "Second Value real one");
		myList.remove(1);
		myList.remove("Second Value");
		myList.replace(1, "what a fucking mess");

		for (Iterator<String> iterator = myList.iterator(); iterator.hasNext(); ){
            System.out.println(iterator.next());
        }
		System.out.println(myList.toString());

	}

	private static void testArrayList(){
		MyArrayList<String> myList = new MyArrayList();
		myList.add("First Value");
		myList.add("Second Value");
		myList.add("Third Value");
		myList.add("Forth Value");

		myList.remove(2);
		myList.remove("Forth Value");

		System.out.println(myList);
	}

	private static void testQueue(){
		MyQueue<String> myQueue = new MyQueue<>();

		myQueue.add("First Value");
		myQueue.offer("Second Value");
		System.out.println(myQueue.contains("Third Value"));
		myQueue.add("Third Value");
		System.out.println(myQueue.contains("Third Value"));
		System.out.println(myQueue);
		myQueue.remove();
		myQueue.poll();
		System.out.println(myQueue);
		myQueue.remove();
		myQueue.remove();
	}

	private static void testBinarySearchTree(){
		BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree();
		BinarySearchTree<Integer> copy = new BinarySearchTree<>();

//		binarySearchTree.add(12);
//		binarySearchTree.add(3);
//		binarySearchTree.add(8);
//		binarySearchTree.add(14);
//		binarySearchTree.add(18);
//		binarySearchTree.add(6);

		binarySearchTree.add(6);
		binarySearchTree.add(4);
		binarySearchTree.add(8);
		binarySearchTree.add(3);
		binarySearchTree.add(5);
		binarySearchTree.add(7);
		binarySearchTree.add(9);

		binarySearchTree.traversPreOrder(value -> System.out.print(value + " "));
		System.out.println();
		binarySearchTree.traversInOrder(value -> System.out.print(value + " "));
		System.out.println();
		binarySearchTree.traversPostOrder(value -> System.out.print(value + " "));
		System.out.println();
		binarySearchTree.traversBreadth(value -> System.out.print(value + " "));
		System.out.println();
		binarySearchTree.traversPreOrder(copy::add);
		copy.traversInOrder(value -> System.out.print(value + " "));
		System.out.println();
		System.out.println(binarySearchTree.search(6));
		System.out.println(binarySearchTree.search(12));
		binarySearchTree.delete(6);
		System.out.println(binarySearchTree.search(6));
		binarySearchTree.traversPreOrder(value -> System.out.print(value + " "));
		binarySearchTree.delete(3);
		System.out.println(binarySearchTree.search(3));
		binarySearchTree.traversPreOrder(value -> System.out.print(value + " "));
		binarySearchTree.delete(4);
		System.out.println(binarySearchTree.search(4));
		binarySearchTree.traversPreOrder(value -> System.out.print(value + " "));
	}

	private static void testMyHashMap(){
		MyHashMap<String, Integer> myHashMap = new MyHashMap<>();
		myHashMap.add("Age", 10);
		myHashMap.add("Age", 20);
		myHashMap.add("Date", 200);
		myHashMap.add("Date", 300);
		myHashMap.add("Date", 400);
		myHashMap.add("What", 400);
		myHashMap.add("Hhhh", 300);
		System.out.println(myHashMap.getSize());
		for (Iterator<MyHashMap<String, Integer>.Entry> iterator = myHashMap.iterator(); iterator.hasNext(); ){
			MyHashMap<String, Integer>.Entry entry = iterator.next();
			System.out.println(entry.getKey() + " " + entry.getValue());
		}

//		System.out.println(myHashMap.search("Date").isPresent());
//		System.out.println(myHashMap.search("testing").isPresent());
	}
}
