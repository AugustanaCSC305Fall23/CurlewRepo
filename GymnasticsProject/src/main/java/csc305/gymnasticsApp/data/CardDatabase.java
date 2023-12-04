package csc305.gymnasticsApp.data;

import com.opencsv.bean.CsvToBeanBuilder;
import csc305.gymnasticsApp.filters.CardFilter;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The CardDatabase class manages a collection of gymnastics cards and provides methods for filtering and organizing them.
 */
public class CardDatabase {
    /**
     * A list containing all gymnastics cards in the database
     */
    private List<Card> allCards;

    private List<Card> newCards;

    private static CardDatabase theDB = new CardDatabase();

    /**
     * A mapping of unique card IDs to their respective cards for efficient retrieval
     */
    private Map<String, Card> allCardMap;

    /**
     * Constructs a new CardDatabase and initializes it by reading gymnastics cards from CSV files.
     */
    public CardDatabase() {
    }

    public static CardDatabase getDB() {
        return theDB;
    }

    public List<Card> getAllCards() {
        return new ArrayList<>(allCards);
    }

    public void loadCardFromCSVFile(String filename) throws FileNotFoundException {
        allCards = new CsvToBeanBuilder<Card>(new FileReader(filename)).withType(Card.class).build().parse();
        allCardMap = new HashMap<>();
        for (Card card : allCards) {
            allCardMap.put(card.getUniqueID(), card);
        }
    }

    /**
     * Reads and adds gymnastics cards from CSV files to the collection of cards.
     */
    public void addCardsFromCSVFile(String filename) throws FileNotFoundException {
        newCards = new CsvToBeanBuilder<Card>(new FileReader(filename)).withType(Card.class).build().parse();
        for (Card card : newCards) {
            allCards.add(card);
            allCardMap.put(card.getUniqueID(), card);
        }

    }


    /**
     * Filters the cards based on the provided filter criteria
     *
     * @param specificFilter The filter criteria to apply
     * @return A list of cards that match the filter criteria
     */
    public List<Card> filter(CardFilter specificFilter) {
        List<Card> filteredCards = new ArrayList<>();
        for(Card card : allCards){
            if(specificFilter.matches(card)){
                filteredCards.add(card);
            }

        }
        return filteredCards;
    }


    /**
     * Sets unique IDs for all cards in the database based on specific criteria.
     */
    private void setUniqueIDs(){
        for(Card card : allCards){
            card.setUniqueID();
        }
    }
    /**
     * Lists all CSV files in a specified folder.
     *
     * @param folderName The folder containing CSV files.
     * @return An array of CSV files found in the folder.
     */
    public static File[] addAllCSVFilesFromFolder(File folderName) {
        File[] csvArray = folderName.listFiles();
        return csvArray;
    }


    /**
     * Retrieves a card from the database based on its unique ID
     *
     * @param cardID The unique ID of the card to retrieve
     * @return The card with the specified unique ID
     */
    public Card getCardByID(String cardID){
        return allCardMap.get(cardID);
    }

}

//get card by id