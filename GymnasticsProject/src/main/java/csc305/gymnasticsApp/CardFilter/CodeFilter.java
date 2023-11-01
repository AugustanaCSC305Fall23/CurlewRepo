package csc305.gymnasticsApp.CardFilter;

import csc305.gymnasticsApp.Card;

public class CodeFilter implements CardFilter {
    private String codeFilter;
    public CodeFilter(String codeFilter) {
        this.codeFilter = codeFilter;
    }

    @Override
    public boolean matches(Card canidateCard) {
        return(canidateCard.getCode().equalsIgnoreCase(codeFilter));

    }
}
