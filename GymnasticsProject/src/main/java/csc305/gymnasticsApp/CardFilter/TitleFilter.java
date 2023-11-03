package csc305.gymnasticsApp.CardFilter;

import csc305.gymnasticsApp.Card;
/**
 * The TitleFilter class is an implementation of the CardFilter interface
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
}
