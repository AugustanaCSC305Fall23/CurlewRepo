package csc305.gymnasticsApp.CardFilter;

import csc305.gymnasticsApp.Card;

import java.util.ArrayList;
import java.util.List;

public class EventFilter implements CardFilter{
    public static List<String> desiredEvents;
    public EventFilter() {
        if (desiredEvents == null) {
            desiredEvents = new ArrayList<String>();
        }
    }

    public void add(String event) {
        if (!(desiredEvents.contains(event))) {
            desiredEvents.add(event);
        } else {
            desiredEvents.remove(event);
        }
    }

    public void reset(){
        desiredEvents.clear();
        desiredEvents.add("ALL");
    }

    public List<String> getDesiredEvents(){
        return desiredEvents;
    }

    @Override
    public boolean matches(Card canidateCard) {
        boolean match = false;
        if(desiredEvents.size() == 1) {
            match = true;
        }else if(canidateCard.getEvent().equalsIgnoreCase("ALL")){
            match = true;
        }else {
            for (String filter : desiredEvents) {
                if (filter.equalsIgnoreCase(canidateCard.getEvent())) {
                    match = true;
                }
            }
        }
        return match;

    }
}
