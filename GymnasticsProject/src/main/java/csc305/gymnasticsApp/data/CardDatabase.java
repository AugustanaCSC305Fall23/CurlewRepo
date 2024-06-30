package csc305.gymnasticsApp.data;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;


import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.stream.Stream;

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

    public static CardDatabase theDatabase;

    static {
        try {
            theDatabase = new CardDatabase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    /**
     * Constructs a new CardDatabase and initializes it by reading gymnastics cards from CSV files.
     */
    private CardDatabase() throws IOException {
        try {
            if(!isRunningFromJar()) {
                Path rootPath = Paths.get(CardDatabase.class.getResource("/GymSoftwarePics").toURI());
                List<File> csvFileList = addAllCSVFromStream(rootPath);
                addCardsFromCSVFiles(csvFileList);
            }else {
                addCardsFromInputStream(this.getClass().getResourceAsStream("/GymSoftwarePics/Demo1/Demo1.csv"));
                addCardsFromInputStream(this.getClass().getResourceAsStream("/GymSoftwarePics/Demo2/Demo2.csv"));

            }
            setUniqueIDs();
            addCardsToMap();

        } catch (NullPointerException e) {
            throw new RuntimeException("Error loading resource path", e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private void addCardsFromInputStream(InputStream stream) throws IOException {
        InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);

        // Process the CSV content as needed (CSV parsing example)
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader("CODE", "Event", "Category", "Title", "Pack Folder", "Image", "Gender", "Model Sex", "Level", "Equipment", "Keywords")
                .setSkipHeaderRecord(true)
                .build();

        Iterable<CSVRecord> records = csvFormat.parse(reader);

        for (CSVRecord record : records) {
            Card card = new Card(record);
            allCards.add(card);
        }
    }


    private void addCardsFromCSVFiles(List<File> csvFileList){
        for(File csvFile: csvFileList){
            try {
                if (csvFile != null) {
                    Reader in = new FileReader(csvFile);

                    CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                            .setHeader("CODE", "Event", "Category", "Title", "Pack Folder", "Image", "Gender", "Model Sex", "Level", "Equipment", "Keywords")
                            .setSkipHeaderRecord(true)
                            .build();

                    Iterable<CSVRecord> records = csvFormat.parse(in);

                    for (CSVRecord record : records){
                        Card card = new Card(record);
                        allCards.add(card);
                    }
                } else {
                    System.err.println("CSV file not found in the classpath.");
                }
            } catch (Exception e) {
                throw new RuntimeException("An error occurred while parsing the CSV file: " + e.getMessage(), e);
            }
        }
    }
    /**
     * method to sort through a specific folder in the project and search for csv files
     * @param rootPath a Path parameter to a file
     * @return a list of csv files
     * @throws IOException
     */
    private List<File> addAllCSVFromStream(Path rootPath) throws IOException {
        List<File> csvList = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(rootPath, 1)) { // Depth 1 for immediate subfolders
            paths.filter(Files::isDirectory)
                    .forEach(subfolder -> {
                        try (Stream<Path> subfolderFiles = Files.list(subfolder)) {
                            subfolderFiles.filter(file -> file.toString().endsWith(".csv"))
                                    .forEach(csvFile -> {
                                        // Process each CSV file (e.g., read, parse, etc.)
                                        System.out.println("CSV File: " + csvFile.getFileName());
                                        csvList.add(csvFile.toFile());
                                        // Example: Read and process CSV file
                                        // processCSVFile(csvFile);
                                    });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvList;
    }


    private boolean isRunningFromJar() {
        return CardDatabase.class.getResource("CardDatabase.class").toString().startsWith("jar:");
    }

    /**
     * Adds cards to the mapping of unique IDs to cards for efficient retrieval
     */
    private static void addCardsToMap(){
        for(Card card:allCards){
            IDToCard.put(card.getUniqueID(),card);
        }
    }

    /**
     * Retrieves all gymnastics cards in the database.
     *
     * @return A list of all gymnastics cards in the database.
     */
    public static List<Card> getAllCards() {
        return allCards;
    }

    public static CardDatabase getInstance(){
        return theDatabase;
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
        System.out.println("starting to add");
        System.out.println(folderName.getPath());
        List<File> csvFiles = new ArrayList<>();
        File[] subFolders = folderName.listFiles();
        for(File subFolder : subFolders){ //allows access to the card packs
            System.out.println(subFolder.getName());
            File[] packFiles = subFolder.listFiles();
            for(File packFile : packFiles){ //goes into each card pack and finds the csv file
                System.out.println(packFile.getName());
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
        return csvFiles;
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

    /**
     * Retrieves a list of unique gender values from the set of cards
     * @return A list containing unique gender values
     */
    public static List<String> getGenderList() {
        Set<String> genderSet = new TreeSet<>();
        for (Card card : allCards) {
            genderSet.add(card.getGender());
        }
        return new ArrayList<>(genderSet);
    }

    /**
     * Retrieves a list of unique model gender values from the set of all cards
     *
     * @return A list containing unique model gender values
     */
    public static List<String> getModelGenderList() {
        Set<String> modelGenderSet = new TreeSet<>();
        for (Card card : allCards) {
            modelGenderSet.add(card.getModelGender());
        }
        return new ArrayList<>(modelGenderSet);
    }

    /**
     * Retrieves a list of unique event values from the set of cards
     *
     * @return A list containing unique event values
     */
    public static List<String> getEventList() {
        Set<String> eventSet = new TreeSet<>();
        for (Card card : allCards) {
            eventSet.add(card.getEvent());
        }
        return new ArrayList<>(eventSet);
    }

    /**
     * Retrieves a list of unique level values from the set of all cards
     *
     * @return A list containing unique level values
     */
    public static List<String> getLevelList() {
        Set<String> levelSet = new TreeSet<>();
        levelSet.add("ALL");
        levelSet.add("Beginner");
        levelSet.add("Advanced Beginner");
        levelSet.add("Intermediate");
        levelSet.add("Advanced");
        return new ArrayList<>(levelSet);
    }

}

//get card by id