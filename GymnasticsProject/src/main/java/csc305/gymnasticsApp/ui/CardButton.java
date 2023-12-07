package csc305.gymnasticsApp.ui;


import javafx.fxml.FXML;
import csc305.gymnasticsApp.data.Card;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The CardButton class represents a custom JavaFX button that is associated with a specific card.
 * It extends the JavaFX Button class and includes additional functionality related to cards.
 */
public class CardButton extends AnchorPane {
    /**
     * The card that is associated with this button
     */
    private Card associatedCard;
    @FXML 
    private ImageView iv;

    @FXML
    private Button favButton;

    /**
     *
     *
     * @param card The card associated with this button
     */
    public CardButton(Card card){
        associatedCard = card;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/csc305/gymnasticsApp/cardButton.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try{
            fxmlLoader.load();
        }catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @FXML
    private void initialize() throws FileNotFoundException{
        int dotIndex = associatedCard.getImage().indexOf(".");
        Image image = new Image(new FileInputStream("GymSoftwarePics/" +
                associatedCard.getPackFolder() + "/thumbs/" +
                associatedCard.getImage().substring(0,dotIndex) + ".jpg"));
        iv.setImage(image);

        favButton.setOnAction(event -> addToFavorites(associatedCard));
    }

    void addToFavorites(Card card) {
        if (card.isFavorite()) {
            card.setFavorite(false);
        } else {
            card.setFavorite(true);
        }
    }


    /**
     * Retrieves the card associated with this button
     *
     * @return The associated card
     */
    public Card getCard() {
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
