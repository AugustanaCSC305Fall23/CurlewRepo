package csc305.gymnasticsApp.filters;

import csc305.gymnasticsApp.data.Card;
import java.util.ArrayList;
import java.util.List;
/**
 * The ModelGenderFilter class is an implementation of the filters interface
 * designed to filter Card objects based on a desired model gender.
 */

public class ModelGenderFilter implements CardFilter {
    private final String desiredModelGender;

    /**
     * Constructs a ModelGenderFilter with the specified desired model gender criteria
     *
     * @param desiredModelGender The model gender criteria to fitler by
     */
    public ModelGenderFilter(String desiredModelGender) {
        this.desiredModelGender = desiredModelGender;
    }


    @Override
    public boolean matches(Card card) {
        return (desiredModelGender.equalsIgnoreCase("All") || desiredModelGender.equalsIgnoreCase(card.getModelGender()));
    }
}
