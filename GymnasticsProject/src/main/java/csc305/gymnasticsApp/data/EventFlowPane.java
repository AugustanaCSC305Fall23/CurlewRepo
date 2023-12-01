package csc305.gymnasticsApp.data;

import javafx.scene.layout.FlowPane;

/**
 * The EventFlowPane class represents a custom JavaFX FlowPane designed for displaying events.
 * It extends the JavaFX FlowPane class and implements the Cloneable interface for cloning instances.
 */
public class EventFlowPane extends FlowPane implements Cloneable {
    /**
     * The preferred height of the event flow pane
     */
    private double prefHeight = 200.0;

    /**
     * The preferred width of the event flow pane
     */
    private double prefWidth = 749.0;

    /**
     * Creates a clone of the current EventFlowPane instance
     *
     * @return A cloned instance of the EventFlowPane
     */
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
