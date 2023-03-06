package dice.service;

import dice.SpecialDicePattern;
import player.Player;

import java.util.List;

public class DiceServiceImpl implements DiceService {

    @Override
    public void checkSpecialDicePattern(int specialDice, List<Player> playerList, int currentIdx) {
        SpecialDicePattern dicePattern = null;

        if(specialDice == SpecialDicePattern.PATTERN_BUDDY_FUCKER.getValue()) {
            dicePattern =  SpecialDicePattern.PATTERN_BUDDY_FUCKER;
            everyoneLossScore(playerList);
        } else if(specialDice == SpecialDicePattern.PATTERN_STEAL.getValue()) {
            dicePattern =  SpecialDicePattern.PATTERN_STEAL;
            stealEachPlayerScore(playerList, currentIdx);
        } else if(specialDice == SpecialDicePattern.PATTERN_DEATH.getValue()) {
            dicePattern =  SpecialDicePattern.PATTERN_DEATH;
            killPlayer(playerList, currentIdx);
        } else if(specialDice == SpecialDicePattern.PATTERN_DONATE.getValue()) {
            dicePattern =  SpecialDicePattern.PATTERN_DONATE;
            donateToEachPlayer(playerList, currentIdx);
        } else {
            dicePattern = SpecialDicePattern.PATTERN_NOTHING;
        }

        System.out.println("pattern: "+ dicePattern.getName() +", value: "+ dicePattern.getValue() +"\n");
    }

    @Override
    public void everyoneLossScore(List<Player> playerList) {
        final int LOSS_SCORE = 2;

        for(int i=0; i<playerList.size(); i++) {
            int totalScore = playerList.get(i).getTotalDiceScore();

            if(totalScore - LOSS_SCORE > 0) { totalScore -= LOSS_SCORE; }
            else { totalScore = 0; }

            playerList.get(i).setTotalDiceScore(totalScore);
        }
    }

    @Override
    public void stealEachPlayerScore(List<Player> playerList, int currentIdx) {
        int myDiceNumber = playerList.get(currentIdx).getTotalDiceScore();

        for (int i=0; i<playerList.size(); i++) {
            if (i == currentIdx) { continue; }

            int targetDiceNumber = playerList.get(i).getTotalDiceScore();

            int stealNumber = howMuchCanWeSteal(targetDiceNumber);
            myDiceNumber += stealNumber;
            playerList.get(i).setTotalDiceScore(targetDiceNumber - stealNumber);
        }

        playerList.get(currentIdx).setTotalDiceScore(myDiceNumber);
    }

    private int howMuchCanWeSteal (int targetDiceNumber) {
        final int STEAL_SCORE = 3;

        if (targetDiceNumber - STEAL_SCORE >= 0) {
            return STEAL_SCORE;
        }
        return targetDiceNumber;
    }

    @Override
    public void donateToEachPlayer(List<Player> playerList, int currentIdx) {
        int myDiceNumber = playerList.get(currentIdx).getTotalDiceScore();
        doDonate(myDiceNumber, playerList, currentIdx);
    }

    private void doDonate (int myDiceNumber, List<Player> playerList, int currentIdx) {
        final int DONATE_SCORE = 2;

        for (int i=0; i<playerList.size(); i++) {
            if (i == currentIdx) { continue; }

            int targetTotalScore = playerList.get(i).getTotalDiceScore();
            int donationValue = 0;

            if (myDiceNumber >= DONATE_SCORE) {
                myDiceNumber -= DONATE_SCORE;
                donationValue = DONATE_SCORE;
            } else if (myDiceNumber > 0) {
                myDiceNumber -= DONATE_SCORE;
                donationValue = 1;
            } else {
                System.out.println("기부 불가");
                donationValue = 0;
                myDiceNumber = 0;
            }

            playerList.get(i).setTotalDiceScore(targetTotalScore + donationValue);
            playerList.get(currentIdx).setTotalDiceScore(myDiceNumber);
        }
    }

    @Override
    public void killPlayer(List<Player> playerList, int currentIdx) {
        playerList.get(currentIdx).setAlive(false);
    }

}