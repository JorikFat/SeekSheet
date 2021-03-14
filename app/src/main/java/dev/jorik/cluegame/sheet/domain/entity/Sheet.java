package dev.jorik.cluegame.sheet.domain.entity;

public class Sheet {
    private long id;
    private Cell[] cells;
    private Player[] players;

    public Sheet(long id, Cell[] cells, Player[] players) {
        this.id = id;
        this.cells = cells;
        this.players = players;
    }

    public Cell[] getCells() {
        return cells;
    }

    public Player[] getPlayers() {
        return players;
    }

    public long getId(){
        return id;
    }
}
