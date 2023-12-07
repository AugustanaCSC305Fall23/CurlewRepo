package csc305.gymnasticsApp.filters;

import csc305.gymnasticsApp.data.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * The GenderFilter class is an implementation of the filters interface
 *  * designed to filter Card objects based on desired gender criteria.
 */
public class GenderFilter implements CardFilter {
    private final String desiredGender;

    /**
     * Constructs a GenderFilter with the specified desired gender criteria
     *
     * @param desiredGender The gender criteria to filter by
     */
    public GenderFilter(String desiredGender) {
        this.desiredGender = desiredGender;
    }

    /**
     * Checks if a Card object's gender matches the desired gender criteria
     *
     * @param card - The Card to be evaluated for a match.
     * @return true if the card's gender matches the desired criteria, false otherwise
     */
    public boolean matches(Card card) {
        return desiredGender.equalsIgnoreCase(card.getGender()) || card.getGender().equalsIgnoreCase("N");
    }
}
