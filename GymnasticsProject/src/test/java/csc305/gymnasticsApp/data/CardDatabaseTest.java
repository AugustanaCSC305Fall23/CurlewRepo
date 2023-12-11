package csc305.gymnasticsApp.data;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardDatabaseTest {

    @Test
    void testLoadCards(){

        CardDatabase.addCardsFromCSVFile();

    }
}
