package dev.jorik.cluegame.sheet;

import android.util.Log;

import dev.jorik.cluegame.games.domain.Game;
import dev.jorik.cluegame.games.domain.GamesOutport;

public class SheetsDomain implements GamesOutport {

    @Override
    public void createGame(Game game) {
        Log.d("GamesOutport", "game row created");
    }
}
