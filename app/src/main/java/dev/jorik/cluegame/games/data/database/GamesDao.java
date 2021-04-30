package dev.jorik.cluegame.games.data.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GamesDao {
    String table = "Game";
    @Query("SELECT * FROM " + table) List<DbGame> readAll();
    @Insert void create(DbGame game);
    @Delete void delete(DbGame game);
}
