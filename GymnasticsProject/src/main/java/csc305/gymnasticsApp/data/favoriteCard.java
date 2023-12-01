package csc305.gymnasticsApp.data;

/**
 * The FavoriteCard class represents a special type of gymnastics card that can be marked as a favorite.
 * It extends the Card class and includes an additional property for tracking its favorite status.
 */
public class favoriteCard extends Card {

    /**
     * Indicates whether the card is marked as a favorite
     */
    private boolean favorite;

    /**
     * Constructs a new FavoriteCard with the specified favorite status
     *
     * @param favorite true if the card is a favorite or false otherwise
     */
    public favoriteCard(boolean favorite) {
        super();
        this.favorite = favorite;
    }
}
