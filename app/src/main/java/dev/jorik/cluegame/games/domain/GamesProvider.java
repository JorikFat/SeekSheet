package dev.jorik.cluegame.games.domain;

import java.util.List;

public interface GamesProvider {
    List<Game> getGames();
    void createGame(Game game);
}
