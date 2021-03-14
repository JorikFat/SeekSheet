package dev.jorik.cluegame.sheet.presentation;

import dev.jorik.cluegame.sheet.domain.entity.Cell;

public class CellUpdate {
    private int playerIndex;
    private int cellIndex;
    private Cell value;

    public CellUpdate(int playerIndex, int cellIndex, Cell value) {
        this.playerIndex = playerIndex;
        this.cellIndex = cellIndex;
        this.value = value;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public int getCellIndex() {
        return cellIndex;
    }

    public Cell getValue() {
        return value;
    }
}
