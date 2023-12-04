package csc305.gymnasticsApp.ui;

import csc305.gymnasticsApp.data.Card;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class exerciseCard extends Button {

    private Card card;

    @FXML
    private ImageView img;
    public exerciseCard(Card card) {
        this.card = card;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/csc305.gymnasticsApp/exerciseCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @FXML
    void initialize() throws FileNotFoundException {
        Image image = new Image(new FileInputStream("GymSoftwarePics/" + card.getPackFolder().toUpperCase() + "Pack/" + card.getImage()));
        img.setImage(image);
    }

    public Card getCard() {
        return card;
    }
}
