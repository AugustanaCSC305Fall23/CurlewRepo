package csc305.gymnasticsApp.ui;


import csc305.gymnasticsApp.data.CardDatabase;
import csc305.gymnasticsApp.data.FavoriteCollection;
import javafx.fxml.FXML;
import csc305.gymnasticsApp.data.Card;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

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
    /**
     * The ImageView for displaying the card's image
     */
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

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/csc305/gymnasticsApp/fxml/cardButton.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try{
            fxmlLoader.load();
        }catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Initializes the CardButton, setting its image based on the associated card
     *
     * @throws FileNotFoundException If the associated card's image file is not found
     */
    @FXML
    private void initialize() throws FileNotFoundException{
        int dotIndex = associatedCard.getImage().indexOf(".");
        Image image = new Image(CardDatabase.class.getResource("/GymSoftwarePics/" +
                associatedCard.getPackFolder() + "/thumbs/" +
                associatedCard.getImage().substring(0,dotIndex) + ".jpg").toString()); //change so it is get resource
        iv.setImage(image);
        favButton.setOnAction(event -> addToFavorites(associatedCard));
        if(associatedCard.isFavorite()) {
            favButton.setStyle("-fx-background-color: #e55451; -fx-shape: 'M12 6.00019C10.2006 3.90317 7.19377 3.2551 4.93923 5.17534C2.68468 7.09558 2.36727 10.3061 4.13778 12.5772C5.60984 14.4654 10.0648 18.4479 11.5249 19.7369C11.6882 19.8811 11.7699 19.9532 11.8652 19.9815C11.9483 20.0062 12.0393 20.0062 12.1225 19.9815C12.2178 19.9532 12.2994 19.8811 12.4628 19.7369C13.9229 18.4479 18.3778 14.4654 19.8499 12.5772C21.6204 10.3061 21.3417 7.07538 19.0484 5.17534C16.7551 3.2753 13.7994 3.90317 12 6.00019Z';");
        }else{
            favButton.setStyle("-fx-background-color: #dfdfdf; -fx-shape: 'M12 6.00019C10.2006 3.90317 7.19377 3.2551 4.93923 5.17534C2.68468 7.09558 2.36727 10.3061 4.13778 12.5772C5.60984 14.4654 10.0648 18.4479 11.5249 19.7369C11.6882 19.8811 11.7699 19.9532 11.8652 19.9815C11.9483 20.0062 12.0393 20.0062 12.1225 19.9815C12.2178 19.9532 12.2994 19.8811 12.4628 19.7369C13.9229 18.4479 18.3778 14.4654 19.8499 12.5772C21.6204 10.3061 21.3417 7.07538 19.0484 5.17534C16.7551 3.2753 13.7994 3.90317 12 6.00019Z';");
        }
    }

    void addToFavorites(Card card) {
        FavoriteCollection.getInstance().modifyFavorites(card.getUniqueID());
        if(associatedCard.isFavorite()) {
            favButton.setStyle("-fx-background-color: #e55451; -fx-shape: 'M12 6.00019C10.2006 3.90317 7.19377 3.2551 4.93923 5.17534C2.68468 7.09558 2.36727 10.3061 4.13778 12.5772C5.60984 14.4654 10.0648 18.4479 11.5249 19.7369C11.6882 19.8811 11.7699 19.9532 11.8652 19.9815C11.9483 20.0062 12.0393 20.0062 12.1225 19.9815C12.2178 19.9532 12.2994 19.8811 12.4628 19.7369C13.9229 18.4479 18.3778 14.4654 19.8499 12.5772C21.6204 10.3061 21.3417 7.07538 19.0484 5.17534C16.7551 3.2753 13.7994 3.90317 12 6.00019Z';");
        }else{
            favButton.setStyle("-fx-background-color: #dfdfdf; -fx-shape: 'M12 6.00019C10.2006 3.90317 7.19377 3.2551 4.93923 5.17534C2.68468 7.09558 2.36727 10.3061 4.13778 12.5772C5.60984 14.4654 10.0648 18.4479 11.5249 19.7369C11.6882 19.8811 11.7699 19.9532 11.8652 19.9815C11.9483 20.0062 12.0393 20.0062 12.1225 19.9815C12.2178 19.9532 12.2994 19.8811 12.4628 19.7369C13.9229 18.4479 18.3778 14.4654 19.8499 12.5772C21.6204 10.3061 21.3417 7.07538 19.0484 5.17534C16.7551 3.2753 13.7994 3.90317 12 6.00019Z';");
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
