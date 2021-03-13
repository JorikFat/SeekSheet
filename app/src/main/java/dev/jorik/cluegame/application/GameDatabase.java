package dev.jorik.cluegame.application;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import dev.jorik.cluegame.games.data.database.DbGame;
import dev.jorik.cluegame.games.data.database.GamesDao;
import dev.jorik.cluegame.sheet.data.database.DbPlayer;
import dev.jorik.cluegame.sheet.data.database.PlayersDao;

@Database(entities = {DbGame.class, DbPlayer.class}, version = 1)
public abstract class GameDatabase extends RoomDatabase{
    public abstract GamesDao gamesDao();
    public abstract PlayersDao playersDao();
}
