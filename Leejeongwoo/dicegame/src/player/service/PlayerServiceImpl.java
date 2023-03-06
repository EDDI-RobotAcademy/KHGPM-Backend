package player.service;

import player.Player;
import player.SpecialDicePattern;

import java.util.Collections;
import java.util.List;

public class PlayerServiceImpl implements PlayerService {

      // 첫번째 주사위 홀/짝 판별 할때 상수 지정
//    final int EVEN_ODD_DECISION_VALUE = 2;
//    final int EVEN = 0;

    // 1. 일반 주사위를 보고 짝수인지 판정한다.
    // 2. 짝수라면 특수 주사위의 숫자를 파악한다.

    // 2-1. 숫자 4가 나오는 경우 즉시 게임 오버된다.
    // 2-2. 숫자 3이 나오는 경우엔 친구들 주사위에서 점수를 3점씩 뺏어올 수 있다.
    // 2-3. 숫자 5가 나오는 경우엔 자신의 점수를 2점씩 친구들에게 나눠줘야 한다.
    // 2-4. 숫자 1의 경우엔 모두 다 함께 -2점씩 감점된다.

    // 3. 각 숫자에 적합한 작업을 진행한다.

    // 첫번째 주사위 홀/짝 판별
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

    private int getSpecialDiceNumber (List<Player> playerList, int currentIdx) {
        return playerList.get(currentIdx).getSpecialDiceNumber();
    }

    private SpecialDicePattern checkSpecialDicePattern (
            int specialDice, List<Player> playerList, int currentIdx) {

        // 2번째 주사이가 4가 나오면
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

//    private int calcTotalDiceNumber (List<Player> playerList, int currentIdx) {
//        return playerList.get(currentIdx).getGeneralDiceNumber() +
//                playerList.get(currentIdx).getSpecialDiceNumber();
//    }

    //3점씩 점수 가져오기~
    private int howMuchCanWeSteal (int myDiceNumber, int targetDiceNumber) {
        final int STEAL_SCORE = 3;

        // 만약에 그놈 점수 - 3 을했을 때 0보다 크거나 같으면 3가져올 수있죠
        if (targetDiceNumber - STEAL_SCORE >= 0) {
            return STEAL_SCORE;
        }
//        else if (targetDiceNumber - STEAL_SCORE == -3) {
//            System.out.println("뺏을 점수가 없음");
//            return 0;
//        } else {
        //그게 아니면 그냥 그놈 점수 다 가져와라(그뜻은 3보다 작은 점수가 되겠죠)
        return targetDiceNumber;
//        }
    }

    private void stealEachPlayerScore (List<Player> playerList, int currentIdx) {

//        int myDiceNumber = calcTotalDiceNumber(playerList, currentIdx);
        int myDiceNumber = playerList.get(currentIdx).getTotalDiceScore();

        for (int i = 0; i < playerList.size(); i++) {

            // 점수 뺏어올때 내 순서가 되면 다음 순서로 넘어가라
            if (i == currentIdx) { continue; }

            int targetDiceNumber = playerList.get(i).getTotalDiceScore();
            int stealNumber = howMuchCanWeSteal(myDiceNumber, targetDiceNumber);
            // 내 점수에 스틸한 점수 더해서 저장
            myDiceNumber += stealNumber;
//            myDiceNumber += howMuchCanWeSteal(myDiceNumber, targetDiceNumber);
            //뺏긴놈은 뺏긴 점수 계산해서 저장
            playerList.get(i).setTotalDiceScore(targetDiceNumber - stealNumber);
        }
        //나도 내점수 저장~
        playerList.get(currentIdx).setTotalDiceScore(myDiceNumber);
    }

    //2점씩 기부~
    private void doDonate (int myDiceNumber, List<Player> playerList, int currentIdx) {
        final int DONATE_SCORE = 2;

//        System.out.println("현재 점수: " + myDiceNumber + ", 플레이어: " + currentIdx);

        for (int i = 0; i < playerList.size(); i++) {
            // 점수 뺏어올때 내 순서가 되면 다음 순서로 넘어가라
            if (i == currentIdx) { continue; }

            int targetTotalScore = playerList.get(i).getTotalDiceScore();
            int donationValue = 0;

            // 내 점수가 2 보다 크거나 같으면
            // 내점수에서 2를 빼고 저장하고
            // 기부할 점수 = 2
            if (myDiceNumber >= DONATE_SCORE) {
                myDiceNumber -= DONATE_SCORE;
                donationValue = DONATE_SCORE;

            // 내점수가 0보다 크면
            // 내점수에서 2빼고 저장하고
            // 기부할 점수 = 1
            } else if (myDiceNumber > 0) {
                myDiceNumber -= DONATE_SCORE;
                donationValue = 1;

            // 내 점수가 0보다 작으면 기부할께 없으니까
            // 당연히 기부 못하겠지?
            // 그래서 내 점수 = 0
            // 기부할 점수 = 0
            } else {
                System.out.println("기부 불가");
                myDiceNumber = 0;
                donationValue = 0;
            }

            // targetTotalScore 이놈 점수에 (위의 if문을 통해) 기부할 점수 더해줌
            playerList.get(i).setTotalDiceScore(targetTotalScore + donationValue);
            //나도 내점수 저장~
            playerList.get(currentIdx).setTotalDiceScore(myDiceNumber);
        }
    }

    private void donateToEachPlayer (List<Player> playerList, int currentIdx) {
        final int DONATE_SCORE = 2;

        //int myDiceNumber = calcTotalDiceNumber(playerList, currentIdx);
        int myDiceNumber = playerList.get(currentIdx).getTotalDiceScore();

        doDonate(myDiceNumber, playerList, currentIdx);
    }

    // 전부다 2점씩 감점~
    private void everyoneLossScore (List<Player> playerList) {
        final int LOSS_SCORE = 2;

        for (int i = 0; i < playerList.size(); i++) {
            int totalScore = playerList.get(i).getTotalDiceScore();

            // 토탈 점수 - 2 가 0 보다 크면
            // 토탈 점수에서 2빼서 저장하고
            if (totalScore - LOSS_SCORE > 0) {
                totalScore -= LOSS_SCORE;

            // 토탈 점수 -2 가 0보다 작으면
            // 토탈 점수 = 0
            }else {
                totalScore = 0;
            }

            playerList.get(i).setTotalDiceScore(totalScore);
        }
    }

    //스페셜주사위까지 다 던지고 다음 꺼 진행~
    private void postProcessAfterGetPattern (
            SpecialDicePattern dicePattern,
            List<Player> playerList,
            int currentIdx
    ) {

        //int myDiceNumber = calcTotalDiceNumber(playerList, currentIdx);
        //int targetDiceNumber;

        // 주사위 4 나오면 Alive->false로 죽임
        if (dicePattern == SpecialDicePattern.PATTERN_DEATH)
            playerList.get(currentIdx).setAlive(false);

        // 주사위 3 나오면 상대방의 Dice를 보고 뺏어와야함(없으면 뺏을것이 없는 상태이기도함)
        if (dicePattern == SpecialDicePattern.PATTERN_STEAL)
            stealEachPlayerScore(playerList, currentIdx);

        // 주사위 5 나오면 점수 2씩 나눠줌
        if (dicePattern == SpecialDicePattern.PATTERN_DONATE)
            donateToEachPlayer(playerList, currentIdx);

        // 주사위 1 나오면 자폭ㅋ
        if (dicePattern == SpecialDicePattern.PATTERN_BUDDY_FUCKER)
            everyoneLossScore(playerList);

        if (dicePattern == SpecialDicePattern.PATTERN_NOTHING) {
//            playerList.get(currentIdx).setTotalDiceScore(
//                    calcTotalDiceNumber(playerList, currentIdx));
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

        SpecialDicePattern dicePattern = checkSpecialDicePattern(specialDice, playerList, currentIdx);

        System.out.println("두번째 주사위가 " + dicePattern.getValue()+" 나와서 " + dicePattern.getName());

        postProcessAfterGetPattern(dicePattern, playerList, currentIdx);
    }
    @Override
    public void playDiceGame(List<Player> playerList) {
        System.out.println("------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < playerList.size(); i++) {
            System.out.println("player" + (i + 1) + ": ");
            applyEachPlayer(playerList, i);

            //플레이어의 총합이 게임 종료일 때가 아니다 ㅠㅠ
            System.out.println("주사위의 총합은 " + playerList.get(i).getTotalDiceScore() + "입니다.");

            System.out.println();
        }
        // 플레이어의 총합이 전체적 으로 계산 됨 근데 따로 나와서 맘에 안듬
        for (int i = 0; i < playerList.size(); i++) {
            System.out.println("주사위의 총합은 " + playerList.get(i).getTotalDiceScore() + "입니다.");
        }
        System.out.println("------------------------------------------------------------------------------------------------------");

    }

    //누가 승자인지 찾자~
    @Override
    public Player findWinner(List<Player> playerList) {
        int winnerIdx = 0;

        // sort에 의해 자동으로 정렬 때림 -> totalDiceScore가 큰 순으로!!!!!
        Collections.sort(playerList, Collections.reverseOrder());
        System.out.println("-> 정렬 후 입니다.");
        System.out.println(playerList);

        // 정렬하고 반복문을 돌렸기 때문에 0번 인덱스에는 최고점인 플레이어가 있을 것이다.
        for (int i = 0; i < playerList.size(); i++) {

            System.out.println("winnerIdx  ->> "+winnerIdx);

            // 현재 플레이어(i)가 살아 있으면 break
            // 살아 있으면 그놈이 우승자
            if (playerList.get(i).isAlive() == true) {
                break;
            }
            // 현재 플레이어(i)가 죽었다면
            // 인덱스 i에 +1

            winnerIdx = i + 1;
        }

        // (1 or 2 or 3) == 3 이면 null 리턴
        // 다 뒤졌을 때 ;;
        if (winnerIdx == playerList.size()) {
            return null;
        }

        // (1 or 2 or 3) == 2 이면
        if (winnerIdx == playerList.size() - 1) {
            return playerList.get(winnerIdx);
        }

        //플레이어(승자)의 점수 == 플레이어 의 점수가 같으면 무승부
        //3명 다 똑같아도 무승부
        if (playerList.get(winnerIdx).getTotalDiceScore()
                == playerList.get(winnerIdx + 1).getTotalDiceScore()) {
            System.out.println("무승부");
            return null;
        }

        return playerList.get(winnerIdx);
    }
}