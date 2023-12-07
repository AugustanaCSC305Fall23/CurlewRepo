package csc305.gymnasticsApp.data;

import java.io.*;
import  java.util.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class FavoriteCollection {
    public static FavoriteCollection theCollection = new FavoriteCollection();

    private HashSet<String> favoriteSet;

    private FavoriteCollection() {
        favoriteSet = new HashSet<>();
        loadFavorites();
    }

    public static FavoriteCollection getInstance(){
        return theCollection;
    }

    public Set<String> getFavoriteSet() {
        return favoriteSet;
    }

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
        System.out.println("printing set");
        for(String id : favoriteSet){
            System.out.println(id);
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
