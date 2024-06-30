package csc305.gymnasticsApp.data;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import  java.util.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Represents a collection of favorite items in the gymnastics app
 * Allows users to add and remove favorites, and provides methods for saving and loading favorites
 */
public class FavoriteCollection {


    private final static String FILE_NAME = "favoritesCollection.json";
    private static String FILE_PATH;

    private static final FavoriteCollection theCollection = new FavoriteCollection();
    private Set<String> favoriteSet;

    /**
     * Private constructor to ensure a single instance of FavoriteCollection (Singleton pattern).
     * Initializes the favoriteSet and loads existing favorites from a JSON file.
     */
    private FavoriteCollection() {
        favoriteSet = new HashSet<>();
        try {
            FILE_PATH = getFilePath();
            loadFavorites();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Failed to initialize FavoriteCollection", e);
        }
    }


    /**
     * Gets the singleton instance of FavoriteCollection.
     *
     * @return The singleton instance.
     */
    public static FavoriteCollection getInstance() {
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
    public void modifyFavorites(String ID) {
        if (favoriteSet == null) {
            favoriteSet = new HashSet<>();
        }
        if (favoriteSet.contains(ID)) {
            favoriteSet.remove(ID);
            CardDatabase.getInstance().getCardByID(ID).setFavorite(false);
        } else {
            favoriteSet.add(ID);
            CardDatabase.getInstance().getCardByID(ID).setFavorite(true);
        }
    }

    /**
     * Code sourced from Open AI. Using their Chat GPT
     * <p>
     * saves a java set into a json file using gson
     */
    public void saveFavorites() {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            Gson gson = new Gson();
            gson.toJson(favoriteSet, writer);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save favorites", e);
        }
    }

    /**
     * Code sourced from Open AI. Using their Chat GPT
     * <p>
     * load favorites reads a json file and converts it to java type so that it can be utilized
     */
    private void loadFavorites() {
        File file = new File(FILE_PATH);
        System.out.println(FILE_PATH);
        if (!file.exists()) {
            return;
        }
        try (Reader reader = new FileReader(FILE_PATH)) {
            Gson gson = new Gson();
            Type setType = new TypeToken<HashSet<String>>() {
            }.getType();
            favoriteSet = gson.fromJson(reader, setType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFilePath() throws URISyntaxException {
        String filePath;
        if (isRunningFromJar()) {
            // Running from JAR
            filePath = new File(FavoriteCollection.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
            return filePath + File.separator + FILE_NAME;
        } else {
            // Running from IDE
            filePath = FavoriteCollection.class.getResource("/favoriteCollection").getPath();
            return filePath;
        }
    }

    private boolean isRunningFromJar() {
        return FavoriteCollection.class.getResource("FavoriteCollection.class").toString().startsWith("jar:");
    }
}