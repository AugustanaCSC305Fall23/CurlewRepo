package csc305.gymnasticsApp.filters;

import csc305.gymnasticsApp.data.Card;
/**
 * The TitleFilter class is an implementation of the filters interface
 * designed to filter Card objects based on a desired title.
 */
public class TitleFilter implements CardFilter{
    private String desiredTitle;
    /**
     * Constructs a new TitleFilter with the desired title to match.
     *
     * @param desiredTitle The title that Card objects should match.
     */
    public TitleFilter(String desiredTitle) {
        this.desiredTitle = desiredTitle;
    }
    /**
     * Determines whether a given Card matches the desired title.
     *
     * @param canidateCard - The Card to be evaluated for a title match.
     * @return true if the candidateCard's title matches the desired title; otherwise, false.
     */
    @Override
    public boolean matches(Card canidateCard) {
        return(canidateCard.getTitle().equalsIgnoreCase(desiredTitle));

    }

    @Override
    public void reset() {}
}
