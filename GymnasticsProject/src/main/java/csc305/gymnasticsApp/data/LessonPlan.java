package csc305.gymnasticsApp.data;

import csc305.gymnasticsApp.ui.GymnasticsAppBeta;
import csc305.gymnasticsApp.ui.MainEditDisplayController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LessonPlan {
    //private List<Course> plan;
    private String lessonPlanTitle;

    private List<String> eventNames = new ArrayList<>();

    private List<List<Card>> eventList;

    public LessonPlan() {
        eventList = new ArrayList<>();
    }



    public List<List<Card>> getEventList() {
        return eventList;
    }

    public void setEventList(List<List<Card>> eventList) {
        this.eventList = eventList;
    }
    public void addToEventList(List<Card> event){
        eventList.add(event);
    }

    public void addAllToEventList(List<List<Card>> newEventList){
        eventList.addAll(newEventList);
    }

    public void clearEventList(){
        eventList.clear();
    }


    public void setLessonPlanTitle(String title){
        lessonPlanTitle = title;
    }

    public void setEventName(String name, int index){
        eventNames.set(index, name);
    }

    public static void save(File saveFile) {
    }
    public String getLessonPlanTitle(){
        return lessonPlanTitle;
    }


    public List<String> getEventNames() {
        return eventNames;
    }

    public void setEventNames(List<String> eventNames) {
        this.eventNames = eventNames;
    }

    public void addEventName(String name, int index){
        
    }


    private static boolean hasBeenLoaded = false;

    public void addToEvent(Card card, int num){
        List<Card> eventCards = eventList.get(num);
        eventCards.add(card);
    }
    public void deleteFromEvent(Card card, int num){
        List<Card> eventCards = eventList.get(num-1);
        eventCards.remove(card);
    }

    public List<Card> getEventCards(int index){
        return eventList.get(index);
    }

    public void printEverything(){
        String eventsName = null;
        for(String name : eventNames){
            eventsName += name + " ";
        }
        System.out.println(lessonPlanTitle + " " + eventsName);
        for(List<Card> eventCards : eventList){
            if (!(eventCards.isEmpty())) {
                System.out.println(eventCards.get(0).toString());
            } else{
                System.out.println("event is empty");
            }
        }
    }


    public void loadPlanFromFile(ArrayList<String> loadedLessonPlan) {
        if (GymnasticsAppBeta.getLoaded() && !hasBeenLoaded) {
            System.out.println("LOADING FROM FILE");
            CardDatabase.getAllCards();
            resetLessonPlan();
            if(eventList.isEmpty()){
                System.out.println("clear");
            }else {
                for(List<Card> event : eventList) {
                    for (Card card : event) {
                        System.out.println(card.getTitle());
                    }
                }
            }
            lessonPlanTitle = loadedLessonPlan.remove(0);

            for(int i = 0; i < loadedLessonPlan.size(); i++){
                int eventNumber = 0;
                if(loadedLessonPlan.get(i).equals("end")) {
                    loadedLessonPlan.remove(i);
                    eventNumber++;
                }else{
                    Card card = CardDatabase.getCardByID(loadedLessonPlan.remove(i));
                    if(eventList.size() < eventNumber){
                        eventList.add(new ArrayList<Card>());
                    }
                    eventList.get(eventNumber).add(card);
                    System.out.println(card.getTitle());
                    i--;
                }
            }
            System.out.println("");
            GymnasticsAppBeta.setLessonPlan(this);
            MainEditDisplayController.addTreeCardItem(eventList);
            hasBeenLoaded = true;
        }
    }

    public Card getCardFromTreeItem(String value, int treeNum) {
        List<Card> event = eventList.get(treeNum);
        for(Card card : event){
            if(card.getTitle().equals(value)){
                return card;
            }
        }

        //return eventOneTreeCards.get(0);
        return null;
    }
    public static void resetBoolean(){
        hasBeenLoaded = false;
    }

    public void resetLessonPlan(){
        lessonPlanTitle = null;
        eventList.clear();
        eventNames.clear();
    }

    public static LessonPlan loadCourseFile(File courseFile) {
        return null;
    }

}
