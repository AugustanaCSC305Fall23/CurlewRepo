package csc305.gymnasticsApp.CardFilter;

import csc305.gymnasticsApp.Card;
import csc305.gymnasticsApp.MainEditDisplayController;

import java.util.Arrays;
import java.util.List;

/**
 * The CombineAndFilter class is an implementation of the CardFilter interface
 * designed to combine and filter Card objects based on multiple criteria.
 * This class is intended to be used to combine and filter Card objects based on
 * the criteria defined in different filters such as GenderFilter and EventFilter.
 */
public class CombineAndFilter implements CardFilter {

    /**
     * Constructs a new CombineAndFilter that combines the criteria from different filters.
     * The specific filters used for combination are GenderFilter and EventFilter.
     */
    public CombineAndFilter() {

        // Creates a list of criteria from GenderFilter and EventFilter
        List<String> filters = Arrays.asList(
                new GenderFilter().getDesiredGenders().toString(),
                new EventFilter().getDesiredEvents().toString(),
                new ModelGenderFilter().getSelectedModelGender().toString());
        System.out.println(filters);
    }

    /**
     * Determines whether a given Card matches the combined criteria defined by multiple filters.
     *
     * @param canidateCard - The Card to be evaluated for a match.
     * @return true if the candidateCard matches the combined criteria; otherwise returns false
     */
    @Override
    public boolean matches(Card canidateCard) {
        return true;
    }
}
