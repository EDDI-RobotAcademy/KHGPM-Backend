import player.Player;
import player.service.PlayerService;
import player.service.PlayerServiceImpl;
import utility.AutoIncrementGenerator;
import utility.CustomRandom;

import java.util.ArrayList;
import java.util.List;

public class Main {

    // 3판 하겠다는 소리
    final public static int PLAYER_MAX = 3;

    public static void main(String[] args) {
        PlayerService playerService = new PlayerServiceImpl();

        //중복 플레이어(도메인) 있는지 확인
        AutoIncrementGenerator.setAutoIncrementEntity("Player");

        //플레이어들 넣을 리스트 생성
        List<Player> playerList = new ArrayList<>();

        for (int i = 0; i < PLAYER_MAX; i++) {
            Player player = new Player(
                    AutoIncrementGenerator.getEntityAutoIncrementValue("Player"));
            addPlayer(playerList, player);
        }
        // 정렬 전
        System.out.println("-> 정렬 전 입니다.");
        System.out.println(playerList);

        playerService.playDiceGame(playerList);
        Player winner = playerService.findWinner(playerList);

        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("-> 승자 판정 입니다.");
        if(winner == null){
            System.out.println("무승부로 우승자는 없습니다.");
        }else{
            System.out.println( winner.playerListWinner());
        }

//        for (int i = 0; i < playerList.size(); i++) {
//            System.out.println("eeeeeeeeeeeeee"+playerList.get(i).getSpecialDiceNumber());
//        }

    }

    public static void addPlayer(List<Player> playerList, Player player) {
        playerList.add(player);
    }
}