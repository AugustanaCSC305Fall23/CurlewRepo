package csc305.gymnasticsApp.filters;

import csc305.gymnasticsApp.data.Card;

import java.util.Arrays;
import java.util.List;

/**
 * The CombineAndFilter class is an implementation of the filters interface
 * designed to combine and filter Card objects based on multiple criteria.
 * This class is intended to be used to combine and filter Card objects based on
 * the criteria defined in different filters such as GenderFilter and EventFilter.
 */
public class CombineAndFilter implements CardFilter {

    CardFilter[] filters;

    /**
     * Constructs a CombineAndFilter with the specified CardFilter objects
     *
     * @param filters The CardFilter objects to be combined
     */
    public CombineAndFilter(CardFilter... filters) {
        this.filters = filters;
    }

    /**
     * Checks if a Card object matches all the criteria defined by the combined filters
     *
     * @param card - The Card to be evaluated for a match.
     * @return true if the card matches all criteria, false otherwise
     */
    @Override
    public boolean matches(Card card) {
        for (CardFilter filter : filters) {
            if (!filter.matches(card)) {
                return false;
            }
        }
        return true;
    }
}
