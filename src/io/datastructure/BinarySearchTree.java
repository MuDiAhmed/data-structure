package io.datastructure;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node root;
    //add
    public void add(T value){
        root = add(root, value);
    }
    //search
    public boolean search(T value){
        return search(root, value);
    }
    //delete
    public void delete(T value){
        root = delete(root, value);
    }
    //depth travers pre-order (node - left - right)
    public void traversPreOrder(Consumer<T> consumer){
        traversPreOrder(consumer, root);
    }
    //depth travers in-order (left - node - right)
    public void traversInOrder(Consumer<T> consumer){
        traversInOrder(consumer, root);
    }
    //depth travers post-order (left - right - node)
    public void traversPostOrder(Consumer<T> consumer){
        traversPostOrder(consumer, root);
    }
    //breadth travers
    public void traversBreadth(Consumer<T> consumer){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            Node current = queue.remove();
            consumer.accept(current.value);
            if(current.left != null){
                queue.add(current.left);
            }
            if(current.right != null){
                queue.add(current.right);
            }
        }
    }

    private Node add(Node root, T value){
        if(root == null) return new Node(value);
        int compareResult = root.value.compareTo(value);
        if(compareResult < 0){
            root.right = add(root.right, value);
        }
        if(compareResult > 0){
            root.left = add(root.left, value);
        }
        return root;
    }

    private boolean search(Node root, T value){
        if(root == null) return false;
        if(root.value.equals(value)) return true;
        int compareResult = root.value.compareTo(value);
        if(compareResult < 0){
            return search(root.right, value);
        }
        if(compareResult > 0){
            return search(root.left, value);
        }
        return false;
    }

    private Node delete(Node root, T value){
        if(root == null) return null;
        if(root.value.equals(value)){
            //Leave node, then save to remove
            if(root.left == null && root.right == null){
                return null;
            }
            //middle node with only one child
            if(root.left == null){
                return root.right;
            }
            if(root.right == null){
                return root.left;
            }
            //middle node with two children
            T replaceValue = findSmallest(root.right);
            root.value = replaceValue;
            delete(root.right, replaceValue);
            return root;

        }
        int compareResult = root.value.compareTo(value);
        if(compareResult < 0){
            root.right = delete(root.right, value);
        }
        if(compareResult > 0){
            root.left = delete(root.left, value);
        }
        return root;

    }
    private void traversPreOrder(Consumer<T> consumer, Node root){
        if(root != null){
            consumer.accept(root.value);
            traversPreOrder(consumer, root.left);
            traversPreOrder(consumer, root.right);
        }
    }
    private void traversInOrder(Consumer<T> consumer, Node root){
        if(root != null){
            traversInOrder(consumer, root.left);
            consumer.accept(root.value);
            traversInOrder(consumer, root.right);
        }
    }
    private void traversPostOrder(Consumer<T> consumer, Node root){
        if(root != null){
            traversPostOrder(consumer, root.left);
            traversPostOrder(consumer, root.right);
            consumer.accept(root.value);
        }
    }

    private T findSmallest(Node root){
        return root.left == null? root.value : findSmallest(root.left);
    }
    private class Node{
        T value;
        Node left;
        Node right;

        Node(T value){
            this.value = value;
        }
    }
}
