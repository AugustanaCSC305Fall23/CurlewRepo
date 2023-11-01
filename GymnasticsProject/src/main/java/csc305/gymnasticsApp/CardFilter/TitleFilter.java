package csc305.gymnasticsApp.CardFilter;

import csc305.gymnasticsApp.Card;

public class TitleFilter implements CardFilter{
    private String desiredTitle;
    public TitleFilter(String desiredTitle) {
        this.desiredTitle = desiredTitle;
    }

    @Override
    public boolean matches(Card canidateCard) {
        return(canidateCard.getTitle().equalsIgnoreCase(desiredTitle));

    }
}
