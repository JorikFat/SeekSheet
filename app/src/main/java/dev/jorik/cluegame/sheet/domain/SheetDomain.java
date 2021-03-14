package dev.jorik.cluegame.sheet.domain;

import java.util.List;

import dev.jorik.cluegame.application.DestroyUseCase;
import dev.jorik.cluegame.games.domain.Game;
import dev.jorik.cluegame.games.domain.GamesOutport;
import dev.jorik.cluegame.sheet.domain.entity.Cell;
import dev.jorik.cluegame.sheet.domain.entity.Player;
import dev.jorik.cluegame.sheet.domain.entity.Sheet;
import dev.jorik.cluegame.utils.Lang;

public class SheetDomain implements GamesOutport, DestroyUseCase {
    private PlayersProvider provider;
    private long gameTimestamp;

    public SheetDomain(PlayersProvider provider){
        this.provider = provider;
    }

    public void setTimestamp(long timestamp){
        this.gameTimestamp = timestamp;
    }

    public Sheet getSheet(){
        List<Player> players = provider.read(gameTimestamp);
        Player user = Lang.find(players, p -> p.getName() == null);
        players.remove(user);
        return new Sheet(user.getId(), user.getCells(), players.toArray(new Player[0]));
    }

    public void saveGame(Sheet sheet){
        provider.update(gameTimestamp, getAllPlayers(sheet));
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

    private Player[] getAllPlayers(Sheet sheet){
        Player[] allPlayers = new Player[sheet.getPlayers().length + 1];
        allPlayers[0] = new Player(sheet.getId(), null, sheet.getCells());
        for(int i=1; i<sheet.getPlayers().length+1; i++){
            allPlayers[i] = sheet.getPlayers()[i - 1];
        }
        return allPlayers;
    }
}
