package csc305.gymnasticsApp.CardFilter;

import csc305.gymnasticsApp.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * The EquipmentFilter class is an implementation of the CardFilter interface
 * designed to filter Card objects based on a desired equipment keyword.
 */
public class EquipmentFilter implements CardFilter {
    public static List<String> desiredEquipments;

    /**
     * Constructs a new EquipmentFilter with the desired equipment keyword to match.
     */
    public EquipmentFilter() {
        if (desiredEquipments == null) {
            desiredEquipments = new ArrayList<>();
        }
    }


    public void add(String equipment) {
        if (!(desiredEquipments.contains(equipment))) {
            desiredEquipments.add(equipment);
        } else {
            desiredEquipments.remove(equipment);
        }
    }


    public static List<String> getDesiredEquipments() {
        return desiredEquipments;
    }

    /**
     * Determines whether a given Card matches the desired equipment keyword.
     *
     * @param canidateCard - The Card to be evaluated for a match.
     * @return true if the candidateCard's keywords contain the desired equipment keyword; otherwise returns false.
     */
    @Override
    public boolean matches(Card canidateCard) {
        //Splits the keywords of the candidateCard into an array.
//        String[] equipmentList = canidateCard.getKeywords().split(",");
//        boolean match = false;
//
//        // Checks if the desired equipment keyword is present in the list of keywords.
//        for(String equipment : equipmentList){
//            if(equipment.equalsIgnoreCase(desiredEquipments.toString())){
//                match = true;
//            }
//        }
//        return match;
        return (canidateCard.getEquipment().equalsIgnoreCase(desiredEquipments.toString()));


    }
}
