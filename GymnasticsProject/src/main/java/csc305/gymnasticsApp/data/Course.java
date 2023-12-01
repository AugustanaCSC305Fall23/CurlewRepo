package csc305.gymnasticsApp.data;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private static List<LessonPlan> lessonPlanList = new ArrayList<>();

    public static void addPlanToCourse(LessonPlan plan){
        lessonPlanList.add(plan);
    }
    public static void removePlanFromCourse(LessonPlan plan){
        lessonPlanList.remove(plan);
    }
    public static List<LessonPlan> getLessonPlanList(){
        return lessonPlanList;
    }
    public static void clearLessonPlanList(){
        lessonPlanList = new ArrayList<>();
    }
    public static boolean containsLessonPlan(String lessonPlanName){
        for(LessonPlan Plan:lessonPlanList){
            if(Plan.getLessonPlanTitle().equals(lessonPlanName)){
                return true;
            }
        }
        return false;
    }

}
