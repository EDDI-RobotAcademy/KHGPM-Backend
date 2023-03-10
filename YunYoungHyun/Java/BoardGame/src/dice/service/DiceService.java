package dice.service;

import player.Player;

import java.util.List;

public interface DiceService {

    public void checkSpecialDicePattern(int specialDice, List<Player> playerList, int currentIdx);
    void stealEachPlayerScore(List<Player> playerList, int currentIdx);
    void donateToEachPlayer(List<Player> playerList, int currentIdx);
    void everyoneLossScore(List<Player> playerList);
    void killPlayer(List<Player> playerList, int currentIdx);

}