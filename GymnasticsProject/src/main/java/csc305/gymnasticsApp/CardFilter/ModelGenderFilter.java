package csc305.gymnasticsApp.CardFilter;

import csc305.gymnasticsApp.Card;

import java.util.ArrayList;
import java.util.List;

public class ModelGenderFilter implements CardFilter {
    public static List<String> selectedModelGender;
    public ModelGenderFilter() {
        if (selectedModelGender == null) {
            selectedModelGender = new ArrayList<String>();
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
    public boolean matches(Card canidateCard) {
        return(canidateCard.getModelGender().equalsIgnoreCase(selectedModelGender.toString()));

    }
}
