package player;

public class Player implements Comparable<Player> {

    final private Long playerId;
    final private String nickname;
    final private Dice generalDice;
    final private Dice specialDice;

    private int totalDiceScore;
    private boolean isAlive;

    public Player(Long playerId, String nickname) {
        this.playerId = playerId;
        this.nickname = nickname;
        this.generalDice = new Dice();

        if (generalDice.getDiceNumber() % 2 == 0) {
            this.specialDice = new Dice();
        } else {
            this.specialDice = new Dice(0);
        }

        isAlive = true;
        totalDiceScore = getGeneralDiceNumber() + getSpecialDiceNumber();
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", nickname='" + nickname + '\'' +
                ", generalDice=" + generalDice +
                ", specialDice=" + specialDice +
                '}' + '\n';
    }

    public int getGeneralDiceNumber() {
        return generalDice.getDiceNumber();
    }

    public int getSpecialDiceNumber() {
        return specialDice.getDiceNumber();
    }

    public void calcMyTotalDiceScore () {
        this.totalDiceScore = generalDice.getDiceNumber() +
                specialDice.getDiceNumber();
    }

    public int getTotalDiceScore() {
        return totalDiceScore;
    }

    public void setTotalDiceScore(int totalDiceScore) {
        this.totalDiceScore = totalDiceScore;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public int compareTo(Player otherPlayer) {
        if (otherPlayer.totalDiceScore < totalDiceScore) {
            return 1;
        } else if (otherPlayer.totalDiceScore > totalDiceScore) {
            return -1;
        }

        return 0;
    }
}
