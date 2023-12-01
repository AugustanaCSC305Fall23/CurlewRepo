package csc305.gymnasticsApp.data;


import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * The CardButton class represents a custom JavaFX button that is associated with a specific card.
 * It extends the JavaFX Button class and includes additional functionality related to cards.
 */
public class CardButton extends Button {
    /**
     * The card that is associated with this button
     */
    private Card associatedCard;

    /**
     *
     * @param s The text to display on the button
     * @param iv The image to display on the button
     * @param card The card associated with this button
     */
    public CardButton(String s, ImageView iv, Card card){
        super(s, iv);
        associatedCard = card;
    }

    /**
     * Retrieves the card associated with this button
     *
     * @return The associated card
     */
    public Card getAssociatedCard() {
        return associatedCard;
    }

    /**
     * Sets the card associated with this button
     *
     * @param associatedCard The new associated card
     */
    public void setAssociatedCard(Card associatedCard) {
        this.associatedCard = associatedCard;
    }
}
