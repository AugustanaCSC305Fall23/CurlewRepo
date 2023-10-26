package csc305.gymnasticsApp.CardFilter;

import csc305.gymnasticsApp.Card;

public interface CardFilter {

    default boolean matches(Card canidateCard) {
        return true;
    }
}

//pass in an instance of any filter
//must pass in a card filter
//any card filter
//controller will call cardDatabase and cardDatabase will call the filters
