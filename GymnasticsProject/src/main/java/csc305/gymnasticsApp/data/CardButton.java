package csc305.gymnasticsApp.data;


import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class CardButton extends Button {
    private Card associatedCard;
    public CardButton(String s, ImageView iv, Card card){
        super(s, iv);
        associatedCard = card;
    }

    public Card getAssociatedCard() {
        return associatedCard;
    }

    public void setAssociatedCard(Card associatedCard) {
        this.associatedCard = associatedCard;
    }
}
