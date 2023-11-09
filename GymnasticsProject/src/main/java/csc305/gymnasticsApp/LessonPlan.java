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
    public static void deleteFromEventOne(Card card){
        eventOneCards.remove(card);
    }
    public static void deleteFromEventTwo(Card card){
        eventTwoCards.remove(card);
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
            resetLessonPlan();
            if(eventOneCards.isEmpty()){
                System.out.println("clear");
            }else {
                for (Card card : eventOneCards) {
                    System.out.println(card.getTitle());
                }
            }
            ArrayList<String> arrayList = GymnasticsAppBeta.setPreviewPage();
            String loadTitle = arrayList.remove(0);
            String loadEventOneTitle = arrayList.remove(0);
            String loadEventTwoTitle = arrayList.remove(0);
            setLessonPlanTitle(loadTitle);
            setEventOneName(loadEventOneTitle);
            setEventTwoName(loadEventTwoTitle);

            for(int i = 0; i < arrayList.size(); i++) {
                if(arrayList.get(i).equals("end")) {
                    arrayList.remove(i);
                    break;
                } else {
                    Card card = CardDatabase.getCardByID(arrayList.remove(i));
                    eventOneCards.add(card);
                    System.out.println(card.getTitle());
                    i--;
                }
            }
            System.out.println("");
            for(int j = 0; j < arrayList.size(); j++){
                Card card = CardDatabase.getCardByID(arrayList.remove(j));
                eventTwoCards.add(card);
                System.out.println(card.getTitle());
                j--;
            }
            for(Card card: eventOneCards){
                System.out.println(card.getTitle());
            }
            MainEditDisplayController.addTreeCardItem(eventOneCards,eventTwoCards);
            hasBeenLoaded = true;
        }
    }

    public static Card getCardFromTreeItem(String value, int treeNum) {
        if (treeNum == 1){
            for(int i = 0; i < eventOneCards.size(); i++){
                if (eventOneCards.get(i).getTitle().equals(value)){
                    return eventOneCards.get(i);
                }
            }
        }else {
            for (int i = 0; i < eventTwoCards.size(); i++) {
                if (eventTwoCards.get(i).getTitle().equals(value)) {
                    return eventTwoCards.get(i);
                }
            }
        }
        //return eventOneTreeCards.get(0);
        return null;
    }
    public static void resetBoolean(){
        hasBeenLoaded = false;
    }

    public static void resetLessonPlan(){
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
