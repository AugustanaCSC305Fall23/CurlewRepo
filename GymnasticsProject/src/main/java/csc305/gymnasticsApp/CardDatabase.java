package csc305.gymnasticsApp;

import com.opencsv.bean.CsvToBeanBuilder;
import csc305.gymnasticsApp.CardFilter.CardFilter;
import csc305.gymnasticsApp.CardFilter.CodeFilter;
import csc305.gymnasticsApp.CardFilter.GenderFilter;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The CardDatabase class manages a collection of gymnastics cards and provides methods for filtering and organizing them.
 */
public class CardDatabase {
    private static List<Card> allCards = new ArrayList<>();

    private static List<Card> eventOneTreeCards = new ArrayList<>();
    private static List<Card> eventTwoTreeCards = new ArrayList<>();
    /**
     * Main method for testing and demonstrating the functionality of the CardDatabase class.
     *
     * @param args - Command-line arguments (not used).
     */
    public static void main(String[] args){
        addCardsFromCSVFile();
        setUniqueIDs();
        printAllCards(allCards);
        CardFilter male = new CodeFilter("S1");
        List<Card> filteredCards = filter(male);
        System.out.println();
        System.out.println();
        printAllCards(filteredCards);

    }
    /**
     * Filters a list of cards using a specified filter.
     *
     * @param specificFilter - The CardFilter to apply for filtering.
     * @return A list of cards that match the filter criteria.
     */
    public static List<Card> filter(CardFilter specificFilter) {
        List<Card> filteredCards = new ArrayList<>();
        for(Card card : allCards){
            if(specificFilter.matches(card)){
                filteredCards.add(card);
            }

        }
        return filteredCards;
    }
    /**
     * Reads and adds gymnastics cards from CSV files to the collection of cards.
     */
    public static void addCardsFromCSVFile() {
        List<Card> cardsFromCSV = null;
        File[] csvFileList = addAllCSVFilesFromFolder(new File("src/main/resources/GymSoftwarePics/CSVFiles"));

        for(File csvFile: csvFileList){
            try {
                if (csvFile != null) {
                    cardsFromCSV = new CsvToBeanBuilder(new FileReader(csvFile))
                            .withType(Card.class).build().parse();
                } else {
                    System.err.println("CSV file not found in the classpath.");
                }
            } catch (Exception e) {
                throw new RuntimeException("An error occurred while parsing the CSV file: " + e.getMessage(), e);
            }
            allCards.addAll(cardsFromCSV);
        }

    }
    /**
     * Retrieves all gymnastics cards in the database.
     *
     * @return A list of all gymnastics cards in the database.
     */
    public static List<Card> getAllCards() {
        addCardsFromCSVFile();
        return allCards;
    }
    /**
     * Sets unique IDs for all cards in the database based on specific criteria.
     */
    private static void setUniqueIDs(){
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
     * Prints a list of cards to the console.
     *
     * @param cardList The list of cards to be printed.
     */
    public static void printAllCards(List<Card> cardList){
        for(Card card : cardList){
            System.out.println(card.toString());
        }
    }
    /**
     * Adds a card to the list of event one tree cards.
     *
     * @param card The card to be added to the list.
     */
    public static void addEventOneTreeCard(Card card){
        eventOneTreeCards.add(card);
    }
    /**
     * Adds a card to the list of event two tree cards.
     *
     * @param card The card to be added to the list.
     */
    public static void addEventTwoTreeCard(Card card){
        eventTwoTreeCards.add(card);
    }
    /**
     * Deletes a card from the list of event one tree cards.
     *
     * @param card The card to be removed from the list.
     */
    public static void deleteEventOneTreeCard(Card card){
        eventOneTreeCards.remove(card);
    }
    /**
     * Deletes a card from the list of event two tree cards.
     *
     * @param card The card to be removed from the list.
     */
    public static void deleteEventTwoTreeCard(Card card){
        eventTwoTreeCards.remove(card);
    }
    /**
     * Retrieves a card from the specified tree list based on the card's title.
     *
     * @param value - The title of the card to retrieve.
     * @param treeNum - The number representing the tree (1 or 2).
     * @return The card matching the title, or the first card if not found.
     */
    public static Card getCardFromTreeItem(String value, int treeNum) {
        if (treeNum == 1){
            for(int i = 0; i < eventOneTreeCards.size(); i++){
                if (eventOneTreeCards.get(i).getTitle().equals(value)){
                    return eventOneTreeCards.get(i);
                }
            }
        } else{
            for(int i = 0; i < eventTwoTreeCards.size(); i++){
                if (eventTwoTreeCards.get(i).getTitle().equals(value)){
                    return eventTwoTreeCards.get(i);
                }
            }
        }
        return eventOneTreeCards.get(0);
    }

    /**
     * Gets the EventOneTreeCards
     *
     * @return The EventOneTreeCards
     */
    public static List<Card> getEventOneTreeCards(){
        return eventOneTreeCards;
    }

    /**
     * Gets the EventTwoTreeCards
     *
     * @return The EventTwoTreeCards
     */
    public static List<Card> getEventTwoTreeCards(){
        return eventTwoTreeCards;
    }


}

//get card by id