package player;

import utility.CustomRandom;

public class Dice {

    final private int MIN = 1;
    final private int MAX = 6;
    final private int diceNumber;

    public Dice () {
        this.diceNumber =
                CustomRandom.createCustomRandom(MIN, MAX);
    }

    public Dice (int zero) {
        this.diceNumber = zero;
    }

    public int getDiceNumber() {
        return diceNumber;
    }

    @Override
    public String toString() {
        return "Dice{" +
                "diceNumber=" + diceNumber +
                '}';
    }
}
