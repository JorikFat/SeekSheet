package dev.jorik.cluegame.application;

import android.app.Application;

import androidx.room.Room;

import dev.jorik.cluegame.application.GameDatabase;
import dev.jorik.cluegame.sheet.data.DatabasePlayersProvider;
import dev.jorik.cluegame.sheet.data.database.PlayersDao;
import dev.jorik.cluegame.sheet.domain.SheetDomain;

public class App extends Application {
    private GameDatabase database;
    private SheetDomain sheetDomain;

    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(getApplicationContext(), GameDatabase.class, "database")
                .allowMainThreadQueries()//todo убрать
                .build();
    }

    public SheetDomain getSheetDomain(){
        if(sheetDomain == null){
            PlayersDao playersDao = database.playersDao();
            DatabasePlayersProvider provider = new DatabasePlayersProvider(playersDao);
            sheetDomain = new SheetDomain(provider);
        }
        return sheetDomain;
    }

    public GameDatabase getDatabase(){
        return database;
    }
}
