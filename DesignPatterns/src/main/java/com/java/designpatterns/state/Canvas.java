package com.java.designpatterns.state;

public class Canvas {
    private Tool currenTool = new SelectionTool();

    public void mouseDown() {
        currenTool.mouseDown();
    }

    public void mouseUp() {
        currenTool.mouseUp();
    }

    public Tool getCurrenTool() {
        return currenTool;
    }

    public void setCurrenTool(Tool currenTool) {
        this.currenTool = currenTool;
    }
}
