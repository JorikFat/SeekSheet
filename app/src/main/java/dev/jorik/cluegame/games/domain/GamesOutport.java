package dev.jorik.cluegame.games.domain;

public interface GamesOutport {
    void selectGame(Game game);
    void createGame(Game game);
}
