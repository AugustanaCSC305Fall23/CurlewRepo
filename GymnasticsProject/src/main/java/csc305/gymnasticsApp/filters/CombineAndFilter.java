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

    public CombineAndFilter(CardFilter... filters) {
        this.filters = filters;
    }

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
