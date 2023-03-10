package dice;

import utility.CustomRandom;

/***
 * VO 객체
 */
public class Dice {

    final private int MIN = 1;
    final private int MAX = 6;
    final private int diceNumber;

    public Dice() {
        this.diceNumber = CustomRandom.createCustomRandom(MIN, MAX);
    }

    public Dice(int zero) {
        this.diceNumber = zero;
    }

    public int getDiceNumber() {
        return this.diceNumber;
    }

    @Override
    public String toString() {
        return "Dice{" +
                "diceNumber=" + diceNumber +
                '}';
    }

}