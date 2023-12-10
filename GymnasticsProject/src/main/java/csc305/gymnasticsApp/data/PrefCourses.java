package csc305.gymnasticsApp.data;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * The PrefCourses class manages preferences for gymnastics courses using the java.util.prefs package.
 * It provides methods to set and get preferences for up to three courses.
 */
public class PrefCourses {
    private Preferences prefs;

    /**
     * Constructs a new PrefCourses object and initializes preferences.
     */
    public PrefCourses(){
        prefs = Preferences.userRoot().node(this.getClass().getName());

        prefs.get("Course1", "");
        prefs.get("Course2", "");
        prefs.get("Course3", "");


    }

    /**
     * Sets the preference for the first course and shifts existing preferences.
     *
     * @param filePath The file path of the first course.
     */
    public void setCoursePreference(String filePath){
        // now set the values
        prefs.put("Course3", prefs.get("Course2",""));
        prefs.put("Course2", prefs.get("Course1",""));
        prefs.put("Course1", filePath);

    }
    /**
     * Gets the file path of the first course from preferences.
     *
     * @return The file path of the first course.
     */
    public String getCourse1(){
        return prefs.get("Course1", "");
    }

    /**
     * Gets the file path of the second course from preferences.
     *
     * @return The file path of the second course.
     */
    public String getCourse2(){
        return prefs.get("Course2", "");
    }

    /**
     * Gets the file path of the third course from preferences.
     *
     * @return The file path of the third course.
     */
    public String getCourse3(){
        return prefs.get("Course3", "");
    }

}
