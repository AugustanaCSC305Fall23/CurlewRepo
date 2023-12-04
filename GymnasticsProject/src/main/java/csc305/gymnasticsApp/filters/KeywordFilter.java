package csc305.gymnasticsApp.filters;

import csc305.gymnasticsApp.data.Card;

/**
 * The KeywordFilter class is an implementation of the filters interface
 *  * designed to filter Card objects based on a desired keyword.
 */
public class KeywordFilter implements CardFilter{
    private final String searchText;

    public KeywordFilter(String searchText) {
        this.searchText = searchText;
    }
    @Override
    public boolean matches(Card card) {
        return card.getTitle().toUpperCase().contains(searchText.toUpperCase()) || card.getCode().toUpperCase().contains(searchText.toUpperCase())
                || card.getKeywords().toUpperCase().contains(searchText.toUpperCase()) || card.getCategory().toUpperCase().contains(searchText.toUpperCase());
    }
}
