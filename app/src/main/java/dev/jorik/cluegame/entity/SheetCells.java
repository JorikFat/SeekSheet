package dev.jorik.cluegame.entity;

public class SheetCells {
    private Cell[] cells;
    private PlayerCells[] players;

    public SheetCells(){
        this.cells = newColumn();
        players = new PlayerCells[]{
            new PlayerCells(),
            new PlayerCells(),
            new PlayerCells(),
            new PlayerCells(),
            new PlayerCells()
        };
    }

    public SheetCells(Cell[] cells, PlayerCells[] players) {
        this.cells = cells;
        this.players = players;
    }

    public Cell[] getCells() {
        return cells;
    }

    public void setCells(Cell[] cells) {
        this.cells = cells;
    }

    public PlayerCells[] getPlayers() {
        return players;
    }

    public void setPlayers(PlayerCells[] players) {
        this.players = players;
    }

    public void setCell(int index, Cell cell){
        this.cells[index] = cell;
    }

    private Cell[] newColumn(){ //todo генерализировать
        Cell[] cells = new Cell[19];
        for (int i=0; i<19; i++) cells[i] = new Cell();
        return cells;
    }
}
