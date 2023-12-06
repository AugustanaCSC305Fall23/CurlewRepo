package csc305.gymnasticsApp.data;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class CardDatabseTest {

    @Test
    void testLoadCards(){
        CardDatabase.addCardsFromCSVFile();
    }
}
