package csc305.gymnasticsApp;


import java.util.List;
import com.opencsv.bean.CsvBindByName;

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
    private String equipment;
    @CsvBindByName(column ="Keywords")
    private String keywords;

    private String uniqueID;


    public String getCode() {return code;}
    public void setCode(String code) {
        this.code = code;
    }

    public String getEvent() {
        return event;
    }
    public void setEvent(String event) {
        this.event = event;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getPackFolder() {
        return packFolder;
    }
    public void setPackFolder(String packFolder) {
        this.packFolder = packFolder;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getModelGender() {
        return modelGender;
    }
    public void setModelGender(String modelGender) {
        this.modelGender = modelGender;
    }

    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }

    public String getEquipment() {
        return equipment;
    }
    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getKeywords() {
        return keywords;
    }
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getUniqueID() {
        return uniqueID;
    }
    public void setUniqueID() {
        this.uniqueID = packFolder + "." + code + "." + event + "." + gender;
    }

    @Override
    public String toString() {
        return "Card{" +
                "code='" + code + '\'' +
                ", event='" + event + '\'' +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", packFolder='" + packFolder + '\'' +
                ", image='" + image + '\'' +
                ", gender='" + gender + '\'' +
                ", modelGender='" + modelGender + '\'' +
                ", level='" + level + '\'' +
                ", equipment=" + equipment +
                ", keywords=" + keywords +
                ", Unique ID=" + uniqueID+
                '}';
    }

}