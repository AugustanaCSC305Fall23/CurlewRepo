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


public class CardDatabase {
    private static List<Card> allCards = new ArrayList<>();

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

    public static List<Card> filter(CardFilter specificFilter) {
        List<Card> filteredCards = new ArrayList<>();
        for(Card card : allCards){
            if(specificFilter.matches(card)){
                filteredCards.add(card);
            }

        }
        return filteredCards;
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

    public static List<Card> getAllCards() {
        addCardsFromCSVFile();
        return allCards;
    }

    private static void setUniqueIDs(){
        for(Card card : allCards){
            card.setUniqueID();
        }
    }

    public static File[] addAllCSVFilesFromFolder(File folderName) {
        File[] csvArray = folderName.listFiles();
        return csvArray;
    }

    public static void printAllCards(List<Card> cardList){
        for(Card card : cardList){
            System.out.println(card.toString());
        }
    }

}

//get card by id