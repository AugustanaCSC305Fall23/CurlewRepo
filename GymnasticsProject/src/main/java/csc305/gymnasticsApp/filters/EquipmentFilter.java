package csc305.gymnasticsApp.filters;

import csc305.gymnasticsApp.data.Card;

/**
 * The EquipmentFilter class is an implementation of the filters interface
 * designed to filter Card objects based on a desired equipment keyword.
 */
public class EquipmentFilter implements CardFilter {
    private final String desiredEquipment;

    public EquipmentFilter(String desiredEquipment) {
        this.desiredEquipment = desiredEquipment;
    }

    @Override
    public boolean matches(Card card) {
        return card.getEquipment().toUpperCase().contains(desiredEquipment.toUpperCase());
    }
}
