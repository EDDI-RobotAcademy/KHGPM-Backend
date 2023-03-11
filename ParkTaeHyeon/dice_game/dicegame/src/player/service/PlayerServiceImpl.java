package player.service;

import player.Player;
import player.SpecialDicePattern;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PlayerServiceImpl implements PlayerService {

    final int EVEN_ODD_DECISION_VALUE = 2;
    final int EVEN = 0;

    // 1. 일반 주사위를 보고 짝수인지 판정한다.
    // 2. 짝수라면 특수 주사위의 숫자를 파악한다.

    // 2-1. 숫자 4가 나오는 경우 즉시 게임 오버된다.
    // 2-2. 숫자 3이 나오는 경우엔 친구들 주사위에서 점수를 3점씩 뺏어올 수 있다.
    // 2-3. 숫자 5가 나오는 경우엔 자신의 점수를 2점씩 친구들에게 나눠줘야 한다.
    // 2-4. 숫자 1의 경우엔 모두 다 함께 -2점씩 감점된다.

    // 3. 각 숫자에 적합한 작업을 진행한다.

    // 플레이어의 일반 주사위가 짝수인지 홀수 인지 구별하고 boolean으로 return
//    private Boolean isEven(List<Player> playerList, int currentIdx) {
//        int diceNumber = playerList.get(currentIdx).getGeneralDiceNumber();
//        Boolean isEven = diceNumber % EVEN_ODD_DECISION_VALUE == EVEN;
//
//        if (isEven) {
//            System.out.println("일반 주사위는 짝수입니다: " + diceNumber);
//        } else {
//            System.out.println("일반 주사위는 홀수입니다: " + diceNumber);
//        }
//
//        return isEven;
//    }

    // 플레이어의 스페셜다이스 숫자 가져와서 return 하기
    private int getSpecialDiceNumber (List<Player> playerList, int currentIdx) {
        return playerList.get(currentIdx).getSpecialDiceNumber();
    }

    // 스페셜다이스패턴 리턴하기
    private SpecialDicePattern checkSpecialDicePattern (
            int specialDice, List<Player> playerList, int currentIdx) {

        if (specialDice == SpecialDicePattern.PATTERN_DEATH.getValue())
            return SpecialDicePattern.PATTERN_DEATH;

        if (specialDice == SpecialDicePattern.PATTERN_STEAL.getValue())
            return SpecialDicePattern.PATTERN_STEAL;

        if (specialDice == SpecialDicePattern.PATTERN_DONATE.getValue())
            return SpecialDicePattern.PATTERN_DONATE;

        if (specialDice == SpecialDicePattern.PATTERN_BUDDY_FUCKER.getValue())
            return SpecialDicePattern.PATTERN_BUDDY_FUCKER;

        return SpecialDicePattern.PATTERN_NOTHING;
    }

    // 플레이어의 일반 다이스와 스페셜 다이스 합을 return
    private int calcTotalDiceNumber (List<Player> playerList, int currentIdx) {
        return playerList.get(currentIdx).getGeneralDiceNumber() +
                playerList.get(currentIdx).getSpecialDiceNumber();
    }

    // PATTERN_STEAL 1점 가져오기에 대한 메소드
    private int howMuchCanWeSteal (int myDiceNumber, int targetDiceNumber) {
        final int STEAL_SCORE = 1;

        if (targetDiceNumber - STEAL_SCORE >= 0) {
            return STEAL_SCORE;
        }

        return targetDiceNumber;
    }

    // calcTotalDiceNumber에서 플레이어의 주사위 합을 myDiceNumber에 담고
    // howMuchCanWeSteal에서 스틸당한 값을 계산한다.
    // 스틸당한 값을 플레이어의 idx에 setTotalDiceScore(myDiceNumber)로 셋해준다.
    private void stealEachPlayerScore (List<Player> playerList, int currentIdx) {

        //int myDiceNumber = calcTotalDiceNumber(playerList, currentIdx);
        int myDiceNumber = playerList.get(currentIdx).getTotalDiceScore();

        for (int i = 0; i < playerList.size(); i++) {
            if (i == currentIdx) { continue; }

            //int targetDiceNumber = calcTotalDiceNumber(playerList, i);
            int targetDiceNumber = playerList.get(i).getTotalDiceScore();

            int stealNumber = howMuchCanWeSteal(myDiceNumber, targetDiceNumber);
            myDiceNumber += stealNumber;
            playerList.get(i).setTotalDiceScore(targetDiceNumber - stealNumber);
        }

        playerList.get(currentIdx).setTotalDiceScore(myDiceNumber);
    }

    // stealEachPlayerScore와 비슷한 로직을 가졌다.
    // PATTERN_DONATE에 대한 2점 기부하기
    private void doDonate (int myDiceNumber, List<Player> playerList, int currentIdx) {
        final int DONATE_SCORE = 2;

        System.out.println("현재 점수: " + myDiceNumber + ", 플레이어: " + currentIdx);

        for (int i = 0; i < playerList.size(); i++) {
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

    // doDonate에 점수, 플레이어, idx 보내기 위한 작업
    private void donateToEachPlayer (List<Player> playerList, int currentIdx) {
        final int DONATE_SCORE = 2;

        //int myDiceNumber = calcTotalDiceNumber(playerList, currentIdx);
        int myDiceNumber = playerList.get(currentIdx).getTotalDiceScore();

        doDonate(myDiceNumber, playerList, currentIdx);
    }

    // 스페셜다이스패턴이 이루어졌을때 메소드를 호출 할 수 있도록 하는 것
    private void everyoneLossScore (List<Player> playerList) {
        final int LOSS_SCORE = 2;

        for (int i = 0; i < playerList.size(); i++) {
            //int totalScore = calcTotalDiceNumber(playerList, i);
            int totalScore = playerList.get(i).getTotalDiceScore();

            if (totalScore - LOSS_SCORE > 0) { totalScore -= LOSS_SCORE; }
            else { totalScore = 0; }

            playerList.get(i).setTotalDiceScore(totalScore);
        }
    }

    // 플레이어의 스페셜주사위 숫자를 뽑아와서
    // 스페셜다이스패턴을 불러오고 불러온 스페셜다이스패턴을
    // dicepattern에 담아 postProcessAfterGetPattern 메소드를 호출한다.
    private void postProcessAfterGetPattern (
            SpecialDicePattern dicePattern,
            List<Player> playerList,
            int currentIdx
    ) {

            //int myDiceNumber = calcTotalDiceNumber(playerList, currentIdx);
            //int targetDiceNumber;

            if (dicePattern == SpecialDicePattern.PATTERN_DEATH)
                playerList.get(currentIdx).setAlive(false);

            // 상대방의 Dice를 보고 뺏어와야함(없으면 뺏을것이 없는 상태이기도함)
            if (dicePattern == SpecialDicePattern.PATTERN_STEAL)
                stealEachPlayerScore(playerList, currentIdx);

            if (dicePattern == SpecialDicePattern.PATTERN_DONATE)
                donateToEachPlayer(playerList, currentIdx);

            if (dicePattern == SpecialDicePattern.PATTERN_BUDDY_FUCKER)
                everyoneLossScore(playerList);

            if (dicePattern == SpecialDicePattern.PATTERN_NOTHING) {
                // playerList.get(currentIdx).setTotalDiceScore(
                //        calcTotalDiceNumber(playerList, currentIdx));
            }

    }

    private void applyEachPlayer (List<Player> playerList, int currentIdx) {
        /*
        if (isEven(playerList, currentIdx)) {

            int specialDice = getSpecialDiceNumber(playerList, currentIdx);

            SpecialDicePattern dicePattern =
                    checkSpecialDicePattern(specialDice, playerList, currentIdx);

            System.out.println("pattern: " + dicePattern.getName() +
                    "value: " + dicePattern.getValue());

            postProcessAfterGetPattern(dicePattern, playerList, currentIdx);
        }
         */

        int specialDice = getSpecialDiceNumber(playerList, currentIdx);

        SpecialDicePattern dicePattern =
                checkSpecialDicePattern(specialDice, playerList, currentIdx);

        System.out.println("pattern: " + dicePattern.getName() +
                " value: " + dicePattern.getValue());

        postProcessAfterGetPattern(dicePattern, playerList, currentIdx);
    }
    @Override
    public void playDiceGame(List<Player> playerList) {
        System.out.println("");

        for (int i = 0; i < playerList.size(); i++) {
            System.out.println("player" + (i + 1) + ": ");
            applyEachPlayer(playerList, i);

        }
        System.out.println("");

        for (int i = 0; i < playerList.size(); i++) {
            System.out.println("총합: " + playerList.get(i).getTotalDiceScore());
        }
        System.out.println("");
    }

    @Override
    public Player findWinner(List<Player> playerList) {
        int winnerIdx = 0;

        Collections.sort(playerList, Collections.reverseOrder());
        System.out.println("After Sort");
        System.out.println(playerList);

        for (int i = 0; i < playerList.size(); i++) {

            if (playerList.get(i).isAlive() == true) { break; }

            winnerIdx = i + 1;
            // 점수 제일 높은 플레이어가 죽었으니까
            // if를 거치지 않고 winnerIdx가 (0 + 1)이 된다.
        }

        // 다 죽었을때 가정해서 무승부 null 리턴
        if (winnerIdx == playerList.size()) { return null; }

        // 1번 10점, 2번 10점, 3번 10점
        // 1번, 3번 죽고 우승자 2번일때 사용되는 if문
        if (winnerIdx == playerList.size() - 1){
            return playerList.get(winnerIdx);
        }

        if (playerList.get(winnerIdx).getTotalDiceScore()
                == playerList.get(winnerIdx + 1).getTotalDiceScore()) {

            System.out.println("무승부");
            return null;
        }

        return playerList.get(winnerIdx);
    }
}
