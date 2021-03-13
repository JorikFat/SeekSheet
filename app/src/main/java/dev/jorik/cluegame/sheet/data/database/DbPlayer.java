package dev.jorik.cluegame.sheet.data.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import dev.jorik.cluegame.core.Wrap;
import dev.jorik.cluegame.games.data.database.DbGame;
import dev.jorik.cluegame.sheet.domain.entity.Cell;
import dev.jorik.cluegame.sheet.domain.entity.Player;

@Entity(foreignKeys = @ForeignKey(entity = DbGame.class, parentColumns = "timestamp", childColumns = "game_timestamp"))
public class DbPlayer implements Wrap<Player> {
    @PrimaryKey(autoGenerate = true) public long id;
    @ColumnInfo(name = "game_timestamp") public long gameTime;
    public String name;
    public Cell[] cells;

    public DbPlayer(long id, long gameTime, Cell[] cells, String name) {
        this.id = id;
        this.gameTime = gameTime;
        this.cells = cells;
        this.name = name;
    }

    public DbPlayer(Player player){
        id = player.getId();
        name = player.getName();
        cells = player.getCells();
    }

    @Override
    public Player unwrap() {
        return new Player(id, name, cells);
    }
}