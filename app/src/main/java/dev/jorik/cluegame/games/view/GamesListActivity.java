package dev.jorik.cluegame.games.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;

import dev.jorik.cluegame.R;
import dev.jorik.cluegame.application.App;
import dev.jorik.cluegame.games.data.DatabaseProvider;
import dev.jorik.cluegame.games.domain.GamesDomain;
import dev.jorik.cluegame.games.presentation.GameListViewModel;
import dev.jorik.cluegame.games.presentation.ViewModelFactory;
import dev.jorik.cluegame.modals.NamesDialog;
import dev.jorik.cluegame.sheet.view.SheetActivity;

public class GamesListActivity extends AppCompatActivity {
    private RecyclerView list;
    private FloatingActionButton add;
    private GamesAdapter adapter;
    private GameListViewModel viewModel;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        list = findViewById(R.id.tv_gamesList_list);
        add = findViewById(R.id.btn_gamesList_create);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameslist);
        adapter = new GamesAdapter(game -> showMessage(Arrays.toString(game.getPlayersName())));
        list.setAdapter(adapter);
        viewModel = new ViewModelProvider(this, new ViewModelFactory(simpleLocationService())).get(GameListViewModel.class);
        viewModel.getGamesProvider().observe(this, games -> adapter.setData(games));
        add.setOnClickListener(view -> {
            new NamesDialog(GamesListActivity.this,
                    (names, keepCells) -> {
                        viewModel.createGame(names, keepCells);
                        startActivity(new Intent(GamesListActivity.this, SheetActivity.class));
                    }).show();
        });
    }

    private GamesDomain simpleLocationService(){//todo вынести в LS
        App app = ((App) getApplication());
        DatabaseProvider provider = new DatabaseProvider(app.getDatabase().gamesDao());
        GamesDomain domain = new GamesDomain(provider);
        domain.setOutport(app.getSheetDomain());
        return domain;
    }

    private void showMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
