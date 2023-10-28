package csc305.gymnasticsApp;

import java.io.*;
import java.util.List;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBeanBuilder;

public class Card {
    @CsvBindByName(column = "CODE")
    private String code;
    @CsvBindByName(column ="Event")
    private String event;
    @CsvBindByName(column ="Category")
    private String category;
    @CsvBindByName(column ="Title")
    private String title;
    @CsvBindByName(column ="Pack Folder")
    private String packFolder;
    @CsvBindByName(column ="Image")
    private String image;
    @CsvBindByName(column ="Gender")
    private String gender;
    @CsvBindByName(column ="Model Sex")
    private String modelGender;
    @CsvBindByName(column ="Level")
    private String level;
    @CsvBindByName(column ="Equipment")
    private List<String> equipment;
    @CsvBindByName(column ="Keywords")
    private List<String> keywords;

    List<Card> beans;
    public Card(){

    }


    public Card(String code, String event, String category, String title, String packFolder, String image,
                String gender, String modelGender, String level, List<String> equipment, List<String> keywords) {
        this.code = code;
        this.event = event;
        this.category = category;
        this.title = title;
        this.packFolder = packFolder;
        this.image = image;
        this.gender = gender;
        this.modelGender = modelGender;
        this.level = level;
        this.equipment = equipment;
        this.keywords = keywords;
    }

    public static void main(String[] args) {
        try {
            InputStream inputStream = Card.class.getResourceAsStream("/GymSoftwarePics/DEMO1.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            List<Card> cards = new CsvToBeanBuilder<Card>(reader)
                    .withType(Card.class).build().parse();

            for (Card card : cards) {
                System.out.println("Code: " + card.code);
                System.out.println("Event: " + card.event);
                System.out.println("Category: " + card.category);
                System.out.println("Title: " + card.title);
                System.out.println("Pack Folder: " + card.packFolder);
                System.out.println("Image: " + card.image);
                System.out.println("Gender: " + card.gender);
                System.out.println("Model Gender: " + card.modelGender);
                System.out.println("Level: " + card.level);
                System.out.println("Equipment: " + card.equipment);
                System.out.println("Keywords: " + card.keywords);
                System.out.println();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




    /*
    public void print(){
        {
            try {
                beans = new CsvToBeanBuilder(new FileReader("DEMO1.csv"))
                        .withType(Card.class).build().parse();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getCode(){return this.code;}

//make a filter package

    */

}