package player;

import dice.Dice;

public class Player {
    final private Long playerId;
    final private String nickname;

    final private Dice generalDice;
    final private Dice specialDice;

    public Player(Long playId, String nickname){
        this.playerId = playId;
        this.nickname = nickname;
        this.generalDice = new Dice();
        this.specialDice = new Dice();
    }
    public int getGeneralDice() {
        return generalDice.getDiceNumber();
    }
    public int getSpecialDice() {
        return specialDice.getDiceNumber();
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", nickname='" + nickname + '\'' +
                ", generalDice=" + generalDice +
                ", specialDice=" + specialDice +
                '}';
    }
}
