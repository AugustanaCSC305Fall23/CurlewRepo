package csc305.gymnasticsApp.CardFilter;

import csc305.gymnasticsApp.Card;
import csc305.gymnasticsApp.CardDatabase;

public class FilterTester {



    public static void main(String[] args){
        testFilter();
    }

    private static void testFilter(){
        EquipmentFilter genderFilter = new EquipmentFilter();

        for(Card card: CardDatabase.getAllCards()){
            if(genderFilter.matches(card)){
            }
        }
    }

}
