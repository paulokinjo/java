package com.java.designpatterns.state;

public class Main {
    public static void main(String[] args) {
        Canvas canvas = new Canvas();
        canvas.mouseDown();

        canvas.setCurrenTool(new SelectionTool());
        canvas.mouseDown();

        canvas.setCurrenTool(new BrushTool());
        canvas.mouseUp();
    }
}
