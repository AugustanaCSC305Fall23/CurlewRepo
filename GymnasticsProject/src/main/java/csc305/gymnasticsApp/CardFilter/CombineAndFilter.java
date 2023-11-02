package csc305.gymnasticsApp.CardFilter;

import csc305.gymnasticsApp.Card;
import csc305.gymnasticsApp.MainEditDisplayController;

import java.util.Arrays;
import java.util.List;

public class CombineAndFilter implements CardFilter {


    public CombineAndFilter() {


        List<String> filters = Arrays.asList(
                new GenderFilter().getDesiredGenders().toString());
        System.out.println(filters);
    }

    @Override
    public boolean matches(Card canidateCard) {
        return true;
    }
}
