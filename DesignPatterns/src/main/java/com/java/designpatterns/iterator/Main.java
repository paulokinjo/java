package com.java.designpatterns.iterator;

public class Main {
    public static void main(String[] args) {
        var history = new BrowseHistory();
        history.push("a");
        history.push("b");
        history.push("c");
        Iterator iterator = history.createIterator();

        while (iterator.hasNext()) {
            var url = iterator.current();
            System.out.printf(url);
            iterator.next();
        }
    }
}
