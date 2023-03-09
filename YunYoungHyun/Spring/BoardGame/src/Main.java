import dice.SpecialDice;
import player.Player;
import player.service.PlayerService;
import player.service.PlayerServiceImpl;
import utility.AutoIncrementGenerator;

import java.util.ArrayList;
import java.util.List;

public class Main {

    final public static int PLAYER_MAX = 3;

    public static void main(String[] args) {
        PlayerService playerService = new PlayerServiceImpl();

        AutoIncrementGenerator.setAutoIncrementEntity("Player");
        List<Player> playerList = new ArrayList<>();

        for(int i=0; i<PLAYER_MAX; i++) {
            Long playerId = AutoIncrementGenerator.getAutoIncrementEntity("Player");
            Player player = new Player(playerId, "Player"+ (i + 1));

            addPlayer(playerList, player);
        }

        playerService.playDiceGame(playerList);

        Player winner = playerService.findWinner(playerList);
        if(winner == null) {
            System.out.println("우승자는 없습니다.");
        } else {
            System.out.println("우승자는 플레이어" + winner.getPlayerId() + " 입니다!");
        }

        SpecialDice sd = new SpecialDice();
    }

    public static void addPlayer(List<Player> playerList, Player player) {
        playerList.add(player);
    }

}