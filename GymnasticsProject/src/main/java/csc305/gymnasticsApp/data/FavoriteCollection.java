package csc305.gymnasticsApp.data;

import java.io.*;
import  java.util.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Represents a collection of favorite items in the gymnastics app
 * Allows users to add and remove favorites, and provides methods for saving and loading favorites
 */
public class FavoriteCollection {
    public static FavoriteCollection theCollection = new FavoriteCollection();

    private HashSet<String> favoriteSet;

    /**
     * Private constructor to ensure a single instance of FavoriteCollection (Singleton pattern).
     * Initializes the favoriteSet and loads existing favorites from a JSON file.
     */
    private FavoriteCollection() {
        favoriteSet = new HashSet<>();
        loadFavorites();
    }

    /**
     * Gets the singleton instance of FavoriteCollection.
     *
     * @return The singleton instance.
     */
    public static FavoriteCollection getInstance(){
        return theCollection;
    }

    /**
     * Gets the set of favorite item IDs.
     *
     * @return The set of favorite item IDs.
     */
    public Set<String> getFavoriteSet() {
        return favoriteSet;
    }

    /**
     * Modifies the favorites by adding or removing an item with the specified ID.
     *
     * @param ID The ID of the item to be added or removed from favorites.
     */
    public void modifyFavorites(String ID){
        if(favoriteSet == null){
            favoriteSet = new HashSet<>();
        }
        if(favoriteSet.contains(ID)){
            favoriteSet.remove(ID);
            CardDatabase.getCardByID(ID).setFavorite(false);
        }else{
            favoriteSet.add(ID);
            CardDatabase.getCardByID(ID).setFavorite(true);
        }
    }

    /**
     * Code sourced from Open AI. Using their Chat GPT
     *
     * saves a java set into a json file using gson
     */
    public void saveFavorites() {
        try (Writer writer = new FileWriter("src/main/resources/FavoriteCollection")) {
            Gson gson = new Gson();
            gson.toJson(getInstance().favoriteSet, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Code sourced from Open AI. Using their Chat GPT
     *
     *load favorites reads a json file and converts it to java type so that it can be utilized
     */
    private void loadFavorites() {
        try (Reader reader = new FileReader("src/main/resources/FavoriteCollection")) {
            Gson gson = new Gson();
            TypeToken<HashSet<String>> token = new TypeToken<HashSet<String>>() {};
            favoriteSet = gson.fromJson(reader, token.getType());
        } catch (FileNotFoundException e) {
            // File doesn't exist yet, no need to do anything
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
