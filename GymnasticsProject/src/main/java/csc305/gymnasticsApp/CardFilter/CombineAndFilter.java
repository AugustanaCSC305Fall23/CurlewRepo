package csc305.gymnasticsApp.CardFilter;

import csc305.gymnasticsApp.Card;
import csc305.gymnasticsApp.CardFilter.CardFilter;

import java.util.List;

public class CombineAndFilter implements CardFilter {

    private List<CardFilter> filters;

    public void combineAndFilter(List<CardFilter> filters) {
        for (int i = 0; i < filters.size(); i++) {

        }
    }

    @Override
    public boolean matches(Card canidateCard) {
        return true;
    }
}
