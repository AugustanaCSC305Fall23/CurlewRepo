package csc305.gymnasticsApp.CardFilter;

import csc305.gymnasticsApp.Card;
import csc305.gymnasticsApp.CardFilter.CardFilter;

public class GenderFilter implements CardFilter {

    public void GenderFilter(char desiredGender) {

    }

    @Override
    public boolean matches(Card canidateCard) {
        return true;
    }
}
