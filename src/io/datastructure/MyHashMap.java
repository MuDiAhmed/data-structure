package io.datastructure;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

public class MyHashMap<K, V> implements Iterable<MyHashMap<K, V>.Entry> {
    private MyLinkedList<Entry>[] list = new MyLinkedList[4];
    private double fillFactor = .8;
    private double growthFactor = 1.5;

    private int size;

    public MyHashMap(){}

    public MyHashMap(double fillFactor, double growthFactor){
        this.fillFactor = fillFactor;
        this.growthFactor = growthFactor;
    }

    //add
    public void add(K key, V value){
        increaseSizeIfNeeded();
        int index = index(key, list.length);
        if(list[index] == null){
            list[index] = new MyLinkedList<>();
        }
        MyLinkedList<Entry> myLinkedLists = list[index];
        for (Entry entry : myLinkedLists) {
            if (entry.getKey().equals(key)) {
                int nodeIndex = myLinkedLists.indexOf(entry);
                myLinkedLists.replace(nodeIndex, new Entry(key, value));
                return;
            }
        }
        myLinkedLists.add(new Entry(key, value));
        size += 1;
    }
    //search
    public Optional<Entry> search(K key){
        for (Entry entry : (Iterable<Entry>) this) {
            if(entry.getKey().equals(key)){
                return Optional.of(entry);
            }
        }
        return Optional.empty();
    }
    //delete

    public int getSize() {
        return size;
    }
    //iteration
    @Override
    public java.util.Iterator<Entry> iterator() {
        return new Iterator(this);
    }
    //hash item
    private int index(K key, int mod){
        return key.hashCode() % mod;
    }
    //increase array size
    private void increaseSizeIfNeeded(){
        boolean haveToIncrease = size >= Math.round(list.length * fillFactor);
        if(haveToIncrease){
            int newListSize = (int) Math.ceil(list.length * growthFactor);
            MyLinkedList<Entry>[] newList = new MyLinkedList[newListSize];
            for (Entry entry : this) {
                int index = index(entry.getKey(), newList.length);
                if (newList[index] == null) {
                    newList[index] = new MyLinkedList<>();
                }
                MyLinkedList<Entry> myLinkedLists = newList[index];
                myLinkedLists.add(entry);
            }
            list = newList;
        }
    }

    private class Iterator implements java.util.Iterator<Entry> {
        private MyLinkedList<Entry>[] list;
        private MyLinkedList<Entry>.Node current;
        private int index = 0;
        private Iterator(MyHashMap<K, V> map){
            this.list = map.list;
        }

        @Override
        public boolean hasNext() {
            if(current != null && current.hasNext()) return true;
            for(int counter = index == 0? 0 :index+1; counter < this.list.length; counter++){
                if(this.list[counter] != null && this.list[counter].getSize() != 0){
                    if(this.list[counter].get(0).equals(current)) continue;
                    return true;
                }
            }
            return false;
        }

        @Override
        public Entry next() {
            if(current != null && current.hasNext()) {
                current = current.getNext();
                return current.getValue();
            }
            for(int counter = index == 0? 0 :index+1;counter < this.list.length; counter++){
                if(this.list[counter] != null && this.list[counter].getSize() != 0){
                    if(this.list[counter].get(0).equals(current)) continue;
                    current = this.list[counter].get(0);
                    index = counter;
                    return current.getValue();
                }
            }
            throw new NoSuchElementException();
        }
    }

    public class Entry{
        private V value;
        private K key;

        private Entry(K key, V value) {
            this.value = value;
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public K getKey() {
            return key;
        }

        @Override
        public boolean equals(Object o) {
            System.out.println("entry equals method");
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry entry = (Entry) o;
            return Objects.equals(value, entry.value) && Objects.equals(key, entry.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, key);
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "value=" + value +
                    ", key=" + key +
                    '}';
        }
    }

}
