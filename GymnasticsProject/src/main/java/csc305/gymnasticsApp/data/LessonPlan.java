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
public class LessonPlan implements Cloneable{
    private String lessonPlanTitle;

    private List<String> eventNames = new ArrayList<>();

    private List<List<Card>> eventList;

    private LessonPlan thePlan;

    /**
     * Constructs a new LessonPlan with an empty list of events
     */
    public LessonPlan() {
        thePlan = this;
        eventList = new ArrayList<>();
    }

    /**
     * Gets the list of events in the lesson plan
     *
     * @return The list of events in the lesson plan
     */
    public List<List<Card>> getEventList() {
        return eventList;
    }

    /**
     * Sets the list of events in the lesson plan
     *
     * @param eventList The list of events to set
     */
    public void setEventList(List<List<Card>> eventList) {
        this.eventList = eventList;
    }

    /**
     * Adds a list of cards to the event list
     *
     * @param event The list of cards to add to the event list
     */
    public void addToEventList(List<Card> event){
        eventList.add(event);
    }

    /**
     * Adds all events from a new list to the event list
     *
     * @param newEventList The list of events to add
     */
    public void addAllToEventList(List<List<Card>> newEventList){
        eventList.addAll(newEventList);
    }

    /**
     * Clears the event list
     */
    public void clearEventList(){
        eventList.clear();
    }

    /**
     * Sets the title of the lesson plan
     *
     * @param title The title to set
     */
    public void setLessonPlanTitle(String title){
        lessonPlanTitle = title;
    }

    /**
     * Sets the name of an event at a specific index
     *
     * @param name The name to set
     * @param index The index of the event
     */
    public void setEventName(String name, int index){
        eventNames.set(index, name);
    }

    /**
     * Saves the lesson plan to a file
     *
     * @param saveFile The file to save the lesson plan to
     */
    public static void save(File saveFile) {
    }

    /**
     * Gets the title of the lesson plan
     *
     * @return The title of the lesson plan
     */
    public String getLessonPlanTitle(){
        return lessonPlanTitle;
    }

    /**
     * Gets the list of event names
     *
     * @return The list of event names
     */
    public List<String> getEventNames() {
        return eventNames;
    }

    /**
     * Sets the list of event names
     *
     * @param eventNames The list of event names to set
     */
    public void setEventNames(List<String> eventNames) {
        this.eventNames = eventNames;
    }

    /**
     * Changes the name of an event at a specified index
     *
     * @param name The new name to set
     * @param index The index of the event
     */
    public void changeEventName(String name, int index){
        eventNames.set(index, name);
    }

    /**
     * Adds a name to the list of event names
     *
     * @param name The name to add
     */
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
    }

    /**
     * Loads a lesson plan from a file
     *
     * @param loadedLessonPlan The loaded lesson plan data
     */
    public void loadPlanFromFile(ArrayList<String> loadedLessonPlan) {
        if (GymnasticsAppBeta.getLoaded() && !hasBeenLoaded) {
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
            MainEditDisplayController.addTreeCardItems(this);
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
     * Retrieves the lesson plan
     * @return the lesson plan
     */
    public LessonPlan getThePlan(){
        return thePlan;
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

    /**
     * Clones the current lesson plan
     *
     * @return A cloned instance of the lesson plan
     */
    public LessonPlan clone() {
        try {
            LessonPlan clone = (LessonPlan) super.clone();
            clone.lessonPlanTitle = lessonPlanTitle;
            clone.eventNames = new ArrayList<String>();
            clone.eventList = new ArrayList<List<Card>>();
            if(eventNames.isEmpty()){
                eventNames.add("Event 1");
            }
            for (String name : eventNames) {
                clone.eventNames.add(name);
            }
            for(List<Card> cardList : eventList){
                List<Card> clonedList = new ArrayList<>();
                for(Card card : cardList){
                    clonedList.add(card.clone());
                }
                clone.eventList.add(clonedList);

            }
            return clone;
        } catch (CloneNotSupportedException e) {
            // should never happen
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Creates a memento for the current state of the LessonPlan
     * @return
     */
    public State createMemento() {
        return new State();
    }

    /**
     * Restores the LessonPlan to a specific state represented by the provided memento.
     *
     * @param lessonPlan The memento representing the state to be restored.
     */
    public void restoreState(LessonPlan.State lessonPlan) {
        lessonPlan.restore();
        resetDisplay();
    }

    /**
     * Resets the display after restoring the lesson plan state.
     * Prints for UNDO, sets the lesson plan in GymnasticsAppBeta, clears and adds tree card items,
     * and clears and resets alert buttons in the MainEditDisplayController.
     */
    private void resetDisplay(){
        LessonPlan.this.thePlan.printEverything();
        GymnasticsAppBeta.setLessonPlan(LessonPlan.this.thePlan);
        MainEditDisplayController.clearTreeCardItems();
        MainEditDisplayController.addTreeCardItems(LessonPlan.this.thePlan);
        MainEditDisplayController.clearAndResetAlertButtons();
    }

    /**
     * The State class represents a memento for the state of a LessonPlan
     * It is used for undo functionality to restore the lesson plan to a previous state
     */
    public class State {
        private LessonPlan lessonPlan;

        /**
         * Constructs a new State object by creating a memento for the current LessonPlan
         */
        public State() {
            lessonPlan = (LessonPlan) LessonPlan.this.thePlan.clone();
        }

        /**
         * Restores the LessonPlan to the state stored in this memento
         */
        public void restore() {
            LessonPlan.this.thePlan = (LessonPlan) lessonPlan.clone();

        }
    }

    /**
     * Gets the entire lesson plan as text, including titles, event names, and details.
     *
     * @return A string representation of the entire lesson plan.
     */
    public String getEntirePlanAsText(){
        String allText = lessonPlanTitle + "\n";
        for(int i = 0; i < eventList.size(); i++){
            allText = allText + "    " + eventNames.get(i) + "\n";
            for(int h = 0; h < eventList.get(i).size(); h++){
                allText = allText + "        " + '•' + " " + eventList.get(i).get(h).getCode() + "  " + eventList.get(i).get(h).getTitle() + "\n";
            }
        }
        return allText;
    }

}


