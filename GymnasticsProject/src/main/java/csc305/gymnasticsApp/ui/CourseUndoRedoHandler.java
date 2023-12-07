
package csc305.gymnasticsApp.ui;


import csc305.gymnasticsApp.data.Course;

import java.util.Stack;


/**
 * This class is responsible for all the information needed in order to undo or
 * redo an action in the Gymnastics App.
 *
 * @author Dale Skrien (ported to JavaFX by Forrest Stonedahl, Copied and Modified for Gymnastics App by Jack Bigler)
 * @version 1.0 August 2005 (updated to JavaFX Oct. 2018)
 **/
public class CourseUndoRedoHandler {
    private Stack<Course.State> undoStack, redoStack;
    // invariant: The top state of the undoStack always is a copy of the
    // current state of the canvas.
    private Course course;

    /**
     * constructor
     *
     * @param course the Course whose changes are saved for later
     *               restoration.
     */
    public CourseUndoRedoHandler(Course course) {
        undoStack = new Stack<Course.State>();
        redoStack = new Stack<Course.State>();
        this.course = course;
        // store the initial state of the canvas on the undo stack
        undoStack.push(course.createMemento());
    }

    /**
     * saves the current state of the course for later restoration
     */
    public void saveState() {
        Course.State courseState = course.createMemento();
        undoStack.push(courseState);
        redoStack.clear();
    }

    /**
     * restores the state of the course to what it was before the last
     * change. Nothing happens if there is no previous state (for example, when the
     * application first starts up or when you've already undone all actions since
     * the startup state).
     */
    public void undo() {
        if (undoStack.size() == 1) // only the current state is on the stack
            return;

        Course.State courseState = undoStack.pop();
        redoStack.push(courseState);
        course.restoreState(undoStack.peek());
    }

    /**
     * restores the state of the course to what it was before the last undo
     * action was performed. If some change was made to the state of the course
     * since the last undo, then this method does nothing.
     */
    public void redo() {
        if (redoStack.isEmpty())
            return;

        Course.State courseState = redoStack.pop();
        undoStack.push(courseState);
        course.restoreState(courseState);
    }
}


