package dev.jorik.cluegame.entity;

import java.util.ArrayList;
import java.util.List;

public class SheetCells {
    private Cell[] cells;
    private List<PlayerCells> players;

    public SheetCells(){
        this.cells = newColumn();
        players = new ArrayList<>();
        players.add(new PlayerCells());
        players.add(new PlayerCells());
        players.add(new PlayerCells());
        players.add(new PlayerCells());
        players.add(new PlayerCells());
    }

    public SheetCells(Cell[] cells, List<PlayerCells> players) {
        this.cells = cells;
        this.players = players;
    }

    public Cell[] getCells() {
        return cells;
    }

    public void setCells(Cell[] cells) {
        this.cells = cells;
    }

    public List<PlayerCells> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerCells> players) {
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
