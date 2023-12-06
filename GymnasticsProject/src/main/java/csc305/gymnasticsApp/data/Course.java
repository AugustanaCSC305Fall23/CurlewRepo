package csc305.gymnasticsApp.data;

import java.util.ArrayList;
import java.util.List;

/**
 * The Course class represents a collection of lesson plans for gymnastics courses.
 * It provides methods to manage lesson plans within a course.
 */
public class Course implements Cloneable{

    /**
     * A list containing all lesson plans in the course
     */
    private List<LessonPlan> lessonPlanList;

    private String courseName = "First Course";
    private static Course course = new Course();

    public Course(){
        lessonPlanList = new ArrayList<>();
        courseName = "First Course";
        course = this;
    }

    /**
     * Adds a lesson plan to the course
     *
     * @param plan The lesson plan to be added
     */
    public void addPlanToCourse(LessonPlan plan){
        lessonPlanList.add(plan);
    }

    /**
     * Removes a lesson plan from the course
     *
     * @param plan The lesson plan to be removed
     */
    public void removePlanFromCourse(LessonPlan plan){
        lessonPlanList.remove(plan);
    }

    /**
     * Retrieves the list of lesson plans in the course
     *
     * @return A list of lesson plans in the course
     */
    public List<LessonPlan> getLessonPlanList(){
        return lessonPlanList;
    }

    /**
     * Clears the list of lesson plans in the course
     */
    public void clearLessonPlanList(){
        lessonPlanList = new ArrayList<>();
    }

    /**
     * Checks if the course contains a lesson plan with the specified name
     *
     * @param lessonPlanName The name of the lesson plan to check for
     * @return true if the course contains a lesson plan with the specified name otherwise returns false
     */
    public boolean containsLessonPlan(String lessonPlanName){
        for(LessonPlan Plan:lessonPlanList){
            if(Plan.getLessonPlanTitle().equals(lessonPlanName)){
                return true;
            }
        }
        return false;
    }

    public String getCourseName(){
        return courseName;
    }

    public void loadEverythingFromFile(ArrayList<String> file){
        CardDatabase.getAllCards();
        courseName = file.get(0);
        file.remove(0);
        while (!(((file.get(0).equals("done with event"))) && ((file.get(1).equals("done with lessonplan"))) && ((file.get(2).equals("done with course"))))){
            if(file.get(0).equals("done with event") && file.get(1).equals("done with lessonplan")){
                file.remove(0);
                file.remove(0);
            }
            LessonPlan curPlan = new LessonPlan();
            curPlan.setLessonPlanTitle(file.get(0));
            file.remove(0);
            while(!(((file.get(0).equals("done with event"))) && ((file.get(1).equals("done with lessonplan"))))){
                if(file.get(0).equals("done with event")){
                    file.remove(0);
                }
                curPlan.addEventName(file.get(0));
                file.remove(0);
                //gets most recent event in this plan
                List<Card> curEvent = new ArrayList<>();
                while(!(file.get(0).equals("done with event"))){
                    curEvent.add(CardDatabase.getCardByID(file.get(0)));
                    file.remove(0);
                }
                curPlan.addToEventList(curEvent);
            }
            addPlanToCourse(curPlan);
        }
    }

    public Course clone() {
        try {
            Course clone = (Course) super.clone();
            clone.lessonPlanList = new ArrayList<LessonPlan>();
            clone.courseName = courseName;
            for (LessonPlan plan : lessonPlanList) {
                clone.lessonPlanList.add(plan.clone());
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            // should never happen
            e.printStackTrace();
            return null;
        }
    }


    public State createMemento() {
        return new State();
    }

    public void restoreState(State canvasState) {
        canvasState.restore();
        repaint();
    }

    public class State {
        private Course course;

        public State() {
            course = (Course) Course.this.clone();
        }

        public void restore() {
            Course.this = (Course) course.clone();
        }
    }

}
