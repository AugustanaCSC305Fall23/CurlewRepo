package csc305.gymnasticsApp.CardFilter;

import csc305.gymnasticsApp.Card;

public class EventFilter implements CardFilter{
    private String desiredEvent;
    public EventFilter(String desiredEvent) {
        this.desiredEvent = desiredEvent;
    }

    @Override
    public boolean matches(Card canidateCard) {
        return(canidateCard.getEvent().equals(desiredEvent));

    }
}
