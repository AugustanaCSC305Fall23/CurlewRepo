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

    public LevelFilter(String desiredLevel) {
        this.desiredLevel = desiredLevel;
    }

    public boolean matches(Card card) {
        return desiredLevel.equalsIgnoreCase("All") || desiredLevel.toUpperCase().contains(card.getLevel().toUpperCase());
    }
}
