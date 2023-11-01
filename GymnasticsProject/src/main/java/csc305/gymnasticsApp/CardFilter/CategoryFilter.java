package csc305.gymnasticsApp.CardFilter;

import csc305.gymnasticsApp.Card;

public class CategoryFilter implements CardFilter{
    private String desiredCategory;
    public CategoryFilter(String desiredCategory) {
        this.desiredCategory = desiredCategory;
    }

    @Override
    public boolean matches(Card canidateCard) {
        return(canidateCard.getCategory().equalsIgnoreCase(desiredCategory));

    }
}
