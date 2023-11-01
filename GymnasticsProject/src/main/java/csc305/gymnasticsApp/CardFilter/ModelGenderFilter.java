package csc305.gymnasticsApp.CardFilter;

import csc305.gymnasticsApp.Card;

public class ModelGenderFilter implements CardFilter {
    private String desiredModelGender;
    public ModelGenderFilter(String desiredModelGender) {
        this.desiredModelGender = desiredModelGender;
    }

    @Override
    public boolean matches(Card canidateCard) {
        return(canidateCard.getModelGender().equalsIgnoreCase(desiredModelGender));

    }
}
