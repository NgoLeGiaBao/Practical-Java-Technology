package org.example;

public class TextEditor {
    private String editorName;
    private String fileName;
    private String textContent;

    public TextEditor(String editorName, String fileName, String textContent) {
        this.editorName = editorName;
        this.fileName = fileName;
        this.textContent = textContent;
    }

    public void displayInfo() {
        System.out.println("Editor Name: " + editorName);
        System.out.println("File Name: " + fileName);
        System.out.println("Text Content: " + textContent);
    }

}
