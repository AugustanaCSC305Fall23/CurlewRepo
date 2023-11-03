package csc305.gymnasticsApp.CardFilter;

import csc305.gymnasticsApp.Card;

/**
 * The KeywordFilter class is an implementation of the CardFilter interface
 *  * designed to filter Card objects based on a desired keyword.
 */
public class KeywordFilter implements CardFilter{
    private String desiredKeyword;

    /**
     * Constructs a new KeywordFilter with the desired keyword to match.
     *
     * @param desiredKeyword - The keyword that Card objects should match.
     */
    public KeywordFilter(String desiredKeyword) {
        this.desiredKeyword = desiredKeyword;
    }
    /**
     * Determines whether a given Card matches the desired keyword.
     *
     * @param canidateCard -  The Card to be evaluated for a keyword match.
     * @return true if the candidateCard's keywords contain the desired keyword; otherwise, false.
     */
    @Override
    public boolean matches(Card canidateCard) {
        //Splits the keywords of the candidateCard into an array.
        String[] keywords = canidateCard.getKeywords().split(",");
        boolean match = false;
        //Checks if the desired keyword is present in the list of keywords.
        for(String keyword : keywords){
            if(keyword.equalsIgnoreCase(desiredKeyword)){
                match = true;
            }
        }
        return match;
    }
}
