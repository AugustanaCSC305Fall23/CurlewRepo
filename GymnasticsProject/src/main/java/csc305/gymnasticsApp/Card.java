package csc305.gymnasticsApp;


import java.io.File;
import java.util.List;
import com.opencsv.bean.CsvBindByName;

/**
 * The Card class represents a gymnastics card with various attributes such as code, event,
 * category, title, pack folder, image, gender, model gender, level, equipment, keywords, and a unique ID.
 */
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

    public Card() {
        this.code = getCode();
        this.event = getEvent();
        this.category = getCategory();
        this.title = getTitle();
        this.packFolder = getPackFolder();
        this.image = getImage();
        this.gender = getGender();
        this.modelGender = getModelGender();
        this.level = getLevel();
        this.equipment = getEquipment();
        this.keywords = getKeywords();
        this.uniqueID = getUniqueID();
    }

    /**
     * Get the code of the card.
     *
     * @return The code of the card
     */
    public String getCode() {return code;}

    /**
     * Set the code of the card.
     * @param code - The code to set.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Get the event of the card.
     *
     * @return The event of the card.
     */
    public String getEvent() {
        return event;
    }

    /**
     * Set the event of the card.
     *
     * @param event - The event to set.
     */
    public void setEvent(String event) {
        this.event = event;
    }

    /**
     * Get the category of the card.
     *
     * @return The category of the card.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Set the category of the card.
     *
     * @param category - The category to set.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Get the title of the card.
     *
     * @return The title of the card.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title of the card.
     * @param title - The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the pack folder of the card.
     *
     * @return The pack folder of the card.
     */
    public String getPackFolder() {
        return packFolder;
    }

    /**
     * Set the pack folder of the card.
     *
     * @param packFolder - The pack folder to set.
     */
    public void setPackFolder(String packFolder) {
        this.packFolder = packFolder;
    }

    /**
     * Get the image of the card.
     *
     * @return The image of the card.
     */
    public String getImage() {
        return image;
    }

    /**
     * Seet the image of the card.
     * @param image - The image to set.
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Get the gender of the card.
     *
     * @return The gender of the card.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Set the gender of the card.
     *
     * @param gender - The gender to set.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Get the model gender of the card.
     *
     * @return The model gender of the card
     */
    public String getModelGender() {
        return modelGender;
    }

    /**
     * Set the model gender of the card.
     *
     * @param modelGender - The model gender to set.
     */
    public void setModelGender(String modelGender) {
        this.modelGender = modelGender;
    }

    /**
     * Get the level of the card.
     *
     * @return The level of the card.
     */
    public String getLevel() {
        return level;
    }

    /**
     * Set the level of the card.
     * @param level - The level to set.
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * Get the equipment of the card.
     *
     * @return The equipment of the card.
     */
    public String getEquipment() {
        return equipment;
    }

    /**
     * Set the equipment of the card.
     *
     * @param equipment - The equipment to set.
     */
    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    /**
     * Get the keywords of the card.
     *
     * @return The keywords of the card.
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * Set the keywords of the card.
     *
     * @param keywords - The keywords to set.
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     * Get the unique ID of the card.
     *
     * @return The unique ID of the card.
     */
    public String getUniqueID() {
        return uniqueID;
    }

    /**
     * Set the unique ID of the card based on pakc folder, code, event, and gender.
     */
    public void setUniqueID() {
        this.uniqueID = packFolder + "." + code + "." + event + "." + gender + "."
                + title + "." + category + "." + level + "." + equipment + "." + keywords;
    }

    /**
     * Creates a string representation of the Card object.
     *
     * @return The string representation of the Card object.
     */
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