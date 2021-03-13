package dev.jorik.cluegame;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import dev.jorik.cluegame.sheet.domain.entity.Cell;
import dev.jorik.cluegame.sheet.domain.entity.Color;
import dev.jorik.cluegame.sheet.domain.entity.PlayerCells;
import dev.jorik.cluegame.sheet.domain.entity.SheetCells;
import dev.jorik.cluegame.sheet.domain.entity.Value;

public class Config {
    private static final String PLAYER_CELLS = "player_cells";
    private static final String PLAYER_NAME_PATTERN = "player%d";
    private static final String PLAYER_CELLS_PATTERN = "player_cells%d";
    private static final String NEW_GAME = "new_game";
    private SharedPreferences preferences;

    public Config(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public void saveSheet(SheetCells sheetCells){
        SharedPreferences.Editor editor = preferences.edit()
                .putString(PLAYER_CELLS, serializeArray(sheetCells.getCells()));

        for (int i=0; i<sheetCells.getPlayers().size(); i++) putPlayer(editor, i, sheetCells.getPlayers().get(i));
        editor.apply();
    }

    public SheetCells getSheet(){
        String codedCells = preferences.getString(PLAYER_CELLS, "");
        Cell[] cells = codedCells.isEmpty() ? newColumn() : deserializeArray(codedCells.split("_"));
        List<PlayerCells> playerCells = new ArrayList<>();
        for (int i=0; i<getPlayersCount(); i++) playerCells.add(getPlayer(i));
        return new SheetCells(cells, playerCells);
    }

    public boolean isNewGame(){
        return preferences.getBoolean(NEW_GAME, true);
    }

    public void setNewGame(boolean newGame){
        preferences.edit().putBoolean(NEW_GAME, newGame).apply();
    }

    private SharedPreferences.Editor putPlayer(SharedPreferences.Editor editor, int index, PlayerCells playerCells){
        return editor.putString(String.format(PLAYER_NAME_PATTERN, index), playerCells.getPlayerName())
                .putString(String.format(PLAYER_CELLS_PATTERN, index), serializeArray(playerCells.getCells()));
    }

    private PlayerCells getPlayer(int index){
        String name = preferences.getString(String.format(PLAYER_NAME_PATTERN, index), "");
        String codedCells = preferences.getString(String.format(PLAYER_CELLS_PATTERN, index), "");
        Cell[] cells = codedCells.isEmpty() ? newColumn() : deserializeArray(codedCells.split("_"));
        return new PlayerCells(name, cells);
    }

    private String serializeArray(Cell[] cells){
        StringBuilder builder = new StringBuilder(serialize(cells[0]));
        for (int i=1; i<cells.length; i++) builder.append('_').append(serialize(cells[i]));
        return builder.toString();
    }

    private Cell[] deserializeArray(String[] serial){
        Cell[] cells = new Cell[19];
        for (int i=0; i<serial.length; i++){
            cells[i] = deserialize(serial[i]);
        }
        return cells;
    }

    private String serialize(Cell cell){
        int value = cell.getValue().index();
        int color = cell.getColor().index();
        return String.valueOf(value) + String.valueOf(color);
    }

    private Cell deserialize(String code){
        Value value = Value.values()[Character.getNumericValue(code.charAt(0))];
        Color color = Color.values()[Character.getNumericValue(code.charAt(1))];
        return new Cell(value, color);
    }

    private Cell[] newColumn(){ //todo генерализировать
        Cell[] cells = new Cell[19];
        for (int i=0; i<19; i++) cells[i] = new Cell();
        return cells;
    }

    private int getPlayersCount(){
        for(int i=0; i<5; i++){
            if (preferences.getString(String.format(PLAYER_NAME_PATTERN, i), null) == null){
                return i;
            }
        }
        return 5;
    }
}
