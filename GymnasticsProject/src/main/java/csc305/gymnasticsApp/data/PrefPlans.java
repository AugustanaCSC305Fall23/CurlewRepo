package csc305.gymnasticsApp.data;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * The PrefPlans class manages preferences for gymnastics plans using the java.util.prefs package.
 * It provides methods to set and get preferences for up to three plans.
 */
public class PrefPlans {
    private Preferences prefs;

    /**
     * Constructs a new PrefPlans object and initializes preferences.
     */
    public PrefPlans(){
        prefs = Preferences.userRoot().node(this.getClass().getName());

        prefs.get("Plan1", "");
        prefs.get("Plan2", "");
        prefs.get("Plan3", "");
    }

    /**
     * Sets the preference for the first plan and shifts existing preferences.
     *
     * @param filePath The file path of the first plan.
     */
    public void setPreference(String filePath){
        // now set the values
        prefs.put("Plan3", prefs.get("Plan2",""));
        prefs.put("Plan2", prefs.get("Plan1",""));
        prefs.put("Plan1", filePath);

    }

    /**
     * Gets the file path of the first plan from preferences.
     *
     * @return The file path of the first plan.
     */
    public String getPlan1(){
        return prefs.get("Plan1", "");
    }

    /**
     * Gets the file path of the second plan from preferences.
     *
     * @return The file path of the second plan.
     */
    public String getPlan2(){
        return prefs.get("Plan2", "");
    }

    /**
     * Gets the file path of the third plan from preferences.
     *
     * @return The file path of the third plan.
     */
    public String getPlan3(){
        return prefs.get("Plan3", "");
    }

}
