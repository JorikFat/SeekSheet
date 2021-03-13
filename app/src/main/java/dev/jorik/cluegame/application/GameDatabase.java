package dev.jorik.cluegame.application;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import dev.jorik.cluegame.games.data.database.DbGame;
import dev.jorik.cluegame.games.data.database.GamesDao;

@Database(entities = {DbGame.class}, version = 1)
public abstract class GameDatabase extends RoomDatabase{
    public abstract GamesDao gamesDao();
}
