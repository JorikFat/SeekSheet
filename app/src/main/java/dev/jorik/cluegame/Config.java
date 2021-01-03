package dev.jorik.cluegame;

import android.content.SharedPreferences;

import dev.jorik.cluegame.entity.PlayerState;
import dev.jorik.cluegame.entity.SheetState;

public class Config {
    private static final String PLAYER_STATE = "player_state";
    private static final String PLAYER_NAME_PATTERN = "player%d";
    private static final String PLAYER_STATE_PATTERN = "player_state%d";
    private static final String NEW_GAME = "new_game";
    private SharedPreferences preferences;

    public Config(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public void saveSheet(SheetState sheetState){
        SharedPreferences.Editor editor = preferences.edit()
                .putString(PLAYER_STATE, serializeArray(sheetState.getState()));

        for (int i=0; i<5; i++) putPlayer(editor, i, sheetState.getPlayers()[i]);
        editor.apply();
    }

    public SheetState getSheet(){
        int[] state = deserializeArray(preferences.getString(PLAYER_STATE, ""));
        PlayerState[] playerStates = new PlayerState[5];
        for (int i=0; i<5; i++) playerStates[i] = getPlayer(i);
        return new SheetState(state, playerStates);
    }

    public boolean isNewGame(){
        return preferences.getBoolean(NEW_GAME, true);
    }

    public void setNewGame(boolean newGame){
        preferences.edit().putBoolean(NEW_GAME, newGame).apply();
    }

    private SharedPreferences.Editor putPlayer(SharedPreferences.Editor editor, int index, PlayerState playerState){
        return editor.putString(String.format(PLAYER_NAME_PATTERN, index), playerState.getPlayerName())
                .putString(String.format(PLAYER_STATE_PATTERN, index), serializeArray(playerState.getState()));
    }

    private PlayerState getPlayer(int index){
        String name = preferences.getString(String.format(PLAYER_NAME_PATTERN, index), "");
        int[] state = deserializeArray(preferences.getString(String.format(PLAYER_STATE_PATTERN, index), ""));
        return new PlayerState(name, state);
    }

    private String serializeArray(int[] state){
        StringBuilder builder = new StringBuilder();
        for (int i : state) builder.append(i);
        return builder.toString();
    }

    private int[] deserializeArray(String serial){
        int[] state = new int[19];
        for (int i = 0; i < serial.length(); i++) {
            state[i] = Character.getNumericValue(serial.charAt(i));
        }
        return state;
    }
}
