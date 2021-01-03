package dev.jorik.cluegame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dev.jorik.cluegame.entity.SheetState;
import dev.jorik.cluegame.utils.Platform;

public class SheetActivity extends AppCompatActivity {
    private Config config;
    private SheetState sheetState;
    private EditText player1name;
    private EditText player2name;
    private EditText player3name;
    private EditText player4name;
    private EditText player5name;
    private List<TableRow> sheetRows = new ArrayList<>();

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        player1name = findViewById(R.id.et_sheet_player1name);
        player2name = findViewById(R.id.et_sheet_player2name);
        player3name = findViewById(R.id.et_sheet_player3name);
        player4name = findViewById(R.id.et_sheet_player4name);
        player5name = findViewById(R.id.et_sheet_player5name);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet);
        config = new Config(PreferenceManager.getDefaultSharedPreferences(this));
        sheetState = config.getSheet();

        player1name.addTextChangedListener((Platform.TextListener) text -> sheetState.getPlayers()[0].setPlayerName(text.toString()));
        player2name.addTextChangedListener((Platform.TextListener) text -> sheetState.getPlayers()[1].setPlayerName(text.toString()));
        player3name.addTextChangedListener((Platform.TextListener) text -> sheetState.getPlayers()[2].setPlayerName(text.toString()));
        player4name.addTextChangedListener((Platform.TextListener) text -> sheetState.getPlayers()[3].setPlayerName(text.toString()));
        player5name.addTextChangedListener((Platform.TextListener) text -> sheetState.getPlayers()[4].setPlayerName(text.toString()));

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
                sheetState = new SheetState();
                setTableIcons();
                setPlayersName();
            }).show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStop() {
        config.saveSheet(sheetState);
        super.onStop();
    }

    public void onCellClick(View view){
        ImageView cell = ((ImageView) view);
        new SelectIconDialog(SheetActivity.this, value -> {
            setCellIcon(cell, value);

            TableRow row = ((TableRow) cell.getParent());
            int rowIndex = sheetRows.indexOf(row);
            int columnIndex = row.indexOfChild(cell)-1;
            if (columnIndex == 0){//player cell
                sheetState.setItemState(rowIndex, value.index());
            } else {//opponent cell
                sheetState.getPlayers()[columnIndex-1].setItemState(rowIndex, value.index());
            }
        }).show();
    }

    private void setCellIcon(ImageView cell, CellValue value){
        int iconId = 0;
        switch (value){
            case EMPTY: iconId = R.drawable.ic_empty; break;
            case CHECK: iconId = R.drawable.ic_check; break;
            case CROSS: iconId = R.drawable.ic_cross; break;
            case EXCLAMATION: iconId = R.drawable.ic_exclamation; break;
            case QUESTION: iconId = R.drawable.ic_question; break;
        }
        cell.setImageResource(iconId);
    }

    private void setTableIcons(){
        for(int r=0; r<sheetRows.size(); r++){
            for(int c=0; c<6; c++){
                int[] state = c == 0 ? sheetState.getState() : sheetState.getPlayers()[c-1].getState();
                ImageView cell = (ImageView) sheetRows.get(r).getChildAt(c + 1);
                CellValue value = CellValue.values()[state[r]];
                setCellIcon(cell, value);
            }
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
        player1name.setText(sheetState.getPlayers()[0].getPlayerName());
        player2name.setText(sheetState.getPlayers()[1].getPlayerName());
        player3name.setText(sheetState.getPlayers()[2].getPlayerName());
        player4name.setText(sheetState.getPlayers()[3].getPlayerName());
        player5name.setText(sheetState.getPlayers()[4].getPlayerName());
    }
}