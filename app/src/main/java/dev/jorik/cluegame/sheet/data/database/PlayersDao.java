package dev.jorik.cluegame.sheet.data.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PlayersDao {
    String table = "Players";
    @Query("SELECT * FROM "+ table +" WHERE game_timestamp = :timestamp")
    List<DbPlayer> readGamePlayers(long timestamp);
    @Insert void create(DbPlayer game);
    @Update void update(DbPlayer game);
}
