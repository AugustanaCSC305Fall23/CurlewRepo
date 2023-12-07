package csc305.gymnasticsApp.filters;

import csc305.gymnasticsApp.data.Card;

/**
 * The SearchBarFilter class is an implementation of the filters interface
 * designned to filter Card objects based on a search text
 */
public class SearchBarFilter implements CardFilter{

    private final String searchText;

    /**
     * Constructs a SearchBarFilter with the specified search text
     *
     * @param searchText The search text to filter by
     */
    public SearchBarFilter(String searchText) {
        this.searchText = searchText;
    }

    /**
     * Checks if a Card object's unique ID contains the search text
     *
     * @param card - The Card to be evaluated for a match.
     * @return true if the card's unique ID contains the search text, false otherwise
     */
    @Override
    public boolean matches(Card card) {
        return card.getUniqueID().toUpperCase().contains(searchText.toUpperCase());

//        card.getTitle().toUpperCase().contains(searchText.toUpperCase())
//                || card.getCategory().toUpperCase().contains(searchText.toUpperCase())
//                || card.getKeywords().toUpperCase().contains(searchText.toUpperCase())
//                || card.getCode().toUpperCase().contains(searchText.toUpperCase())
//                || card.getEvent().toUpperCase().contains(searchText.toUpperCase());
    }
}
