package dev.jorik.cluegame;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ImageViewCompat;

import java.util.ArrayList;
import java.util.List;

import dev.jorik.cluegame.entity.Cell;
import dev.jorik.cluegame.entity.SheetCells;
import dev.jorik.cluegame.entity.Value;
import dev.jorik.cluegame.modals.ConfirmDialog;
import dev.jorik.cluegame.modals.NamesDialog;
import dev.jorik.cluegame.modals.SelectionPopup;
import dev.jorik.cluegame.utils.Platform;

import static android.graphics.Color.TRANSPARENT;
import static dev.jorik.cluegame.utils.View.getIntColor;

public class SheetActivity extends AppCompatActivity {
    private SelectionPopup selecting;
    private Config config;
    private SheetCells sheetCells;
    private TextView player1name;
    private TextView player2name;
    private TextView player3name;
    private TextView player4name;
    private TextView player5name;
    private List<TableRow> sheetRows = new ArrayList<>();

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        player1name = findViewById(R.id.tv_sheet_player1name);
        player2name = findViewById(R.id.tv_sheet_player2name);
        player3name = findViewById(R.id.tv_sheet_player3name);
        player4name = findViewById(R.id.tv_sheet_player4name);
        player5name = findViewById(R.id.tv_sheet_player5name);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet);
        config = new Config(PreferenceManager.getDefaultSharedPreferences(this));
        selecting = new SelectionPopup(this, (cellIcon, cell) -> {
            setCellIcon(cellIcon, cell);

            TableRow row = ((TableRow) cellIcon.getParent());
            int rowIndex = sheetRows.indexOf(row);
            int columnIndex = row.indexOfChild(cellIcon)-1;

            if (columnIndex == 0) sheetCells.setCell(rowIndex, cell);
            else sheetCells.getPlayers()[columnIndex-1].setCell(rowIndex, cell);
        });

        if (config.isNewGame()) initPlayers();
        sheetCells = config.getSheet();

        player1name.addTextChangedListener((Platform.TextListener) text -> sheetCells.getPlayers()[0].setPlayerName(text.toString()));
        player2name.addTextChangedListener((Platform.TextListener) text -> sheetCells.getPlayers()[1].setPlayerName(text.toString()));
        player3name.addTextChangedListener((Platform.TextListener) text -> sheetCells.getPlayers()[2].setPlayerName(text.toString()));
        player4name.addTextChangedListener((Platform.TextListener) text -> sheetCells.getPlayers()[3].setPlayerName(text.toString()));
        player5name.addTextChangedListener((Platform.TextListener) text -> sheetCells.getPlayers()[4].setPlayerName(text.toString()));

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
                setTableIcons();
                setPlayersName();
                config.setNewGame(true);
                initPlayers();
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
            for(int c=0; c<6; c++){
                ImageView cellView = (ImageView) sheetRows.get(r).getChildAt(c + 1);
                Cell cell = c == 0 ? sheetCells.getCells()[r] : sheetCells.getPlayers()[c-1].getCells()[r];
                setCellIcon(cellView, cell);
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
        player1name.setText(sheetCells.getPlayers()[0].getPlayerName());
        player2name.setText(sheetCells.getPlayers()[1].getPlayerName());
        player3name.setText(sheetCells.getPlayers()[2].getPlayerName());
        player4name.setText(sheetCells.getPlayers()[3].getPlayerName());
        player5name.setText(sheetCells.getPlayers()[4].getPlayerName());
    }

    private void initPlayers(){
        new NamesDialog(this, names -> {
            for(int i=0; i<names.length; i++){
                sheetCells.getPlayers()[i].setPlayerName(getInitials(names[i]));
            }
            config.setNewGame(false);
            setPlayersName();
        }).show();
    }

    private String getInitials(String fullName){
        StringBuilder builder = new StringBuilder();
        for (String part : fullName.split(" "))
            builder.append(part.substring(0,1));
        return builder.toString();
    }
}