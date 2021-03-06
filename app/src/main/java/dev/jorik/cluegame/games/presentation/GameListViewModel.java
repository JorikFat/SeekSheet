package dev.jorik.cluegame.games.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import dev.jorik.cluegame.application.BaseViewModel;
import dev.jorik.cluegame.games.domain.Game;
import dev.jorik.cluegame.games.domain.GamesDomain;

import static dev.jorik.cluegame.utils.Lang.filter;

public class GameListViewModel extends BaseViewModel {
    private final GamesDomain domain;
    private final List<Game> games;
    private final MutableLiveData<List<Game>> gamesProvider = new MutableLiveData<>();

    public GameListViewModel(GamesDomain domain) {
        super(domain);
        this.domain = domain;
        games = domain.getGames();
        gamesProvider.postValue(games);
    }

    public LiveData<List<Game>> getGamesProvider(){
        return gamesProvider;
    }

    public void createGame(String[] names, boolean keepCells){
        String[] gamePlayers = keepCells ? names : filter(Arrays.asList(names), name -> !name.isEmpty()).toArray(new String[0]);
        Game newGame = new Game(new Date(), gamePlayers);
        domain.createGame(newGame);//todo распаралелить
        games.add(newGame);
        gamesProvider.postValue(games);
    }

    public void selectGame(Game game){
        domain.selectGame(game);
    }

    public void onDeleteClick(Game game){
        games.remove(game);
        gamesProvider.postValue(games);
        domain.deleteGame(game);
    }
}
