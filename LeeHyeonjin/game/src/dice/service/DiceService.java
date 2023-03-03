package dice.service;


import dice.Dice;

public class DiceService {

    final private int MIN = 1;
    final private int MAX = 6;

    final private Dice generalDice;


    public DiceService(Dice generalDice, Dice specialDice) {
        this.generalDice = generalDice;
    }
    public int getGeneralDice() {
        return this.generalDice.getDiceNumber();
    }





}
