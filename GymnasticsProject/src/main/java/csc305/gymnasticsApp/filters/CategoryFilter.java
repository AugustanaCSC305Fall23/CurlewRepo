package csc305.gymnasticsApp.filters;

import csc305.gymnasticsApp.data.Card;

/**
 * The CategoryFilter class is an implementation of the filters interface
 * designed to filter Card objects based on the desired category.
 */
public class CategoryFilter implements CardFilter{
    private String desiredCategory;

    /**
     * Constructs a new CategoryFilter with the desired category.
     *
     * @param desiredCategory - The desired category that the Card objects should match.
     */
    public CategoryFilter(String desiredCategory) {
        this.desiredCategory = desiredCategory;
    }

    public void reset(){};

    /**
     * Determines whether a given Card matches the desired category.
     *
     * @param canidateCard - The Card to be evaluated for a match.
     * @return true if the candidateCard's desired category matches the desired category.
     */
    @Override
    public boolean matches(Card canidateCard) {
        return(canidateCard.getCategory().equalsIgnoreCase(desiredCategory));

    }
}
