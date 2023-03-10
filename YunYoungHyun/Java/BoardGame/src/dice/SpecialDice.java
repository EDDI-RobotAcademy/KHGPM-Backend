package dice;

import utility.CustomRandom;

import java.util.Arrays;

public class SpecialDice {
    final private int MIN = 1;
    final private int MAX = 6;
    final private int diceNumber;
    final private SpecialDicePattern specialDicePattern;

    public SpecialDicePattern getSpecialDicePattern() {
        return specialDicePattern;
    }

    public SpecialDice () {
        this.diceNumber = CustomRandom.createCustomRandom(MIN, MAX);

        SpecialDicePattern[] patterns = SpecialDicePattern.values();
        specialDicePattern = Arrays.stream(patterns).filter(elem -> elem.getValue() == diceNumber).
                findAny().orElse(SpecialDicePattern.PATTERN_NOTHING);
    }

}