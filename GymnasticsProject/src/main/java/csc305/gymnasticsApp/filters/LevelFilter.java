package csc305.gymnasticsApp.filters;

import csc305.gymnasticsApp.data.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * The LevelFilter class is an implementation of the filters interface
 * designed to filter Card objects based on a desired level.
 */
public class LevelFilter implements CardFilter{
    private final String desiredLevel;

    /**
     * Constructs a LevelFilter with the specified desired level criteria
     *
     * @param desiredLevel The level criteria to fitler by
     */
    public LevelFilter(String desiredLevel) {
        this.desiredLevel = desiredLevel;
    }

    /**
     * Checks if a Card object's level matches the desired level criteria
     *
     * @param card - The Card to be evaluated for a match.
     * @return true if the card's level matches the desired criteria, false otherwise
     */
    public boolean matches(Card card) {
        return desiredLevel.equalsIgnoreCase("All") || desiredLevel.toUpperCase().contains(card.getLevel().toUpperCase());
    }
}
