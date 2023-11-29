package csc305.gymnasticsApp.filters;

import csc305.gymnasticsApp.data.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * The GenderFilter class is an implementation of the filters interface
 *  * designed to filter Card objects based on desired gender criteria.
 */
public class GenderFilter implements CardFilter {
    public static List<String> desiredGenders;

    /**
     * Constructs a new GenderFilter and initializes the list of desired genders.
     *      * If the list of desired genders is null, it is created as an empty list.
     */
    public GenderFilter() {
        if (desiredGenders == null) {
            desiredGenders = new ArrayList<>();
        }
    }



    /**
     * Gets the list of desired genders that have been added to this filter.
     *
     * @return - A list of desired gender criteria.
     */
    public List<String> getDesiredGenders() {
        return desiredGenders;
    }

    /**
     * Adds or removes a desired gender to/from the list of desired genders.
     *
     * @param desiredGender The gender criteria to add or remove from the list.
     */
    public void add(String desiredGender) {
        if(!(desiredGenders.contains(desiredGender))) {
            desiredGenders.add(desiredGender);
        } else {
            desiredGenders.remove(desiredGender);
        }
    }

    public void reset(){
        desiredGenders.clear();
    }
    /**
     * Determines whether a given Card matches the list of desired genders.
     *
     * @param canidateCard - The Card to be evaluated for a gender match.
     * @return true if the candidateCard's gender matches any of the desired genders; otherwise returns false.
     */
    @Override
    public boolean matches(Card canidateCard) {
        boolean match = false;
        if(desiredGenders.isEmpty()) {
            match = true;
        }
        for(String filter:desiredGenders){
            if(filter.equalsIgnoreCase(canidateCard.getGender())){
                match = true;
            }
        }
        return match;

    }
}
