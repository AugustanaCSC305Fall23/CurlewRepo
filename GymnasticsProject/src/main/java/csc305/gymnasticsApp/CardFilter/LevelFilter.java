package csc305.gymnasticsApp.CardFilter;

import csc305.gymnasticsApp.Card;

public class LevelFilter implements CardFilter{
    private String desiredLevel;
    public LevelFilter(String desiredLevel) {
        this.desiredLevel = desiredLevel;
    }

    @Override
    public boolean matches(Card canidateCard) {
        return(canidateCard.getLevel().equalsIgnoreCase(desiredLevel));

    }
}
