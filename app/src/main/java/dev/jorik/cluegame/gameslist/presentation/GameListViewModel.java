package dev.jorik.cluegame.gameslist.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dev.jorik.cluegame.gameslist.Game;
import dev.jorik.cluegame.gameslist.data.DbGame;
import dev.jorik.cluegame.gameslist.data.GamesDao;
import dev.jorik.cluegame.utils.Lang;

import static dev.jorik.cluegame.utils.Lang.filter;
import static dev.jorik.cluegame.utils.Lang.map;

public class GameListViewModel extends ViewModel {
    private GamesDao dao;
    private List<Game> games;
    private MutableLiveData<List<Game>> gamesProvider = new MutableLiveData<>();

    public GameListViewModel(GamesDao dao){
        this.dao = dao;
        games = map(dao.readAll(), DbGame::unwrap);
        gamesProvider.postValue(games);
    }

    public LiveData<List<Game>> getGamesProvider(){
        return gamesProvider;
    }

    public void createGame(String[] names, boolean keepCells){
        String[] gamePlayers = keepCells ? names : filter(names, name -> !name.isEmpty());
        Game newGame = new Game(new Date(), gamePlayers);
        dao.create(new DbGame(newGame));//todo распаралелить
        games.add(newGame);
        gamesProvider.postValue(games);
    }
}
