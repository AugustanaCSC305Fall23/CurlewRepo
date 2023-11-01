package csc305.gymnasticsApp;

import com.opencsv.bean.CsvToBeanBuilder;
import csc305.gymnasticsApp.CardFilter.CardFilter;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class CardDatabase {
    private static List<Card> allCards = new ArrayList<>();

    public List<Card> filter(CardFilter specificFilter) {
        return null;
    }

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

    public static File[] addAllCSVFilesFromFolder(File folderName) {
        File[] csvArray = folderName.listFiles();
        return csvArray;
    }

    public static void main(String[] args){
        addCardsFromCSVFile();
        printAllCards(allCards);
    }

    public static void printAllCards(List<Card> cardList){
        for(Card card : cardList){
            System.out.println(card.toString());
        }
    }

}

//get card by id