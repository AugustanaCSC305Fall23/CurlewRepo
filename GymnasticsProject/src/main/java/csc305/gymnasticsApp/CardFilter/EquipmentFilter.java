package csc305.gymnasticsApp.CardFilter;

import csc305.gymnasticsApp.Card;

public class EquipmentFilter implements CardFilter {
    private String desiredEquipment;
    public EquipmentFilter(String desiredEquipment) {
        this.desiredEquipment = desiredEquipment;
    }

    @Override
    public boolean matches(Card canidateCard) {
        String[] equipmentList = canidateCard.getKeywords().split(",");
        boolean match = false;
        for(String equipment : equipmentList){
            if(equipment.equalsIgnoreCase(desiredEquipment)){
                match = true;
            }
        }
        return match;

    }
}
