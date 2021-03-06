package dev.jorik.cluegame.sheet.domain.entity;

public class Cell {
    private Value value = Value.EMPTY;
    private Color color = Color.BLACK;

    public Cell(){}

    public Cell(Value value, Color color) {
        this.value = value;
        this.color = color;
    }

    public Cell(int valueIndex, int colorIndex){
        this.value = Value.values()[valueIndex];
        this.color = Color.values()[colorIndex];
    }

    public Value getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public static Cell[] emptyCells(int count){
        Cell[] cells = new Cell[count];
        for (int i = 0; i < count; i++) cells[i] = new Cell();
        return cells;
    }
}
