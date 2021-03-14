package dev.jorik.cluegame.sheet.view;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ImageViewCompat;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

import dev.jorik.cluegame.R;
import dev.jorik.cluegame.application.App;
import dev.jorik.cluegame.application.modals.ConfirmDialog;
import dev.jorik.cluegame.application.modals.SelectionPopup;
import dev.jorik.cluegame.sheet.domain.entity.Cell;
import dev.jorik.cluegame.sheet.domain.entity.Player;
import dev.jorik.cluegame.sheet.domain.entity.Sheet;
import dev.jorik.cluegame.sheet.domain.entity.Value;
import dev.jorik.cluegame.sheet.presentation.SheetModelFactory;
import dev.jorik.cluegame.sheet.presentation.SheetViewModel;

import static android.graphics.Color.TRANSPARENT;
import static dev.jorik.cluegame.utils.View.getIntColor;

public class SheetActivity extends AppCompatActivity {
    private SheetViewModel viewModel;
    private SelectionPopup selecting;
    private final List<TextView> playersName = new ArrayList<>();
    private final List<TableRow> sheetRows = new ArrayList<>();

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        playersName.add(findViewById(R.id.tv_sheet_player1name));
        playersName.add(findViewById(R.id.tv_sheet_player2name));
        playersName.add(findViewById(R.id.tv_sheet_player3name));
        playersName.add(findViewById(R.id.tv_sheet_player4name));
        playersName.add(findViewById(R.id.tv_sheet_player5name));
        initPlayRows();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet);
        SheetModelFactory factory = new SheetModelFactory(((App) getApplication()).getSheetDomain());
        viewModel = new ViewModelProvider(this, factory).get(SheetViewModel.class);
        selecting = new SelectionPopup(this, (cellIcon, cell) -> {
            ViewParent parentView = cellIcon.getParent();
            if (parentView instanceof TableRow){//ячейка пользователя
                TableRow row = ((TableRow) cellIcon.getParent());
                int rowIndex = sheetRows.indexOf(row);
                viewModel.setCellValue(-1, rowIndex, cell);
            } else {//ячейка опанента
                TableRow row = ((TableRow) cellIcon.getParent().getParent());
                int rowIndex = sheetRows.indexOf(row);
                int columnIndex = ((LinearLayout)row.getChildAt(2)).indexOfChild(cellIcon);
                viewModel.setCellValue(columnIndex, rowIndex, cell);
            }
        });
        viewModel.getChangedCell().observe(this, cellUpdate -> {
            TableRow tableRow = sheetRows.get(cellUpdate.cellIndex);
            ImageView cell;
            if(cellUpdate.playerIndex == -1){
                cell = ((ImageView) tableRow.getChildAt(1));
            } else {
                LinearLayout playerCells = (LinearLayout) tableRow.getChildAt(2);
                cell = (ImageView) playerCells.getChildAt(cellUpdate.playerIndex);
            }
            setCellIcon(cell, cellUpdate.value);
        });
        fillPlayersName(viewModel.getSheet().getPlayers());
        fillIcons(viewModel.getSheet());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.newgame, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.omi_newGame_clearSheet){
            new ConfirmDialog(this, R.string.sure, R.string.sheet_clearTableWarning, () -> {
                viewModel.clearSheet();
                fillIcons(viewModel.getSheet());
            }).show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStop() {
        viewModel.onHide();
        super.onStop();
    }

    public void onCellClick(View view){//this method set on layout cells
        ImageView cell = ((ImageView) view);
        selecting.show(cell);
    }

    private void setCellIcon(ImageView cellIcon, Cell cell){
        int iconId = 0;
        switch (cell.getValue()){
            case EMPTY: iconId = R.drawable.ic_empty; break;
            case CHECK: iconId = R.drawable.ic_check; break;
            case CROSS: iconId = R.drawable.ic_cross; break;
            case EXCLAMATION: iconId = R.drawable.ic_exclamation; break;
            case QUESTION: iconId = R.drawable.ic_question; break;
        }
        cellIcon.setImageResource(iconId);
        ImageViewCompat.setImageTintList(cellIcon, ColorStateList.valueOf(cell.getValue() == Value.EMPTY ?
                        TRANSPARENT :
                        getIntColor(this, cell.getColor())
                )
        );
    }

    private void initPlayRows(){
        sheetRows.add(findViewById(R.id.TR_sheet_personGreen));
        sheetRows.add(findViewById(R.id.TR_sheet_personMustard));
        sheetRows.add(findViewById(R.id.TR_sheet_personPeacock));
        sheetRows.add(findViewById(R.id.TR_sheet_personPlum));
        sheetRows.add(findViewById(R.id.TR_sheet_personScarlett));

        sheetRows.add(findViewById(R.id.TR_sheet_toolCandleStick));
        sheetRows.add(findViewById(R.id.TR_sheet_toolDagger));
        sheetRows.add(findViewById(R.id.TR_sheet_toolRevolver));
        sheetRows.add(findViewById(R.id.TR_sheet_toolRope));
        sheetRows.add(findViewById(R.id.TR_sheet_toolWrench));

        sheetRows.add(findViewById(R.id.TR_sheet_placeBallroom));
        sheetRows.add(findViewById(R.id.TR_sheet_placeBilliard));
        sheetRows.add(findViewById(R.id.TR_sheet_placeWinterGarder));
        sheetRows.add(findViewById(R.id.TR_sheet_placeDining));
        sheetRows.add(findViewById(R.id.TR_sheet_placeBath));
        sheetRows.add(findViewById(R.id.TR_sheet_placeKitchen));
        sheetRows.add(findViewById(R.id.TR_sheet_placeLibrary));
        sheetRows.add(findViewById(R.id.TR_sheet_placeLivingroom));
        sheetRows.add(findViewById(R.id.TR_sheet_placeCabinet));
    }

    private void fillPlayersName(Player[] players){
        for (int i = 0; i < players.length; i++) {
            if (players[i].getName().isEmpty()){
                playersName.get(i).setText(players[i].getName());
                continue;
            }
            StringBuilder builder = new StringBuilder();
            for (String part : players[i].getName().split(" ")) builder.append(part.substring(0,1));
            playersName.get(i).setText(builder.toString());
        }
    }

    private void fillIcons(Sheet sheet){
        for(int r=0; r<sheetRows.size(); r++){
            TableRow row = sheetRows.get(r);

            ImageView cellView = (ImageView) row.getChildAt(1);
            Cell cell = sheet.getCells()[r];
            setCellIcon(cellView, cell);

            for(int c=0; c<sheet.getPlayers().length; c++){
                LinearLayout playersCell = (LinearLayout) row.getChildAt(2);
                ImageView playerCellView = (ImageView) playersCell.getChildAt(c);
                Cell playerCell = sheet.getPlayers()[c].getCells()[r];
                setCellIcon(playerCellView, playerCell);
            }

            int playersCount = sheet.getPlayers().length;
            if(0 < playersCount && playersCount < 5) removeCells(sheet);
        }
    }

    private void removeCells(Sheet sheet){
        LinearLayout playersName = findViewById(R.id.LL_sheet_playersName);
        int removeCount = playersName.getChildCount() - sheet.getPlayers().length;
        removeCells(playersName, removeCount);
        for(int r=0; r<sheetRows.size(); r++){
            LinearLayout cellsRow = (LinearLayout) sheetRows.get(r).getChildAt(2);
            removeCells(cellsRow, removeCount);
        }
    }

    private void removeCells(ViewGroup viewParent, int removeCount){
        for(int i=0; i<removeCount; i++){
            viewParent.removeViewAt(viewParent.getChildCount()-1);
        }
    }
}