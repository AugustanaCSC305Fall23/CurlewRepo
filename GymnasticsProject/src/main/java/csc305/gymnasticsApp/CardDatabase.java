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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The CardDatabase class manages a collection of gymnastics cards and provides methods for filtering and organizing them.
 */
public class CardDatabase {
    private static List<Card> allCards = new ArrayList<>();

    private static Map<String, Card> IDToCard= new HashMap<String, Card>();


    public CardDatabase() {
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

//    /**
//     * Main method for testing and demonstrating the functionality of the CardDatabase class.
//     *
//     * @param args - Command-line arguments (not used).
//     */
//    public static void main(String[] args){
//        addCardsFromCSVFile();
//        setUniqueIDs();
//        addCardsToMap();
//        printAllCards(allCards);
//        CardFilter male = new CodeFilter("S1");
//        List<Card> filteredCards = filter(male);
//        System.out.println();
//        System.out.println();
//        printAllCards(filteredCards);
//
//    }

    private static void addCardsToMap(){
        for(Card card:allCards){
            IDToCard.put(card.getUniqueID(),card);
        }
    }

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
        allCards.clear();
        IDToCard.clear();
        addCardsFromCSVFile();
        setUniqueIDs();
        addCardsToMap();
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


    public static Card getCardByID(String id){
        Card card = IDToCard.get(id);
        return card;
    }

}

//get card by id