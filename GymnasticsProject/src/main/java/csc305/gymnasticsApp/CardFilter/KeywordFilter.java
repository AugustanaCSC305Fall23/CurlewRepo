package csc305.gymnasticsApp.CardFilter;

import csc305.gymnasticsApp.Card;

public class KeywordFilter implements CardFilter{
    private String desiredKeyword;
    public KeywordFilter(String desiredKeyword) {
        this.desiredKeyword = desiredKeyword;
    }

    @Override
    public boolean matches(Card canidateCard) {
        String[] keywords = canidateCard.getKeywords().split(",");
        boolean match = false;
        for(String keyword : keywords){
            if(keyword.equalsIgnoreCase(desiredKeyword)){
                match = true;
            }
        }
        return match;
    }
}
