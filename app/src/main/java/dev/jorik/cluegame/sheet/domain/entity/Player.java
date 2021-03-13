package dev.jorik.cluegame.sheet.domain.entity;

public class Player {
    private long id;
    private String name;
    private Cell[] cells;

    public Player(long id, String name, Cell[] cells) {
        this.id = id;
        this.name = name;
        this.cells = cells;
    }

    public Player(String name){
        this(0, name, emptyCells(19));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cell[] getCells() {
        return cells;
    }

    public void setCells(Cell[] cells) {
        this.cells = cells;
    }

    public long getId() {
        return id;
    }

    private static Cell[] emptyCells(int count){
        Cell[] cells = new Cell[count];
        for (int i = 0; i < count; i++) cells[i] = new Cell();
        return cells;
    }
}
