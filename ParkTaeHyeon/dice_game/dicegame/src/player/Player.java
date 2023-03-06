package player;

public class Player implements Comparable<Player> {

    final private Long playerId;
//    final private String nickname;
    final private Dice generalDice;
    final private Dice specialDice;

    private int totalDiceScore;
    private boolean isAlive;

    public Player(Long playerId, String nickname) {
        this.playerId = playerId;
//        this.nickname = nickname;
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
        return "Player {" +
                "플레이어 = " + playerId +
                ", 일반 주사위 = " + generalDice +
                ", 스페셜 주사위 = " + specialDice +
                ", 주사위 총합 = " + totalDiceScore +
                '}' + '\n';
    }

    public int getGeneralDiceNumber() {
        return generalDice.getDiceNumber();
    }

    public int getSpecialDiceNumber() {
        return specialDice.getDiceNumber();
    }

//    public void calcMyTotalDiceScore () {
//        this.totalDiceScore = generalDice.getDiceNumber() +
//                specialDice.getDiceNumber();
//    }

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

    // comparable 인터페이스와 compareTo 메소드를를 이용한 내림차순 정렬
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
