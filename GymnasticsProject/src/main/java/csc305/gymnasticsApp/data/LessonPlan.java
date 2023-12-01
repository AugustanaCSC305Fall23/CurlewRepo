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
        System.out.println("This is the index in setEventName" + index);
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

    public void changeEventName(String name, int index){
        eventNames.set(index, name);
    }

    public void addEventName(String name){
        eventNames.add(name);
    }


    private static boolean hasBeenLoaded = false;

    public void addToEvent(Card card, int num){
        List<Card> eventCards = eventList.get(num);
        eventCards.add(card);
    }
    public void deleteFromEvent(int index, int num){
        eventList.get(index).remove(num);
    }

    public List<Card> getEventCards(int index){
        return eventList.get(index);
    }

    public void printEverything(){
        String eventsName = "";
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
            lessonPlanTitle = loadedLessonPlan.remove(0);

            int eventNumber = 0;
            int sizeOfLoadedLessonPlan = loadedLessonPlan.size();
            //i is the line in the textFile
            for(int i = 0; i < sizeOfLoadedLessonPlan; i++){
                //checks if the next line is a card or still an event
                if(loadedLessonPlan.get(i).equals("end")) {
                    loadedLessonPlan.get(i);
                    eventNumber = eventNumber + 1;
                }else if(!(CardDatabase.getAllCards().contains(CardDatabase.getCardByID(loadedLessonPlan.get(i))))){
                    List<Card> eventCards = new ArrayList<>();
                    addToEventList(eventCards);
                    eventNames.add(loadedLessonPlan.get(i));
                } else{
                    Card card = CardDatabase.getCardByID(loadedLessonPlan.get(i));
                    addToEvent(card, eventNumber);
                }
            }
            GymnasticsAppBeta.setLessonPlan(this);
            MainEditDisplayController.addTreeCardItem(eventList);
            hasBeenLoaded = true;
        }
    }

    public Card getCardFromTreeItem(String value, int treeNum) {
        for(Card card : eventList.get(treeNum - 1)){
            if(card.getCode().equals(value)){
                return card;
            }
        }
        System.out.println("Didnt get card from tree item");
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
