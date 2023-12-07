package csc305.gymnasticsApp.data;


import org.junit.jupiter.api.Test;

public class CardDatabaseTest {

    @Test
    void testLoadCards(){

        CardDatabase.addCardsFromCSVFile();

    }
}
