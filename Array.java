
import java.lang.*;
import java.util.Iterator;

public class Program {
    public static void main(String[] args) {
        System.out.println("Testing CustomArray");

        CustomArray<Integer> integers = new CustomArray<Integer>(20);
        System.out.println("size: " + integers.size());
        System.out.println("is empty? " + integers.isEmpty());
        System.out.println("Add element [15] to position 0");
        integers.set(0, 15);
        System.out.println("size: " + integers.size());
        System.out.println("is empty? " + integers.isEmpty());
        System.out.println("get element at position 0: " + integers.get(0));
        System.out.println("adding element 6");
        integers.add(6);
        System.out.println("adding element 99");
        integers.add(99);
        System.out.println(integers);
        System.out.println("size: " + integers.size());
        System.out.println("is empty? " + integers.isEmpty());
        System.out.println("get element at position 1: " + integers.get(1));
        System.out.println("remove item at index 0: " + integers.removeAt(0));
        System.out.println("size: " + integers.size());
        System.out.println("is empty? " + integers.isEmpty());
        System.out.println("get element at position 0: " + integers.get(0));
        System.out.println("remove element 99: " + integers.remove(99));
        System.out.println("size: " + integers.size());
        System.out.println("is empty? " + integers.isEmpty());
        System.out.println("index of 6: " + integers.indexOf(6));
        System.out.println("contain element 6? 8? " + integers.contains(6) + ", " + integers.contains(8));        
        System.out.println(integers);
    }
}

@SuppressWarnings("unchecked")
class CustomArray<T> implements Iterable<T> {
    private T[] arr;
    private int len = 0;
    private int capacity = 0;

    public CustomArray() {
        this(16);
    }

    public CustomArray(int capacity) {
        if (capacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        this.capacity = capacity;
        arr = (T[]) new Object[capacity];
    }

    public int size() {
        return len;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T get(int index) {
        return arr[index];
    }

    public void set(int index, T elem) {
        if (len < index) {
            throw new IndexOutOfBoundsException("The index is out ouf bound. Index: " + index);
        }

        T currElem = arr[index];
        if (currElem == null) {
            len++;
        } else {
            if (len > 0) {
                --len;
            }
        }

        arr[index] = elem;
    }

    public void clear() {
        for (int i = 0; i < capacity; i++) {
            arr[i] = null;
        }
        len = 0;
    }

    public void add(T elem) {
        if (len + 1 >= capacity) {
            if (capacity == 0) {
                capacity = 1;
            } else {
                capacity *= 2;
            }

            T[] newArr = (T[]) new Object[capacity];
            for (int i = 0; i < len; i++) {
                newArr[i] = arr[i];
            }

            arr = newArr;
        }

        arr[len++] = elem;
    }

    public T removeAt(int rmIndex) {
        if (rmIndex >= len && rmIndex < 0) {
            throw new IndexOutOfBoundsException();
        }

        T data = arr[rmIndex];
        T[] newArr = (T[]) new Object[len - 1];
        for (int i = 0, j = 0; i < len; i++, j++) {
            if (i == rmIndex)
                j--;
            else
                newArr[j] = arr[i];
        }

        arr = newArr;
        capacity = --len;
        return data;
    }

    public boolean remove(Object obj) {
        for (int i = 0; i < len; i++) {
            if (arr[i].equals(obj)) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    public int indexOf(Object obj) {
        for (int i = 0; i < len; i++) {
            if (arr[i].equals(obj)) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;

            public boolean hasNext() {
                return index < len;
            }

            public T next() {
                return arr[index++];
            }
        };
    }

    @Override public String toString() {
        if(len == 0) return "[]";
        else {
            StringBuilder sb = new StringBuilder(len).append("[");
            for (int i = 0; i < len - 1; i++) {
                sb.append(arr[i] + ", ");
            }
            return sb.append(arr[len - 1] + "]").toString();
        }        
    }
}