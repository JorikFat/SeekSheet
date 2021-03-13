package dev.jorik.cluegame.games.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import dev.jorik.cluegame.games.domain.GamesDomain;

public class ViewModelFactory implements ViewModelProvider.Factory{
    private GamesDomain domain;

    public ViewModelFactory(GamesDomain domain) {
        this.domain = domain;
    }

    @NonNull @Override @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new GameListViewModel(domain);
    }
}
