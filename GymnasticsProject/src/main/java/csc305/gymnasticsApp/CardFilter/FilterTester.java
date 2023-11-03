package csc305.gymnasticsApp.CardFilter;

import csc305.gymnasticsApp.Card;
import csc305.gymnasticsApp.CardDatabase;

public class FilterTester {



    public static void main(String[] args){
        testGenderFilter();
    }

    private static void testGenderFilter(){
        GenderFilter genderFilter = new GenderFilter();
        //genderFilter.add("n");
        genderFilter.add("f");
        for(Card card: CardDatabase.getAllCards()){
            if(genderFilter.matches(card)){
                System.out.println("yes" + card.getGender());
            }
        }
    }

}
