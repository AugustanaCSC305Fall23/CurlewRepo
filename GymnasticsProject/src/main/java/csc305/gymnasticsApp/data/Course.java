package csc305.gymnasticsApp.data;

import java.util.ArrayList;
import java.util.List;

/**
 * The Course class represents a collection of lesson plans for gymnastics courses.
 * It provides methods to manage lesson plans within a course.
 */
public class Course {

    /**
     * A list containing all lesson plans in the course
     */
    private static List<LessonPlan> lessonPlanList = new ArrayList<>();

    private static String courseName = "First Course";

    /**
     * Adds a lesson plan to the course
     *
     * @param plan The lesson plan to be added
     */
    public static void addPlanToCourse(LessonPlan plan){
        lessonPlanList.add(plan);
    }

    /**
     * Removes a lesson plan from the course
     *
     * @param plan The lesson plan to be removed
     */
    public static void removePlanFromCourse(LessonPlan plan){
        lessonPlanList.remove(plan);
    }

    /**
     * Retrieves the list of lesson plans in the course
     *
     * @return A list of lesson plans in the course
     */
    public static List<LessonPlan> getLessonPlanList(){
        return lessonPlanList;
    }

    /**
     * Clears the list of lesson plans in the course
     */
    public static void clearLessonPlanList(){
        lessonPlanList = new ArrayList<>();
    }

    /**
     * Checks if the course contains a lesson plan with the specified name
     *
     * @param lessonPlanName The name of the lesson plan to check for
     * @return true if the course contains a lesson plan with the specified name otherwise returns false
     */
    public static boolean containsLessonPlan(String lessonPlanName){
        for(LessonPlan Plan:lessonPlanList){
            if(Plan.getLessonPlanTitle().equals(lessonPlanName)){
                return true;
            }
        }
        return false;
    }

    public static String getCourseName(){
        return courseName;
    }


}
