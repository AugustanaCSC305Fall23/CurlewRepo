package csc305.gymnasticsApp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Course {
    //private List<LessonPlan> plan;
    private static String courseTitle;
    private static String eventOneName;
    private static String eventTwoName;
    private static List<Card> eventOneCards = new ArrayList<>();
    private static List<Card> eventTwoCards = new ArrayList<>();


    public static void setEventOneCards(List<Card> cardList){
        eventOneCards = cardList;
    }

    public static void setEventTwoCards(List<Card> cardList){
        eventTwoCards = cardList;
    }

    public static void setCourseTitle(String title){
        courseTitle = title;
    }

    public static void setEventOneName(String name){
        eventOneName = name;
    }

    public static void setEventTwoName(String name){
        eventTwoName = name;
    }
    public static void save(File saveFile) {

    }

    public static void printEverything(){
        System.out.println(courseTitle + " " + eventOneName + " " + eventTwoName);
        if (!(eventOneCards.isEmpty())) {
            System.out.println(eventOneCards.get(0).toString());
        } else{
            System.out.println("Event one cards is empty");
        }
        if(!(eventTwoCards.isEmpty())) {
            System.out.println(eventTwoCards.get(0).toString());
        } else{
            System.out.println("Event two cards is empty");
        }
    }


    public static Course loadCourseFile(File courseFile) {
        return null;
    }
}
