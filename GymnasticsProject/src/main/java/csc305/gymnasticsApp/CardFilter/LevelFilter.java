package csc305.gymnasticsApp.CardFilter;

import csc305.gymnasticsApp.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * The LevelFilter class is an implementation of the CardFilter interface
 * designed to filter Card objects based on a desired level.
 */
public class LevelFilter implements CardFilter{
    public static List<String> desiredLevels;
    /**
     * Constructs a new LevelFilter with the desired level to match.
     */
    public LevelFilter() {
        if (desiredLevels == null) {
            desiredLevels = new ArrayList<>();
        }
    }

    public void add(String desiredLevel) {
        if(!(desiredLevels.contains(desiredLevel))) {
            desiredLevels.add(desiredLevel);
        } else {
            desiredLevels.remove(desiredLevel);
        }
    }

    public List<String> getDesiredLevels() {
        return desiredLevels;
    }

    public void reset(){
        desiredLevels.clear();
        desiredLevels.add("ALL");
    }


    /**
     * Determines whether a given Card matches the desired level.
     *
     * @param canidateCard - The Card to be evaluated for a level match.
     * @return true if the candidateCard's level matches the desired level; otherwise, false.
     */
    @Override
    public boolean matches(Card canidateCard) {
        boolean match = false;
        if (desiredLevels.size() == 1) {
            match = true;
        }else if(canidateCard.getLevel().equalsIgnoreCase("ALL")){
            match = true;
        }else {
            for (String level : desiredLevels) {
                String[] cardLevels = canidateCard.getLevel().split(" ");
                for (String levelInCard : cardLevels) {
                    if (levelInCard.equalsIgnoreCase(level)) {
                        match = true;
                    }
                }
            }
        }
        return match;
    }
}
