package dev.jorik.cluegame.sheet.domain;

import dev.jorik.cluegame.application.DestroyUseCase;
import dev.jorik.cluegame.games.domain.Game;
import dev.jorik.cluegame.games.domain.GamesOutport;
import dev.jorik.cluegame.sheet.domain.entity.Player;

public class SheetDomain implements GamesOutport, DestroyUseCase {
    private PlayersProvider provider;
    private long gameTimestamp;
    private Player[] players;

    public SheetDomain(long gameTimestamp, PlayersProvider provider){
        this.gameTimestamp = gameTimestamp;
        this.provider = provider;
    }

    public SheetDomain(PlayersProvider provider){
        this.provider = provider;
    }

    public void setGameTimestamp(long gameTimestamp){
        this.gameTimestamp = gameTimestamp;
    }

    public Player[] getPlayers(){
        players = provider.read(gameTimestamp).toArray(new Player[0]);
        return players;
    }

    @Override
    public void createGame(Game game) {
        gameTimestamp = game.getDate().getTime();
        provider.create(gameTimestamp, game.getPlayersName());
    }

    @Override
    public void destroy() {

    }
}
