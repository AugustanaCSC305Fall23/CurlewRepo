package csc305.gymnasticsApp.filters;

import csc305.gymnasticsApp.data.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * The EventFilter class is an implementation of the filters interface
 * designed to filter Card objects based on a desired event keyword
 */
public class EventFilter implements CardFilter{
    private final String desiredEvent;

    /**
     * Constructs an EventFilter with the specified desired event keyword
     *
     * @param desiredEvent The event keyword to filter by
     */
    public EventFilter(String desiredEvent) {
        this.desiredEvent = desiredEvent;
    }

    /**
     * Checks if a Card object's event matches the desired event keyword
     *
     * @param card - The Card to be evaluated for a match.
     * @return true if the card's event matches the desired keyword, false otherwise
     */
    public boolean matches(Card card) {
        return (desiredEvent.equalsIgnoreCase("All") ||
                desiredEvent.equalsIgnoreCase(card.getEvent())||
                card.getEvent().equalsIgnoreCase("All"));
    }
}
