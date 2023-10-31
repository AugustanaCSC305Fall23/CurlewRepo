package csc305.gymnasticsApp;

import com.opencsv.bean.CsvToBeanBuilder;
import csc305.gymnasticsApp.CardFilter.CardFilter;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;


public class CardDatabase {
    private List<Card> allCards;

    public List<Card> filter(CardFilter specificFilter) {
        return null;
    }

    public void addCardsFromCSVFile(File csvFile) {

    }

    public void addAllCSVFilesFromFolder(File folderName) {

    }
    
    public static void main(String[] args){
        List<Card> beans = null;

        {
            try {
                InputStream inputStream = Card.class.getResourceAsStream("/GymSoftwarePics/DEMO1.csv");
                if (inputStream != null) {
                    beans = new CsvToBeanBuilder(new InputStreamReader(inputStream))
                            .withType(Card.class).build().parse();
                } else {
                    System.err.println("CSV file not found in the classpath.");
                }
            } catch (Exception e) {
                throw new RuntimeException("An error occurred while parsing the CSV file: " + e.getMessage(), e);
            }
        }
        printAllCards(beans);
    }
    
    public static void printAllCards(List<Card> cardList){
        for(Card card : cardList){
            System.out.println(card.toString());
        }
    }
    
}

//get card by id