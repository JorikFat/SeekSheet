package dev.jorik.cluegame.gameslist.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {DbGame.class}, version = 1)
public abstract class GameDatabase extends RoomDatabase{
    public abstract GamesDao gamesDao();
}
