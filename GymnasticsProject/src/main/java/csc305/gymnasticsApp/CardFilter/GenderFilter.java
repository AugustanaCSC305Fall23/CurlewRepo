package csc305.gymnasticsApp.CardFilter;

import csc305.gymnasticsApp.Card;

import java.util.ArrayList;
import java.util.List;

public class GenderFilter implements CardFilter {
    public static List<String> desiredGenders;
    public GenderFilter() {
        if (desiredGenders == null) {
            desiredGenders = new ArrayList<String>();
        }
    }

    public List<String> getDesiredGenders() {
        return desiredGenders;
    }

    public void add(String desiredGender) {
        if(!(desiredGenders.contains(desiredGender))) {
            desiredGenders.add(desiredGender);
        } else {
            desiredGenders.remove(desiredGender);
        }
    }

    @Override
    public boolean matches(Card canidateCard) {
        boolean match = false;
        for(String filter:desiredGenders){
            if(filter.equalsIgnoreCase(canidateCard.getGender())){
                match = true;
            }
        }
        return match;

    }
}
