package dev.jorik.cluegame.gameslist;

import java.util.Date;

public class Game {
    public Date creatingDate;
    public String[] names;

    public Game(Date creatingDate, String[] names) {
        this.creatingDate = creatingDate;
        this.names = names;
        this.getClass().getSimpleName();

    }

    public String[] getPlayersName(){
        return names;
    }

    public static class Null extends Game{
        public Null() {
            super(null, null);
        }
    }
}
