package dice.service;


import dice.Dice;

public class DiceService {

    final private int MIN = 1;
    final private int MAX = 6;


    /**
     * generalDice 던지기
     * @return generalDice 숫자
     */
    public int getGeneralDice() {
        return 1;
    }

    /**
     * generalDice 가 짝수인 경우 special 주사위 던지기 가능
     * @return isEven
     */
    public boolean isEven(Dice gerneralDice){
        int diceNumber = gerneralDice.getDiceNumber();
        Boolean isEven = diceNumber % 2 ==0 ? true : false;
        return isEven;
    }

    /**
     * generalDice 의 숫자가 isEven인 경우 specialDice 굴리기
     * @return specialDice number
     */












}
