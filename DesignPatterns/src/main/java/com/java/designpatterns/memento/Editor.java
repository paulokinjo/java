package com.java.designpatterns.memento;

public class Editor {
    private String content;

    public String getContent() {
        return content;
    }

    public EditorState createState() {
        return new EditorState(content);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void restore(EditorState state) {
        content = state.getContent();
    }
}
