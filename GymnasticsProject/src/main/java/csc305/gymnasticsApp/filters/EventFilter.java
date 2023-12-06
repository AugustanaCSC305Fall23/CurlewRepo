package csc305.gymnasticsApp.filters;

import csc305.gymnasticsApp.data.Card;

import java.util.ArrayList;
import java.util.List;

public class EventFilter implements CardFilter{
    private final String desiredEvent;

    public EventFilter(String desiredEvent) {
        this.desiredEvent = desiredEvent;
    }

    public boolean matches(Card card) {
        return desiredEvent.equalsIgnoreCase("All") || desiredEvent.equalsIgnoreCase(card.getEvent());
    }
}
