package csc305.gymnasticsApp.filters;

import csc305.gymnasticsApp.data.Card;
import csc305.gymnasticsApp.data.FavoriteCollection;

public class FavFilter implements CardFilter {
    private final boolean desiredFavorite;

    public FavFilter(boolean desiredFavorite) {
        this.desiredFavorite = desiredFavorite;
    }

    public boolean matches(Card card) {
        return (FavoriteCollection.getInstance().getFavoriteSet().contains(card.getUniqueID()) || !desiredFavorite);
    }
}
