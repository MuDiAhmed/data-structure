package io.datastructure;

public class MyArrayList <T>{
	Object[] arr = new Object[10];
	int currentIndex = 0;
	
	void add(T value) {
		expandIfNeeded();
		arr[currentIndex++] = value;
	}
	
	void add(int index, T value) {
		if(index < 0 || index > currentIndex) {
			throw new IllegalArgumentException();
		}
		expandIfNeeded();
		for(int i = index; i < currentIndex; i++) {
			arr[i+1] = arr[i];
		}
		arr[index] = value;
	}
	
	T remove(int index) {
		if(index < 0 || index > currentIndex) {
			throw new IllegalArgumentException();
		}
		
		T value = (T)arr[index];
		
		for(int i = index; i<currentIndex - 1; i++) {
			arr[i] = arr[i+1];
		}
		currentIndex--;
		return value;
	}
	
	T remove(T value) {
		int index = -1;
		for(int i = 0; i < currentIndex || (index>=0 && i< currentIndex - 1); i++) {
			
			if(arr[i].equals(value)) {
				index = i;
				currentIndex--;
				break;
			}
			if(index>=0) {
				arr[i] = arr[i+1];
			}
		}
		return value;
	}
	
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("[");
		for(int i=0; i < currentIndex - 1; i++) {
			stringBuilder.append(arr[i].toString() + ",");
		}
		stringBuilder.append(arr[currentIndex - 1]);
		stringBuilder.append("]");
		return stringBuilder.toString();
	}
	
	private void expandIfNeeded() {
		if(currentIndex + 1 > arr.length) {
			Object[] newArray = new Object[arr.length * 2];
			for(int i = 0; i < arr.length; i++) {
				newArray[i] = arr[i];
			}
			arr = newArray;
		}
	}
	
}
