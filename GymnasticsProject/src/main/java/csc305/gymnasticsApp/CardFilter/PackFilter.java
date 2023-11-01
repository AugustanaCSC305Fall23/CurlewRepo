package csc305.gymnasticsApp.CardFilter;

import csc305.gymnasticsApp.Card;

public class PackFilter implements CardFilter{
    private String desiredPack;
    public PackFilter(String desiredPack) {
        this.desiredPack = desiredPack;
    }

    @Override
    public boolean matches(Card canidateCard) {
        return(canidateCard.getPackFolder().equalsIgnoreCase(desiredPack));

    }
}
