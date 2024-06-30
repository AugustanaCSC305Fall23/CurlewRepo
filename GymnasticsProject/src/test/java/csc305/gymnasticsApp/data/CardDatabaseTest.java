package csc305.gymnasticsApp.data;


import csc305.gymnasticsApp.ui.GymnasticsAppBeta;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CardDatabaseTest {

    @Test
    void testNumLoadedCards(){
        List<Card> cardList = CardDatabase.getInstance().getAllCards();
        assertEquals(36, cardList.size());
    }

    @Test
    void testEachCardLoadedValues(){
        List<Card> cardList = CardDatabase.getInstance().getAllCards();
        for (Card card : cardList) {
            assertNotNull(card);
            assertNotNull(card.getImage());
            assertNotNull(card.getUniqueID());
            assertNotNull(card.getPackFolder());
            assertNotNull(card.getCode());
            assertNotNull(card.getTitle());
            assertNotNull(card.getCode());
            assertNotNull(card.getEquipment());
            assertNotNull(card.getEvent());
            assertNotNull(card.getGender());
            assertNotNull(card.getLevel());
            assertNotNull(card.getCategory());
            assertNotNull(card.getModelGender());
            assertNotNull(card.getKeywords());
        }
    }
}
