package csc305.gymnasticsApp;

import java.io.*;
import java.util.List;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBeanBuilder;

public class Card {
    @CsvBindByName(column = "CODE",required = true)
    private String code;
    @CsvBindByName(column ="Event",required = true)
    private String event;
    @CsvBindByName(column ="Category",required = true)
    private String category;
    @CsvBindByName(column ="Title",required = true)
    private String title;
    @CsvBindByName(column ="Pack Folder",required = true)
    private String packFolder;
    @CsvBindByName(column ="Image",required = true)
    private String image;
    @CsvBindByName(column ="Gender",required = true)
    private String gender;
    @CsvBindByName(column ="Model Sex",required = true)
    private String modelGender;
    @CsvBindByName(column ="Level",required = true)
    private String level;
    @CsvBindByName(column ="Equipment",required = true)
    private List<String> equipment;
    @CsvBindByName(column ="Keywords",required = true)
    private List<String> keywords;

    List<Card> beans;

    {
        try {
            InputStream inputStream = Card.class.getResourceAsStream("/GymSoftwarePics/DEMO1.csv");
            if (inputStream != null) {
                beans = new CsvToBeanBuilder<Card>(new InputStreamReader(inputStream))
                        .withType(Card.class).build().parse();
            } else {
                System.err.println("CSV file not found in the classpath.");
            }
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while parsing the CSV file: " + e.getMessage(), e);
        }
    }





    /*public Card(String code, String event, String category, String title, String packFolder, String image,
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
    */
   // public static void main(String[] args) {
    //}





    public void print(){
        try {
            for(Card card : beans) {
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

    public String getCode(){return this.code;}

//make a filter package



}