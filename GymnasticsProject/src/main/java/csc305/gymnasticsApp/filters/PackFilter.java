package csc305.gymnasticsApp.filters;

import csc305.gymnasticsApp.data.Card;
/**
 * The PackFilter class is an implementation of the filters interface
 * designed to filter Card objects based on a desired pack folder.
 */
public class PackFilter implements CardFilter{
    private String desiredPack;
    /**
     * Constructs a new PackFilter with the desired pack folder to match.
     *
     * @param desiredPack The pack folder that Card objects should match.
     */
    public PackFilter(String desiredPack) {
        this.desiredPack = desiredPack;
    }
    /**
     * Determines whether a given Card matches the desired pack folder.
     *
     * @param canidateCard  The Card to be evaluated for a pack folder match.
     * @return true if the candidateCard's pack folder matches the desired pack folder; otherwise, false.
     */
    @Override
    public boolean matches(Card canidateCard) {
        return(canidateCard.getPackFolder().equalsIgnoreCase(desiredPack));

    }

}
