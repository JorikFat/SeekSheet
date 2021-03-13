package dev.jorik.cluegame.sheet.presentation;

import androidx.lifecycle.MutableLiveData;

import dev.jorik.cluegame.application.BaseViewModel;
import dev.jorik.cluegame.sheet.domain.SheetDomain;
import dev.jorik.cluegame.sheet.domain.entity.Cell;
import dev.jorik.cluegame.sheet.domain.entity.Player;

public class SheetViewModel extends BaseViewModel {
    private SheetDomain domain;
    private MutableLiveData<Cell[]> userCells = new MutableLiveData<>();
    private MutableLiveData<Player[]> players = new MutableLiveData<>();

    public SheetViewModel(SheetDomain domain) {
        super(domain);
        this.domain = domain;
    }

    public MutableLiveData<Cell[]> getUserCells() {
        return userCells;
    }

    public MutableLiveData<Player[]> getPlayers() {
        return players;
    }
}
