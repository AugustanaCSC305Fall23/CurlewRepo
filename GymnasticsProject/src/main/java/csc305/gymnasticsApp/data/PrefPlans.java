package csc305.gymnasticsApp.data;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class PrefPlans {
    private Preferences prefs;

    public PrefPlans(){
        prefs = Preferences.userRoot().node(this.getClass().getName());

        prefs.get("Plan1", "");
        prefs.get("Plan2", "");
        prefs.get("Plan3", "");
    }


    public void setPreference(String filePath){
        System.out.println(filePath);

        // now set the values
        prefs.put("Plan3", prefs.get("Plan2",""));
        prefs.put("Plan2", prefs.get("Plan1",""));
        prefs.put("Plan1", filePath);

    }

    public String getPlan1(){
        return prefs.get("Plan1", "");
    }

    public String getPlan2(){
        return prefs.get("Plan2", "");
    }
    public String getPlan3(){
        return prefs.get("Plan3", "");
    }

}
