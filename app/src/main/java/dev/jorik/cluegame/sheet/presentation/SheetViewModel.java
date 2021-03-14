package dev.jorik.cluegame.sheet.presentation;

import com.hadilq.liveevent.LiveEvent;

import java.util.ArrayList;
import java.util.List;

import dev.jorik.cluegame.application.BaseViewModel;
import dev.jorik.cluegame.sheet.domain.SheetDomain;
import dev.jorik.cluegame.sheet.domain.entity.Cell;
import dev.jorik.cluegame.sheet.domain.entity.Player;
import dev.jorik.cluegame.sheet.domain.entity.Sheet;

public class SheetViewModel extends BaseViewModel {
    private SheetDomain domain;
    private Sheet sheet;
    private LiveEvent<CellUpdate> changedCell = new LiveEvent<>();

    public SheetViewModel(SheetDomain domain) {
        super(domain);
        this.domain = domain;
        sheet = domain.getSheet();
    }

    public Sheet getSheet(){
        return sheet;
    }

    public LiveEvent<CellUpdate> getChangedCell(){
        return changedCell;
    }

    public void setCellValue(int playerIndex, int cellIndex, Cell value){
        if(playerIndex == -1) sheet.getCells()[cellIndex] = value;
        else sheet.getPlayers()[playerIndex].getCells()[cellIndex] = value;
        changedCell.postValue(new CellUpdate(playerIndex, cellIndex, value));
    }

    public void onHide(){
        domain.saveGame(sheet);
    }
}
