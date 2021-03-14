package dev.jorik.cluegame.core;

import androidx.room.RoomDatabase;

import dev.jorik.cluegame.games.data.database.DbGame;
import dev.jorik.cluegame.games.data.database.GamesDao;
import dev.jorik.cluegame.sheet.data.database.DbPlayer;
import dev.jorik.cluegame.sheet.data.database.PlayersDao;

@androidx.room.Database(entities = {DbGame.class, DbPlayer.class}, version = 1)
public abstract class Database extends RoomDatabase{
    public abstract GamesDao gamesDao();
    public abstract PlayersDao playersDao();
}
