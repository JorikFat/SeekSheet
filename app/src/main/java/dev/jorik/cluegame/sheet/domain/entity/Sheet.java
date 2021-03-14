package dev.jorik.cluegame.sheet.domain.entity;

public class Sheet {
    private final long id;
    private Cell[] cells;
    private final Player[] players;

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

    public void clear(){
        cells = Cell.emptyCells(19);
        for(Player player :players){
            player.setCells(Cell.emptyCells(19));
        }
    }
}
