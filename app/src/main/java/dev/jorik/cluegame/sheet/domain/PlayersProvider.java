package dev.jorik.cluegame.sheet.domain;

import java.util.List;

import dev.jorik.cluegame.sheet.domain.entity.Player;

public interface PlayersProvider {
    List<Player> read(long timestamp);
    void create(long timestamp, String[] names);
    void update(long timestamp, Player[] players);
}
