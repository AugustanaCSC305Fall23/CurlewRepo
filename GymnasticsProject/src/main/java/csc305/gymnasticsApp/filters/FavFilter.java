package csc305.gymnasticsApp.filters;

import csc305.gymnasticsApp.data.Card;

public class FavFilter implements CardFilter {
    private final boolean desiredFavorite;

    public FavFilter(boolean desiredFavorite) {
        this.desiredFavorite = desiredFavorite;
    }

    public boolean matches(Card card) {
        return card.isFavorite() == desiredFavorite;
    }
}
