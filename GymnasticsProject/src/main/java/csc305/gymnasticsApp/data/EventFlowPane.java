package csc305.gymnasticsApp.data;

import javafx.scene.layout.FlowPane;

public class EventFlowPane extends FlowPane implements Cloneable {
    private double prefHeight = 200.0;
    private double prefWidth = 749.0;

    public EventFlowPane clone() {
        try {
            EventFlowPane clone = (EventFlowPane) super.clone();
            clone.setPrefHeight(prefHeight);
            clone.setPrefWidth(prefWidth);
            return clone;
        } catch (CloneNotSupportedException e) {
            assert false; // this block should never execute
            return null;
        }
    }
}
