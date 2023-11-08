package csc305.gymnasticsApp.CardFilter;

import csc305.gymnasticsApp.Card;
import java.util.ArrayList;
import java.util.List;
/**
 * The ModelGenderFilter class is an implementation of the CardFilter interface
 * designed to filter Card objects based on a desired model gender.
 */

public class ModelGenderFilter implements CardFilter {
    public static List<String> selectedModelGender;
    public ModelGenderFilter() {
        if (selectedModelGender == null) {
            selectedModelGender = new ArrayList<>();
        }
    }

    public void add(String desiredModelGender) {
        if (!(selectedModelGender.contains(desiredModelGender))) {
            selectedModelGender.add(desiredModelGender);
        } else {
            selectedModelGender.remove(desiredModelGender);
        }
    }

    public List<String> getSelectedModelGender() {
        return selectedModelGender;
    }

    @Override
    public void reset() {
        selectedModelGender.clear();
    }

    /**
     * Determines whether a given Card matches the desired model gender.
     *
     * @param canidateCard - The Card to be evaluated for a model gender match.
     * @return true if the candidateCard's model gender matches the desired model gender; otherwise, false.
     */
    @Override
    public boolean matches(Card canidateCard) {
        boolean match = false;
        if (selectedModelGender.isEmpty()) {
            match = true;
        }
        for (String modelGender : selectedModelGender) {
            if (modelGender.equalsIgnoreCase(canidateCard.getModelGender())) {
                match = true;
            }
        }
        return match;

    }
}
