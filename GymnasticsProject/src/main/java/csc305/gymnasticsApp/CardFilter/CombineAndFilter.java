package csc305.gymnasticsApp.CardFilter;

import csc305.gymnasticsApp.Card;
import csc305.gymnasticsApp.CardFilter.CardFilter;

import java.util.ArrayList;
import java.util.List;

public class CombineAndFilter implements CardFilter {

    private List<CardFilter> filters;


    public void combineAndFilter() {
        
    }

    @Override
    public boolean matches(Card canidateCard) {
        return true;
    }
}
