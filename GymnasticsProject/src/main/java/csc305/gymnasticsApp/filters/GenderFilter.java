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

    public GenderFilter(String desiredGender) {
        this.desiredGender = desiredGender;
    }

    public boolean matches(Card card) {
        return desiredGender == null || card.getGender().equalsIgnoreCase("N") || desiredGender.equalsIgnoreCase(card.getGender());
    }
}
