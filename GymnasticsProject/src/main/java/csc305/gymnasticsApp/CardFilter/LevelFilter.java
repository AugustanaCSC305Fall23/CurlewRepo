package csc305.gymnasticsApp.CardFilter;

import csc305.gymnasticsApp.Card;
/**
 * The LevelFilter class is an implementation of the CardFilter interface
 * designed to filter Card objects based on a desired level.
 */
public class LevelFilter implements CardFilter{
    private String desiredLevel;
    /**
     * Constructs a new LevelFilter with the desired level to match.
     *
     * @param desiredLevel - The level that Card objects should match.
     */
    public LevelFilter(String desiredLevel) {
        this.desiredLevel = desiredLevel;
    }
    /**
     * Determines whether a given Card matches the desired level.
     *
     * @param canidateCard - The Card to be evaluated for a level match.
     * @return true if the candidateCard's level matches the desired level; otherwise, false.
     */
    @Override
    public boolean matches(Card canidateCard) {
        return(canidateCard.getLevel().equalsIgnoreCase(desiredLevel));

    }
}
