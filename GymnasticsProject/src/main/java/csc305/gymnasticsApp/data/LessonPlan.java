package csc305.gymnasticsApp.data;

import csc305.gymnasticsApp.ui.GymnasticsAppBeta;
import csc305.gymnasticsApp.ui.MainEditDisplayController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The LessonPlan class represents a collection of gymnastics lesson plans.
 * It includes methods for managing lesson plans, such as adding events, setting title, and loading from files.
 */
public class LessonPlan {
    //private List<Course> plan;
    private String lessonPlanTitle;

    private List<String> eventNames = new ArrayList<>();

    private List<List<Card>> eventList;

    /**
     * Constructs a new LessonPlan with an empty list of events
     */
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

    /**
     * Adds a card to a specific event in the lesson plan
     *
     * @param card The card to add to the event
     * @param num The index of the event in the lesson plan
     */
    public void addToEvent(Card card, int num){
        List<Card> eventCards = eventList.get(num);
        eventCards.add(card);
    }

    /**
     * Deletes a card from a specific event in the lesson plan
     *
     * @param index The index of the card to delete in the event
     * @param num The index of the event in the lesson plan
     */
    public void deleteFromEvent(int index, int num){
        eventList.get(index).remove(num);
    }

    /**
     * Retrieves the list of cards for a specific event in the lesson plan
     *
     * @param index The index of the event in the lesson plan
     * @return A list of cards for the specified event
     */
    public List<Card> getEventCards(int index){
        return eventList.get(index);
    }

    /**
     * Prints information about the lesson plan and its events to the console
     */
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

    /**
     * Loads a lesson plan from a file
     *
     * @param loadedLessonPlan The loaded lesson plan data
     */
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

    /**
     * Retrieves a card from a specific event based on its value and event index
     *
     * @param value The value to match
     * @param treeNum The index of the event in the lesson plan
     * @return The card with the specified value in the specified event
     */
    public Card getCardFromTreeItem(String value, int treeNum) {
        for(Card card : eventList.get(treeNum - 1)){
            if(card.getCode().equals(value)){
                return card;
            }
        }
        System.out.println("Didnt get card from tree item");
        return null;
    }

    /**
     * Resets the boolean flag indicating whether the lesson plan has been loaded
     */
    public static void resetBoolean(){
        hasBeenLoaded = false;
    }

    /**
     * Resets the lesson plan by clearing its title, event list, and event names
     */
    public void resetLessonPlan(){
        lessonPlanTitle = null;
        eventList.clear();
        eventNames.clear();
    }

    /**
     * Loads a lesson plan from a file
     *
     * @param courseFile courseFile The file containing the lesson plan data
     * @return The loaded lesson plan
     */
    public static LessonPlan loadCourseFile(File courseFile) {
        return null;
    }

}
