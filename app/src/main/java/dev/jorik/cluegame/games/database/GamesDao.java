package dev.jorik.cluegame.games.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import dev.jorik.cluegame.games.database.DbGame;

@Dao
public interface GamesDao {
    @Query("SELECT * FROM " + DbGame.dbName) List<DbGame> readAll();
    @Insert void create(DbGame game);
    @Update void update(DbGame game);
    @Delete void delete(DbGame game);
}
