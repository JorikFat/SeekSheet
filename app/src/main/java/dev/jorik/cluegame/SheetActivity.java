package dev.jorik.cluegame;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

import java.util.ArrayList;
import java.util.List;

import dev.jorik.cluegame.entity.Cell;
import dev.jorik.cluegame.entity.PlayerCells;
import dev.jorik.cluegame.entity.SheetCells;
import dev.jorik.cluegame.entity.Value;
import dev.jorik.cluegame.modals.ConfirmDialog;
import dev.jorik.cluegame.modals.NamesDialog;
import dev.jorik.cluegame.modals.SelectionPopup;

import static android.graphics.Color.TRANSPARENT;
import static dev.jorik.cluegame.utils.View.getIntColor;

public class SheetActivity extends AppCompatActivity {
    private SelectionPopup selecting;
    private Config config;
    private SheetCells sheetCells;
    private ArrayList<TextView> playersName = new ArrayList<>();
    private List<TableRow> sheetRows = new ArrayList<>();

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        playersName.add(findViewById(R.id.tv_sheet_player1name));
        playersName.add(findViewById(R.id.tv_sheet_player2name));
        playersName.add(findViewById(R.id.tv_sheet_player3name));
        playersName.add(findViewById(R.id.tv_sheet_player4name));
        playersName.add(findViewById(R.id.tv_sheet_player5name));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet);
        config = new Config(PreferenceManager.getDefaultSharedPreferences(this));
        selecting = new SelectionPopup(this, (cellIcon, cell) -> {
            setCellIcon(cellIcon, cell);

            ViewParent parentView = cellIcon.getParent();
            if (parentView instanceof TableRow){
                TableRow row = ((TableRow) cellIcon.getParent());
                int rowIndex = sheetRows.indexOf(row);
                sheetCells.setCell(rowIndex, cell);
            } else {
                TableRow row = ((TableRow) cellIcon.getParent().getParent());
                int rowIndex = sheetRows.indexOf(row);
                int columnIndex = ((LinearLayout)row.getChildAt(2)).indexOfChild(cellIcon);
                sheetCells.getPlayers().get(columnIndex).setCell(rowIndex, cell);
            }
        });

        if (config.isNewGame()) initPlayers();
        sheetCells = config.getSheet();

        initPlayRows();
        setPlayersName();
        setTableIcons();
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
                sheetCells = new SheetCells();
                setPlayersName();
                config.setNewGame(true);
                initPlayers();
                recreate();
            }).show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStop() {
        config.saveSheet(sheetCells);
        super.onStop();
    }

    public void onCellClick(View view){
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

    private void setTableIcons(){
        for(int r=0; r<sheetRows.size(); r++){
            TableRow row = sheetRows.get(r);

            ImageView cellView = (ImageView) row.getChildAt(1);
            Cell cell = sheetCells.getCells()[r];
            setCellIcon(cellView, cell);

            for(int c=0; c<sheetCells.getPlayers().size(); c++){
                LinearLayout playersCell = (LinearLayout) row.getChildAt(2);
                ImageView playerCellView = (ImageView) playersCell.getChildAt(c);
                Cell playerCell = sheetCells.getPlayers().get(c).getCells()[r];
                setCellIcon(playerCellView, playerCell);
            }

            int playersCount = sheetCells.getPlayers().size();
            if(0 < playersCount && playersCount < 5) removeCells();
        }
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

    private void setPlayersName(){
        for (int i = 0; i < sheetCells.getPlayers().size(); i++) {
            playersName.get(i).setText(sheetCells.getPlayers().get(i).getPlayerName());
        }
    }

    private void initPlayers(){
        new NamesDialog(this, (names, keepCells) -> {
            for (String name : names) {
                sheetCells.getPlayers().add(new PlayerCells(getInitials(name)));
            }
            config.setNewGame(false);
            setPlayersName();
            if(!keepCells) removeCells();
        }).show();
    }

    private String getInitials(String fullName){
        StringBuilder builder = new StringBuilder();
        for (String part : fullName.split(" "))
            builder.append(part.substring(0,1));
        return builder.toString();
    }

    private void removeCells(){
        LinearLayout playersName = findViewById(R.id.LL_sheet_playersName);
        int removeCount = playersName.getChildCount() - sheetCells.getPlayers().size();
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