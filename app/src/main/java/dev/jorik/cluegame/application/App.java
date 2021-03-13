package dev.jorik.cluegame.application;

import android.app.Application;

import androidx.room.Room;

import dev.jorik.cluegame.application.GameDatabase;

public class App extends Application {
    private GameDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(getApplicationContext(), GameDatabase.class, "database")
                .allowMainThreadQueries()//todo убрать
                .build();
    }

    public GameDatabase getDatabase(){
        return database;
    }
}
