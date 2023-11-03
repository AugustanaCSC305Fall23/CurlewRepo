package csc305.gymnasticsApp.CardFilter;

import csc305.gymnasticsApp.Card;
/**
 * The ModelGenderFilter class is an implementation of the CardFilter interface
 * designed to filter Card objects based on a desired model gender.
 */
public class ModelGenderFilter implements CardFilter {
    private String desiredModelGender;
    /**
     * Constructs a new ModelGenderFilter with the desired model gender to match.
     *
     * @param desiredModelGender The model gender that Card objects should match.
     */
    public ModelGenderFilter(String desiredModelGender) {
        this.desiredModelGender = desiredModelGender;
    }
    /**
     * Determines whether a given Card matches the desired model gender.
     *
     * @param canidateCard - The Card to be evaluated for a model gender match.
     * @return true if the candidateCard's model gender matches the desired model gender; otherwise, false.
     */
    @Override
    public boolean matches(Card canidateCard) {
        return(canidateCard.getModelGender().equalsIgnoreCase(desiredModelGender));

    }
}
