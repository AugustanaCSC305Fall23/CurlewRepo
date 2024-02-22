package csc305.gymnasticsApp.data;
import java.util.prefs.Preferences;

public class PrefFileLocation {
    private Preferences prefs;

    public PrefFileLocation() {
        prefs = Preferences.userRoot().node(this.getClass().getName());

        prefs.get("CourseLocation", "");
        prefs.get("LPLocation", "");
    }
    public void setLastSavedLocationCourses(String location) {
        prefs.put("CourseLocation", location);
    }

    public void setLastSavedLocationLP(String location){
        prefs.put("LPLocation", location);
    }

    public String getLastSavedLocationCourse() {
        return prefs.get("CourseLocation", "");
    }

    public String getLastSavedLocationLP(){
        return prefs.get("LPLocation", "");
    }
}

