package csc305.gymnasticsApp.filters;

import csc305.gymnasticsApp.data.Card;

/**
 * The filters interface defines the contract for filtering Card object.
 * Classes implementing this interface can provide custom filtering logic to detect
 * whether a given Card matches specific criteria.
 */
public interface CardFilter {
    /**
     * Default implementation of the filters that always returns true.
     * Subclasses should override this method to provide specific filtering logic.
     *
     * @param canidateCard - The Card to be evaluated for a match.
     * @return true if the candidateCard matches the filter criteria; otherwise it returns false.
     */
    default boolean matches(Card canidateCard) {
        return true;
    }


    abstract void reset();
}

//pass in an instance of any filter
//must pass in a card filter
//any card filter
//controller will call cardDatabase and cardDatabase will call the filters
