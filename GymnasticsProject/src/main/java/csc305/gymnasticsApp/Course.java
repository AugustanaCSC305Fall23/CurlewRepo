package csc305.gymnasticsApp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

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


    /*
    JACKS WORK BELOW, COMMENTED OUT

    private List<Card> cards;
    private String title;

    public TreeMap<String, Card> getCardsGroupedByEvent() {
        return null;
    }

    public void addCard(Card newCard) {

    }

    public void save(File saveFile) {

    }
    */
}
