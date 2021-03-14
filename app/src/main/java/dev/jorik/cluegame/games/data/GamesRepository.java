package dev.jorik.cluegame.games.data;

import java.util.List;

import dev.jorik.cluegame.games.data.database.DbGame;
import dev.jorik.cluegame.games.data.database.GamesDao;
import dev.jorik.cluegame.games.domain.Game;
import dev.jorik.cluegame.games.domain.GamesProvider;

import static dev.jorik.cluegame.utils.Lang.map;

public class GamesRepository implements GamesProvider {
    private final GamesDao dao;

    public GamesRepository(GamesDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Game> getGames() {
        return map(dao.readAll(), DbGame::unwrap);
    }

    @Override
    public void createGame(Game game) {
        dao.create(new DbGame(game));
    }
}
