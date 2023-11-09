package csc305.gymnasticsApp.CardFilter;

import csc305.gymnasticsApp.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * The EquipmentFilter class is an implementation of the CardFilter interface
 * designed to filter Card objects based on a desired equipment keyword.
 */
public class EquipmentFilter implements CardFilter {
    public static String desiredEquipments;

    /**
     * Constructs a new EquipmentFilter with the desired equipment keyword to match.
     */
    public EquipmentFilter() {
        if (desiredEquipments == null) {
            desiredEquipments = "";
        }
    }
    @Override
    public void reset() {
        desiredEquipments = "";
    }

    public void add(String equipment) {
        desiredEquipments = equipment;
    }


    //public static List<String> getDesiredEquipments() {
    //    return desiredEquipments;
    //}

    /**
     * Determines whether a given Card matches the desired equipment keyword.
     *
     * @param canidateCard - The Card to be evaluated for a match.
     * @return true if the candidateCard's keywords contain the desired equipment keyword; otherwise returns false.
     */
    @Override
    public boolean matches(Card canidateCard) {
        //Splits the keywords of the candidateCard into an array.
        String[] equipmentList = canidateCard.getEquipment().split(",|/");
        for(String equip:equipmentList){
            System.out.println(equip + "+");
        }
        boolean match = false;

        // Checks if the desired equipment keyword is present in the list of keywords.
        for(String equipment : equipmentList){
            if(equipment.toLowerCase().contains(desiredEquipments.toLowerCase())){
                match = true;
            }
        }
        return match;


    }
}
