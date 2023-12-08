package csc305.gymnasticsApp.data;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class PrefPlans {
    private Preferences prefs;

    public PrefPlans(){
        prefs = Preferences.userRoot().node(this.getClass().getName());
        // Define a boolean value
        System.out.println(prefs.get("Plan1", null));
        // Define a string with default "Hello World
        System.out.println(prefs.get("Plan2", null));
        // Define a integer with default 50
        System.out.println(prefs.get("Plan3", null));
    }


    public void setPreference(String filePath){
        System.out.println(filePath);

        // now set the values
        prefs.put("Plan3", prefs.get("Plan2",null));
        prefs.put("Plan2", prefs.get("Plan1",null));
        prefs.put("Plan1", filePath);

    }

    public String getPlan1(){
        return prefs.get("Plan1", null);
    }

    public String getPlan2(){
        return prefs.get("Plan2", null);
    }
    public String getPlan3(){
        return prefs.get("Plan3", null);
    }


    public static void main(String[] args) {
        PrefPlans test = new PrefPlans();
        test.setPreference("C:\\csc\\CSC305\\Tests\\yur.GymPlanFile");
        System.out.println("returning plan 1: " + test.getPlan1());
    }
}
