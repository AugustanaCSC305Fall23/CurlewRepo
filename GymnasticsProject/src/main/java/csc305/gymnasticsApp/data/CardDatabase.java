package csc305.gymnasticsApp.data;

import com.opencsv.bean.CsvToBeanBuilder;
import csc305.gymnasticsApp.filters.CardFilter;


import java.io.File;
import java.io.FileReader;
import java.util.*;

/**
 * The CardDatabase class manages a collection of gymnastics cards and provides methods for filtering and organizing them.
 */
public class CardDatabase {
    /**
     * A list containing all gymnastics cards in the database
     */
    private static List<Card> allCards = new ArrayList<>();

    /**
     * A mapping of unique card IDs to their respective cards for efficient retrieval
     */
    private static Map<String, Card> IDToCard= new HashMap<String, Card>();

    /**
     * Constructs a new CardDatabase and initializes it by reading gymnastics cards from CSV files.
     */
    public CardDatabase() {
        List<Card> cardsFromCSV = null;
        List<File> csvFileList = addAllCSVFilesFromFolder(new File("GymSoftwarePics/CSVFiles"));

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
//        filters male = new CodeFilter("S1");
//        List<Card> filteredCards = filter(male);
//        System.out.println();
//        System.out.println();
//        printAllCards(filteredCards);
//
//    }

    /**
     * Adds cards to the mapping of unique IDs to cards for efficient retrieval
     */
    private static void addCardsToMap(){
        for(Card card:allCards){
            IDToCard.put(card.getUniqueID(),card);
        }
    }

    /**
     * Filters the cards based on the provided filter criteria
     *
     * @param specificFilter The filter criteria to apply
     * @return A list of cards that match the filter criteria
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
        List<File> csvFileList = addAllCSVFilesFromFolder(new File("GymSoftwarePics"));

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
    public static List<File> addAllCSVFilesFromFolder(File folderName) {
        List<File> csvFiles = new ArrayList<>();
        File[] subFolders = folderName.listFiles();
        for(File subFolder : subFolders){
            File[] packFiles = subFolder.listFiles();
            for(File packFile : packFiles){
                String fileName = packFile.getName();
                String extension = "";
                int i = fileName.lastIndexOf('.');
                if (i >= 0) { extension = fileName.substring(i+1); }
                if(extension.equals("csv")){
                    csvFiles.add(packFile);
                    break;
                }
            }
        }
        for(File file : csvFiles) {
            System.out.println(file.getName());
        }
        return csvFiles;
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
     * Retrieves a card from the database based on its unique ID
     *
     * @param id The unique ID of the card to retrieve
     * @return The card with the specified unique ID
     */
    public static Card getCardByID(String id){
        Card card = IDToCard.get(id);
        return card;
    }

    public static List<String> getGenderList() {
        Set<String> genderSet = new TreeSet<>();
        for (Card card : allCards) {
            genderSet.add(card.getGender());
        }
        return new ArrayList<>(genderSet);
    }

    public static List<String> getModelGenderList() {
        Set<String> modelGenderSet = new TreeSet<>();
        for (Card card : allCards) {
            modelGenderSet.add(card.getModelGender());
        }
        return new ArrayList<>(modelGenderSet);
    }

}

//get card by id