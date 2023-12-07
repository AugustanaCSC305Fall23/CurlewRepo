package csc305.gymnasticsApp.filters;

import csc305.gymnasticsApp.data.Card;

public class SearchBarFilter implements CardFilter{

    private final String searchText;

    public SearchBarFilter(String searchText) {
        this.searchText = searchText;
    }
    @Override
    public boolean matches(Card card) {
        return card.getTitle().toUpperCase().contains(searchText.toUpperCase())
                || card.getCategory().toUpperCase().contains(searchText.toUpperCase())
                || card.getKeywords().toUpperCase().contains(searchText.toUpperCase())
                || card.getCode().toUpperCase().contains(searchText.toUpperCase())
                || card.getEvent().toUpperCase().contains(searchText.toUpperCase());
    }
}
