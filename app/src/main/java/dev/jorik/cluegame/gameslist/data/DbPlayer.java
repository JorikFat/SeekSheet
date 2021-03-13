package dev.jorik.cluegame.gameslist.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Player")
public class DbPlayer {
    @PrimaryKey
    private long id;
    private String name;
    private String cells;
}
