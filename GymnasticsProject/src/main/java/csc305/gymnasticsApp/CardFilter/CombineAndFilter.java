package csc305.gymnasticsApp.CardFilter;

import csc305.gymnasticsApp.Card;
import csc305.gymnasticsApp.CardFilter.CardFilter;
import csc305.gymnasticsApp.MainEditDisplayController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombineAndFilter implements CardFilter {


    public CombineAndFilter() {
        String desiredGender = new MainEditDisplayController().getGenderCheckBox();
        List<CardFilter> filters = Arrays.asList(
                new GenderFilter(desiredGender));
        System.out.println(filters);
    }

    @Override
    public boolean matches(Card canidateCard) {
        return true;
    }
}
