package csc305.gymnasticsApp.filters;

import csc305.gymnasticsApp.data.Card;

public class SearchBarFilter implements CardFilter{

    public static String keyword;

    public SearchBarFilter() {
        if (keyword == null) {
            keyword = "";
        }
    }


    public void add(String word) {
        keyword = word;
    }

    @Override
    public boolean matches(Card canidateCard) {
        //Splits the keywords of the candidateCard into an array.
        String uniqueID = canidateCard.getUniqueID();

        return (uniqueID.toLowerCase().contains(keyword.toLowerCase()));
    }
}
