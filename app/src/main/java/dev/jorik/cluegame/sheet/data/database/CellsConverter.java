package dev.jorik.cluegame.sheet.data.database;

import androidx.room.TypeConverter;

import dev.jorik.cluegame.sheet.domain.entity.Cell;
import dev.jorik.cluegame.sheet.domain.entity.Color;
import dev.jorik.cluegame.sheet.domain.entity.Value;

public class CellsConverter {
    private final String sep = "_";

    @TypeConverter
    public String toData(Cell[] cells){
        StringBuilder builder = new StringBuilder(serialize(cells[0]));
        for (int i=1; i<cells.length; i++) builder.append(sep).append(serialize(cells[i]));
        return builder.toString();
    }

    @TypeConverter
    public Cell[] fromData(String line){
        Cell[] cells = new Cell[19];
        String[] codes = line.split(sep);
        for (int i=0; i<codes.length; i++) cells[i] = deserialize(codes[i]);
        return cells;
    }

    private String serialize(Cell cell){
        int value = cell.getValue().index();
        int color = cell.getColor().index();
        return "" + value + color;
    }

    private Cell deserialize(String code){
        Value value = Value.values()[Character.getNumericValue(code.charAt(0))];
        Color color = Color.values()[Character.getNumericValue(code.charAt(1))];
        return new Cell(value, color);
    }
}
