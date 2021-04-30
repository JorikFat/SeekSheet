package dev.jorik.cluegame.games.data.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

import dev.jorik.cluegame.games.domain.Game;
import dev.jorik.cluegame.utils.Wrap;


@Entity(tableName = GamesDao.table)
public class DbGame implements Wrap<Game> {
    @PrimaryKey public long timestamp;
    @TypeConverters(NamesConverter.class) public String[] names;

    public DbGame(long timestamp, String[] names){
        this.timestamp = timestamp;
        this.names = names;
    }

    public DbGame(Game game){
        this.timestamp = game.creatingDate.getTime();
        this.names = game.names;
    }

    @Override
    public Game unwrap() {
        return new Game(new Date(timestamp), names);
    }
}
