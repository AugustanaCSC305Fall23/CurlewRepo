package csc305.gymnasticsApp.filters;

import csc305.gymnasticsApp.data.Card;

/**
 * The EquipmentFilter class is an implementation of the filters interface
 * designed to filter Card objects based on a desired equipment keyword.
 */
public class EquipmentFilter implements CardFilter {
    private final String desiredEquipment;

    /**
     * Constructs an EquipmentFilter with the specified desired equipment keyword
     *
     * @param desiredEquipment The equipment keyword to filter by
     */
    public EquipmentFilter(String desiredEquipment) {
        this.desiredEquipment = desiredEquipment;
    }

    /**
     * Checks if a Card object's equipment contains the desired equipment keyword
     *
     * @param card - The Card to be evaluated for a match.
     * @return true if the card's equipment contains the desired keyword, false otherwise
     */
    @Override
    public boolean matches(Card card) {
        return card.getEquipment().toUpperCase().contains(desiredEquipment.toUpperCase());
    }
}
