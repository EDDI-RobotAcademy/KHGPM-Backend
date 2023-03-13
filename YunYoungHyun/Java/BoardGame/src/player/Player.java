package player;

import dice.Dice;

/***
 * Entity 객체
 */
public class Player implements Comparable<Player> {

    final private Long playerId;
    final private String nickname;
    final private Dice generalDice;
    final private Dice specialDice;
    private int totalDiceScore;
    private boolean isAlive;
    private String calculate = "";
    private String patternName = "";

    public Player(Long playerId, String nickname) {
        this.playerId = playerId;
        this.nickname = nickname;
        this.generalDice = new Dice();

        if(generalDice.getDiceNumber() % 2 == 0) {
            this.specialDice = new Dice();
        } else {
            this.specialDice = new Dice(0);
        }
        isAlive = true;
        totalDiceScore = getGeneralDiceNumber() + getSpecialDiceNumber();
//        this.calculate = String.valueOf(getGeneralDiceNumber()) + "(일반) + "+ String.valueOf(getSpecialDiceNumber()) + "(특수)";
    }

    public Long getPlayerId() {
        return this.playerId;
    }

    public int getGeneralDiceNumber() {
        return generalDice.getDiceNumber();
    }

    public int getSpecialDiceNumber() {
        return specialDice.getDiceNumber();
    }

    public int getTotalDiceScore() {
        return totalDiceScore;
    }
    public void setTotalDiceScore(int totalDiceScore) {
        this.totalDiceScore = totalDiceScore;
    }

    public boolean isAlive() {
        return isAlive;
    }
    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public String getCalculate() {
        return calculate;
    }
    public void setCalculate(String calculate) {
        this.calculate += calculate;
    }

    public String getPatternName() {
        return patternName;
    }
    public void setPatternName(String patternName) {
        this.patternName = patternName;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", nickname='" + nickname + '\'' +
                ", generalDice=" + generalDice +
                ", specialDice=" + specialDice +
                ", totalDiceScore=" + totalDiceScore +
                '}'+ '\n';
    }

    @Override
    public int compareTo(Player otherPlayer) {
        if(otherPlayer.totalDiceScore < totalDiceScore) {
            return 1;
        } else if(otherPlayer.totalDiceScore > totalDiceScore) {
            return -1;
        }
        return 0;
    }

}