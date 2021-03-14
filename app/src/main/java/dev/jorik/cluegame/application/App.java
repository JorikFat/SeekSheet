package dev.jorik.cluegame.application;

import android.app.Application;

import androidx.room.Room;

import dev.jorik.cluegame.core.Database;
import dev.jorik.cluegame.sheet.data.PlayersRepository;
import dev.jorik.cluegame.sheet.data.database.PlayersDao;
import dev.jorik.cluegame.sheet.domain.SheetDomain;

public class App extends Application {
    private Database database;
    private SheetDomain sheetDomain;

    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(getApplicationContext(), Database.class, "database")
                .allowMainThreadQueries()//todo убрать
                .build();
    }

    public SheetDomain getSheetDomain(){
        if(sheetDomain == null){
            PlayersDao playersDao = database.playersDao();
            PlayersRepository provider = new PlayersRepository(playersDao);
            sheetDomain = new SheetDomain(provider);
        }
        return sheetDomain;
    }

    public Database getDatabase(){
        return database;
    }
}
