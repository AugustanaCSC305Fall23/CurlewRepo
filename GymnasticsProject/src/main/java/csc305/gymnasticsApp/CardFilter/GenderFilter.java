package csc305.gymnasticsApp.CardFilter;

import csc305.gymnasticsApp.Card;
import csc305.gymnasticsApp.CardFilter.CardFilter;
import csc305.gymnasticsApp.MainEditDisplayController;

public class GenderFilter implements CardFilter {
    private String desiredGender;
    public GenderFilter(String desiredGender) {
        this.desiredGender = desiredGender;

    }

    public String getDesiredGender() {
        return desiredGender;
    }

    @Override
    public boolean matches(Card canidateCard) {
        return(canidateCard.getGender().equalsIgnoreCase(desiredGender));

    }
}
