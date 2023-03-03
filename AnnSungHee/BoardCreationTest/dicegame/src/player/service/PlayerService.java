package player.service;

import player.Player;

import java.util.List;

public interface PlayerService {
    public void playDiceGame(List<Player> playerList);
    public Player findWinner(List<Player> playerList);
}
