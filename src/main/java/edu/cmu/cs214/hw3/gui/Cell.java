package edu.cmu.cs214.hw3.gui;

public class Cell {

    private String text;
    private String clazz;
    private String action;
    private String location;
    private String link;

    Cell(String text, String clazz, String action, String location) {
        this.text = text;
        this.clazz = clazz;
        this.action = action;
        this.location = location;
        this.link = "/" + this.action + "?" + this.location;
    }

    public String getText() {
        return this.text;
    }

    public String getClazz() {
        return this.clazz;
    }

    public void setClazz(String newClazz) {
        clazz = newClazz;
    }

    public void setText(String newText) {
        text = newText;
    }

    public void setAction(String newAction) {
        action = newAction;
        link = "/" + action + "?" + location;
    }

    public String getLink() {
        return this.action + this.location;
    }

    @Override
    public String toString() {
        return "Cell[" +
                "text=" + this.text + ", " +
                "clazz=" + this.clazz + ", " +
                "link=" + this.link + ']';
    }
}
