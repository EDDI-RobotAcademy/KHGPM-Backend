package player.service;

import dice.service.DiceService;
import dice.service.DiceServiceImpl;
import player.Player;

import java.util.Collections;
import java.util.List;

public class PlayerServiceImpl implements PlayerService {

    @Override
    public Player findWinner(List<Player> playerList) {
        int winnerIdx = 0;

        Collections.sort(playerList, Collections.reverseOrder());

        for(int i=0; i<playerList.size(); i++) {
            if(playerList.get(i).isAlive()) { break; }

            winnerIdx = i + 1;
        }

        // Player1 : 10 - 죽음
        // Player2 : 10
        // Player3 : 10 - 죽음

        // Player1 : 10
        // Player2 : 10 - 죽음
        // Player3 : 10 - 죽음

        // Player1 : 10
        // Player2 : 10 - 죽음
        // Player3 : 10

        if(winnerIdx == playerList.size()) { return null; }

        // 이 if 문을 굳이 만들어준 이유? 여기서 return 해주지 않으면
        // 바로 밑에 있는 if 문에서 indexOutOfBounds? 같은 에러 뜨지 않을까?
        // 아니면 데이터가 없어서 그에 관한 에러가 뜰 지도?
        if(winnerIdx == playerList.size() - 1) { return playerList.get(winnerIdx); }

        if(playerList.get(winnerIdx).getTotalDiceScore() == playerList.get(winnerIdx + 1).getTotalDiceScore()) {
            if(playerList.get(winnerIdx + 1).isAlive()) {
                System.out.println("무승부");
                return null;
            }
        }

        return playerList.get(winnerIdx);
    }

    private int getSpecialDiceNumber(List<Player> playerList, int currentIdx) {
        return playerList.get(currentIdx).getSpecialDiceNumber();
    }

    private void applyEachPlayer(List<Player> playerList, int currentIdx) {
        int specialDice = getSpecialDiceNumber(playerList, currentIdx);

        DiceService diceService = new DiceServiceImpl();
        diceService.checkSpecialDicePattern(specialDice, playerList, currentIdx);

//        SpecialDicePattern dicePattern = diceService.checkSpecialDicePattern(specialDice, playerList, currentIdx);

//        System.out.println("pattern: "+ dicePattern.getName() +", value: "+ dicePattern.getValue() +"\n");
//        diceService.postProcessAfterGetPattern(dicePattern, playerList, currentIdx);
    }

    @Override
    public void playDiceGame(List<Player> playerList) {
        for(int i=0; i<playerList.size(); i++) {
//            System.out.println("---------------------------------------------------------------------------------------------------------------");
//            System.out.println("player"+ (i + 1));
//            System.out.println("일반 주사위: "+ playerList.get(i).getGeneralDiceNumber() +", 특수 주사위: "+ playerList.get(i).getSpecialDiceNumber());
            applyEachPlayer(playerList, i);
        }

        for(int i=0; i<playerList.size(); i++) {
            String dieMessage = "";
            if(playerList.get(i).isAlive() == false)
                dieMessage = "죽었음.";

            int general = playerList.get(i).getGeneralDiceNumber();
            int special = playerList.get(i).getSpecialDiceNumber();
            String dice = String.valueOf(general) + "(일반) + "+ String.valueOf(special) + "(특수)";

            String cal = playerList.get(i).getCalculate();
            cal = cal.replace("+", " + ");
            cal = cal.replace("-", " - ");

            System.out.println("플레이어"+ (i + 1) +" "+  dieMessage);
            System.out.println(dice + cal +" -> "+ playerList.get(i).getPatternName() +" | 총점: " + playerList.get(i).getTotalDiceScore() +"\n");
        }
    }

}