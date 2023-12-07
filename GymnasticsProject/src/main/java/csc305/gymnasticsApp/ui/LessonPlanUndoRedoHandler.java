package csc305.gymnasticsApp.ui;

import csc305.gymnasticsApp.data.LessonPlan;

import java.util.Stack;

public class LessonPlanUndoRedoHandler {
    private Stack<LessonPlan.State> undoStack, redoStack;
    // invariant: The top state of the undoStack always is a copy of the
    // current state of the canvas.
    private LessonPlan lessonplan;

    /**
     * constructor
     *
     * @param lessonplan the LessonPlan whose changes are saved for later
     *               restoration.
     */
    public LessonPlanUndoRedoHandler(LessonPlan lessonplan) {
        undoStack = new Stack<LessonPlan.State>();
        redoStack = new Stack<LessonPlan.State>();
        this.lessonplan = lessonplan;
        // store the initial state of the canvas on the undo stack
        undoStack.push(lessonplan.createMemento());
    }

    /**
     * saves the current state of the lesson plan for later restoration
     */
    public void saveState() {
        LessonPlan.State lessonPlanState = lessonplan.createMemento();
        System.out.print("Printing For the Save State: ");
        lessonplan.printEverything();
        undoStack.push(lessonPlanState);
        redoStack.clear();
    }

    /**
     * restores the state of the lesson plan to what it was before the last
     * change. Nothing happens if there is no previous state (for example, when the
     * application first starts up or when you've already undone all actions since
     * the startup state).
     */
    public void undo() {
        if (undoStack.size() == 1) { // only the current state is on the stack
            return;
        }
        LessonPlan.State lessonPlanState = undoStack.pop();
        redoStack.push(lessonPlanState);
        lessonplan.restoreState(undoStack.peek());
    }

    /**
     * restores the state of the lesson plan to what it was before the last undo
     * action was performed. If some change was made to the state of the lesson plan
     * since the last undo, then this method does nothing.
     */
    public void redo() {
        if (redoStack.isEmpty())
            return;

        LessonPlan.State lessonPlanState = redoStack.pop();
        undoStack.push(lessonPlanState);
        lessonplan.restoreState(lessonPlanState);
    }
}
