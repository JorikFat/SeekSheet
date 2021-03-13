package dev.jorik.cluegame.games.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import dev.jorik.cluegame.games.database.GamesDao;

public class ViewModelFactory implements ViewModelProvider.Factory{
    private GamesDao dao;

    public ViewModelFactory(GamesDao dao) {
        this.dao = dao;
    }

    @NonNull
    @Override @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new GameListViewModel(dao);
    }
}
