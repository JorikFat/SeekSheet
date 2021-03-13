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
        String[] players = new String[game.getPlayersName().length+1];
        for(int i=1; i<game.getPlayersName().length+1; i++){//первое имя null (имя пользователя)
            players[i] = game.getPlayersName()[i-1];
        }
        provider.create(gameTimestamp, players);
    }

    @Override
    public void destroy() {

    }
}
