package csc305.gymnasticsApp.CardFilter;

import csc305.gymnasticsApp.Card;
import csc305.gymnasticsApp.CardFilter.CardFilter;

public class GenderFilter implements CardFilter {
    private String desiredGender;
    public GenderFilter(String desiredGender) {
        this.desiredGender = desiredGender;
    }

    @Override
    public boolean matches(Card canidateCard) {
        return(canidateCard.getGender().equalsIgnoreCase(desiredGender));

    }
}
