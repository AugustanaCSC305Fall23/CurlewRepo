package csc305.gymnasticsApp.data;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class PrefCourses {
    private Preferences prefs;

    public PrefCourses(){
        prefs = Preferences.userRoot().node(this.getClass().getName());

        prefs.get("Course1", "");
        prefs.get("Course2", "");
        prefs.get("Course3", "");


    }


    public void setCoursePreference(String filePath){
        System.out.println(filePath);

        // now set the values
        prefs.put("Course3", prefs.get("Course2",""));
        prefs.put("Course2", prefs.get("Course1",""));
        prefs.put("Course1", filePath);

    }

    public String getCourse1(){
        return prefs.get("Course1", "");
    }

    public String getCourse2(){
        return prefs.get("Course2", "");
    }
    public String getCourse3(){
        return prefs.get("Course3", "");
    }

}
