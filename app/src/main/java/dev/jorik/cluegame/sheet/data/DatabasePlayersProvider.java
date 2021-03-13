package dev.jorik.cluegame.sheet.data;

import java.util.List;

import dev.jorik.cluegame.sheet.data.database.DbPlayer;
import dev.jorik.cluegame.sheet.data.database.PlayersDao;
import dev.jorik.cluegame.sheet.domain.PlayersProvider;
import dev.jorik.cluegame.sheet.domain.entity.Player;
import dev.jorik.cluegame.utils.Lang;

public class DatabasePlayersProvider implements PlayersProvider {
    private PlayersDao dao;

    public DatabasePlayersProvider(PlayersDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Player> read(long timestamp) {
        return Lang.map(dao.readGamePlayers(timestamp), DbPlayer::unwrap);
    }

    @Override
    public void create(long timestamp, String[] names) {
        for(String name : names) dao.create(new DbPlayer(timestamp, new Player(name)));
    }

    @Override
    public void update(long timestamp, Player[] players) {
        for(Player player :players){
            DbPlayer dbPlayer = new DbPlayer(player.getId(), timestamp, player.getCells(), player.getName());
            dao.update(dbPlayer);
        }
    }
}
