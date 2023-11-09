package csc305.gymnasticsApp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LessonPlan {
    //private List<Course> plan;
    private static String lessonPlanTitle;
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

    public static void setLessonPlanTitle(String title){
        lessonPlanTitle = title;
    }

    public static void setEventOneName(String name){
        eventOneName = name;
    }

    public static void setEventTwoName(String name){
        eventTwoName = name;
    }
    public static void save(File saveFile) {
    }
    public static String getLessonPlanTitle(){
        return lessonPlanTitle;
    }
    public static String getEventOneName(){
        return eventOneName;
    }
    public static String getEventTwoName(){
        return eventTwoName;
    }
    public static List<Card> getEventOneCards(){
        return eventOneCards;
    }
    public static List<Card> getEventTwoCards(){
        return eventTwoCards;
    }

    private static boolean hasBeenLoaded = false;

    public static void addToEventOne(Card card){
        eventOneCards.add(card);
    }
    public static void addToEventTwo(Card card){
        eventTwoCards.add(card);
    }

    public static void printEverything(){
        System.out.println(lessonPlanTitle + " " + eventOneName + " " + eventTwoName);
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
    public static void loadPlanFromFile() {
        if (GymnasticsAppBeta.getLoaded() && !hasBeenLoaded) {
            System.out.println("LOADING FROM FILE");
            CardDatabase.getAllCards();
            resetCourse();
            ArrayList<String> arrayList = GymnasticsAppBeta.setPreviewPage();
            for(int i = 0; i < arrayList.size(); i++) {
                System.out.println(arrayList.get(i));
            }
            String loadTitle = arrayList.remove(0);
            String loadEventOneTitle = arrayList.remove(0);
            String loadEventTwoTitle = arrayList.remove(0);
            setLessonPlanTitle(loadTitle);
            setEventOneName(loadEventOneTitle);
            setEventTwoName(loadEventTwoTitle);
            ArrayList<String> arrayList1 = new ArrayList<String>();

            for(int i = 0; i < arrayList.size(); i++) {
                if(arrayList.get(i).equals("end")) {
                    arrayList.remove(i);
                    break;
                } else {
                    Card card = CardDatabase.getCardByID(arrayList.remove(i));
                    eventOneCards.add(card);
                    CardDatabase.addEventOneTreeCard(card);
                    i--;
                }
            }
            for(int j = 0; j < arrayList.size(); j++){
                Card card = CardDatabase.getCardByID(arrayList.remove(j));
                eventTwoCards.add(card);
                CardDatabase.addEventTwoTreeCard(card);
                j--;
            }
            MainEditDisplayController.addTreeCardItem(eventOneCards,eventTwoCards);
            hasBeenLoaded = true;
        }
    }

    public static void resetCourse(){
        lessonPlanTitle = "";
        eventOneName = "";
        eventTwoName = "";
        eventOneCards.clear();
        eventTwoCards.clear();
    }

    public static LessonPlan loadCourseFile(File courseFile) {
        return null;
    }
}
