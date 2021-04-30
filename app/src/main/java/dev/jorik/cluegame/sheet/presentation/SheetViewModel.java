package dev.jorik.cluegame.sheet.presentation;

import androidx.lifecycle.ViewModel;

import com.hadilq.liveevent.LiveEvent;

import dev.jorik.cluegame.sheet.domain.SheetDomain;
import dev.jorik.cluegame.sheet.domain.entity.Cell;
import dev.jorik.cluegame.sheet.domain.entity.Sheet;

public class SheetViewModel extends ViewModel {
    private final SheetDomain domain;
    private final Sheet sheet;
    private final LiveEvent<CellUpdate> changedCell = new LiveEvent<>();

    public SheetViewModel(SheetDomain domain) {
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

    public void clearSheet(){
        sheet.clear();
        domain.saveGame(sheet);
    }
}
