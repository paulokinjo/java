package linkedlists;

import java.util.NoSuchElementException;

public class LinkedList {
    class Node {

        public Node next;
        public int value;

        public Node(int value) {
            this.value = value;
        }
    }

    private Node first;
    private Node last;
    private int size;

    public void addLast(int item) {
        var node = new Node(item);

        if (isEmpty()) {
            first = last = node;
        } else {
            last.next = node;
            last = node;
        }
        size++;
    }

    public void addFirst(int item) {
        var node = new Node(item);

        if (isEmpty()) {
            first = last = node;
        } else {
            node.next = first;
            first = node;
        }

        size++;
    }

    public int indexOf(int item) {
        int index = 0;
        var current = first;

        while(current != null) {
            if(current.value == item) {
                return index;
            }
            current = current.next;
            index++;
        }

        return -1;
    }

    public void removeFirst() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }

        if(first == last) {
            first = last = null;
        } else {
            var second = first.next;
            first.next = null;
            first = second;
        }
        size--;
    }

    public void removeLast() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }

        if(first == last) {
            first = last = null;
        } else {
            var previous = getPrevious(last);
            last = previous;
            last.next = null;
        }
        size--;
    }

    public int getSize() {
        return size;
    }

    public boolean contains(int item) {
        return indexOf(item) != -1;
    }

    public int[] toArray() {
        int[] array = new int[size];

        var current = first;
        var index = 0;
        while(current !=null) {
            array[index++] = current.value;
            current = current.next;
        }

        return array;
    }

    public void reverse() {
        if(isEmpty()) return;

        var previous = first;
        var current = first.next;

        while(current != null) {
            var next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        last = first;
        last.next = null;
        first = previous;
    }

    public int getKthFromTheEnd(int k) {
        if(size >= k) {
            int indexFromEnd = size - k;
            int currentIndex = 0;

            var currentNode = first;
            while(currentIndex != indexFromEnd) {
                currentNode = currentNode.next;
                currentIndex++;
            }

            return currentNode.value;
        }

        return -1;
    }

    private Node getPrevious(Node node) {
        var current = first;
        while(current != null) {
            if(current.next == node) {
                return current;
            }
            current = current.next;
        }

        return null;
    }

    private boolean isEmpty() {
        return first == null;
    }
}

