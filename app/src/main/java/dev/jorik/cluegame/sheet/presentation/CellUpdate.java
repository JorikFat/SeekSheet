package dev.jorik.cluegame.sheet.presentation;

import dev.jorik.cluegame.sheet.domain.entity.Cell;

public class CellUpdate {
    public final int playerIndex;
    public final int cellIndex;
    public final Cell value;

    public CellUpdate(int playerIndex, int cellIndex, Cell value) {
        this.playerIndex = playerIndex;
        this.cellIndex = cellIndex;
        this.value = value;
    }
}
