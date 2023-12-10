package csc305.gymnasticsApp.filters;

import csc305.gymnasticsApp.data.Card;
import csc305.gymnasticsApp.data.FavoriteCollection;
/**
 * The FavFilter class implements the CardFilter interface to filter cards based on their favorite status.
 * It allows filtering cards by whether they are favorites or non-favorites.
 */
public class FavFilter implements CardFilter {
    private final boolean desiredFavorite;

    /**
     * Constructs a new FavFilter with the specified favorite status.
     *
     * @param desiredFavorite True if filtering for favorites, false if filtering for non-favorites.
     */
    public FavFilter(boolean desiredFavorite) {
        this.desiredFavorite = desiredFavorite;
    }

    /**
     * Determines whether a card matches the filter criteria.
     * A card matches if its unique ID is in the set of favorites or if filtering for non-favorites.
     *
     * @param card The card to be checked against the filter criteria.
     * @return True if the card matches the filter criteria, false otherwise.
     */
    public boolean matches(Card card) {
        return (FavoriteCollection.getInstance().getFavoriteSet().contains(card.getUniqueID()) || !desiredFavorite);
    }
}
