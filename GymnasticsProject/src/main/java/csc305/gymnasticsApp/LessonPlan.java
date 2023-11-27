package csc305.gymnasticsApp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LessonPlan {
    //private List<Course> plan;
    private String lessonPlanTitle;
    private static String eventOneName;
    private static String eventTwoName;
    private List<Card> eventOneCards;
    private List<Card> eventTwoCards;

    public LessonPlan() {
        eventOneCards = new ArrayList<>();
        eventTwoCards = new ArrayList<>();
    }


    public void setEventOneCards(List<Card> cardList){
        eventOneCards = cardList;
    }

    public void setEventTwoCards(List<Card> cardList){
        eventTwoCards = cardList;
    }

    public void setLessonPlanTitle(String title){
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
    public String getLessonPlanTitle(){
        return lessonPlanTitle;
    }
    public static String getEventOneName(){
        return eventOneName;
    }
    public static String getEventTwoName(){
        return eventTwoName;
    }
    public List<Card> getEventOneCards(){
        return eventOneCards;
    }
    public List<Card> getEventTwoCards(){
        return eventTwoCards;
    }

    private static boolean hasBeenLoaded = false;

    public void addToEventOne(Card card){
        eventOneCards.add(card);
    }
    public void addToEventTwo(Card card){
        eventTwoCards.add(card);
    }
    public void deleteFromEventOne(Card card){
        eventOneCards.remove(card);
    }
    public void deleteFromEventTwo(Card card){
        eventTwoCards.remove(card);
    }

    public void printEverything(){
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


    public void loadPlanFromFile(ArrayList<String> loadedLessonPlan) {
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
            lessonPlanTitle = loadedLessonPlan.remove(0);

            eventOneName = loadedLessonPlan.remove(0);
            eventTwoName = loadedLessonPlan.remove(0);
            MainEditDisplayController.eventOneItems.setValue(eventOneName);
            MainEditDisplayController.eventTwoItems.setValue(eventTwoName);

            for(int i = 0; i < loadedLessonPlan.size(); i++) {
                if(loadedLessonPlan.get(i).equals("end")) {
                    loadedLessonPlan.remove(i);
                    break;
                } else {
                    Card card = CardDatabase.getCardByID(loadedLessonPlan.remove(i));
                    eventOneCards.add(card);
                    System.out.println(card.getTitle());
                    i--;
                }
            }
            System.out.println("");
            for(int j = 0; j < loadedLessonPlan.size(); j++){
                Card card = CardDatabase.getCardByID(loadedLessonPlan.remove(j));
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

    public Card getCardFromTreeItem(String value, int treeNum) {
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

    public void resetLessonPlan(){
        lessonPlanTitle = "";
        eventOneName = "Event 1";
        eventTwoName = "Event 2";
        eventOneCards.clear();
        eventTwoCards.clear();
    }

    public static LessonPlan loadCourseFile(File courseFile) {
        return null;
    }

}
