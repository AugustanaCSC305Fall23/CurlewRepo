package csc305.gymnasticsApp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBeanBuilder;

public class GymnasticsCard {
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
    private String equipment;
    @CsvBindByName(column ="Keywords")
    private String keywords;

    List<GymnasticsCard> beans;
    public GymnasticsCard(){

    }
    public void print(){
        {
            try {
                beans = new CsvToBeanBuilder(new FileReader("DEMO1.csv"))
                        .withType(GymnasticsCard.class).build().parse();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getCode(){return this.code;}





}