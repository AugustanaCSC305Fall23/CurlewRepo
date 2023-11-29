package csc305.gymnasticsApp.filters;

import csc305.gymnasticsApp.data.Card;
import csc305.gymnasticsApp.data.CardDatabase;

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
