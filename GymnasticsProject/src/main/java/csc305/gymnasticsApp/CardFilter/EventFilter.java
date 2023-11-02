package csc305.gymnasticsApp.CardFilter;

import csc305.gymnasticsApp.Card;

import java.util.ArrayList;
import java.util.List;

public class EventFilter implements CardFilter{
    private List<String> desiredEvent;
    public EventFilter() {
        desiredEvent = new ArrayList<String>();
    }

    public void add(String event) {
        desiredEvent.add(event);
    }

    @Override
    public boolean matches(Card canidateCard) {
        return(canidateCard.getEvent().equals(desiredEvent));

    }
}
