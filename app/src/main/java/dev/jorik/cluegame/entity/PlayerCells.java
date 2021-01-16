package dev.jorik.cluegame.entity;

public class PlayerCells {
    private String playerName;
    private Cell[] cells;

    public PlayerCells(){
        cells = newColumn();
    }

    public PlayerCells(String playerName, Cell[] cells) {
        this.playerName = playerName;
        this.cells = cells;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Cell[] getCells() {
        return cells;
    }

    public void setCells(Cell[] cells) {
        this.cells = cells;
    }

    public void setCell(int index, Cell cell){
        this.cells[index] = cell;
    }

    private Cell[] newColumn(){//todo генерализировать
        Cell[] cells = new Cell[19];
        for (int i=0; i<19; i++) cells[i] = new Cell();
        return cells;
    }
}
