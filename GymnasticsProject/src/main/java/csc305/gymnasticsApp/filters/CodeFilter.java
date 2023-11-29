package csc305.gymnasticsApp.filters;

import csc305.gymnasticsApp.data.Card;

/**
 * The CodeFilter class is an implementation of the filters interface
 * designed to filter Card objects based on a desired code.
 */
public class CodeFilter implements CardFilter {
    private String codeFilter;

    /**
     * Constructs a new CodeFilter with the desired code to match.
     *
     * @param codeFilter - The code that Card objects should match.
     */
    public CodeFilter(String codeFilter) {
        this.codeFilter = codeFilter;
    }

    /**
     * Determines whether a given Card matches the desired code.
     *
     * @param canidateCard - The Card to be evaluated for a match.
     * @return true if the candidateCard's code matches the desired code; otherwise returns false
     */
    @Override
    public boolean matches(Card canidateCard) {
        return(canidateCard.getCode().equalsIgnoreCase(codeFilter));

    }

    @Override
    public void reset() {}
}
