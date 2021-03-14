package dev.jorik.cluegame.games.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import dev.jorik.cluegame.application.App;
import dev.jorik.cluegame.application.modals.NamesDialog;
import dev.jorik.cluegame.databinding.ActivityGameslistBinding;
import dev.jorik.cluegame.games.data.GamesRepository;
import dev.jorik.cluegame.games.domain.GamesDomain;
import dev.jorik.cluegame.games.presentation.GameListViewModel;
import dev.jorik.cluegame.games.presentation.ViewModelFactory;
import dev.jorik.cluegame.sheet.view.SheetActivity;

public class GamesListActivity extends AppCompatActivity {
    private GamesAdapter adapter;
    private GameListViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityGameslistBinding binding = ActivityGameslistBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        adapter = new GamesAdapter(game -> {
            viewModel.selectGame(game);
            startActivity(new Intent(GamesListActivity.this, SheetActivity.class));
        });
        binding.rvGamesListList.setAdapter(adapter);
        viewModel = new ViewModelProvider(this, new ViewModelFactory(simpleLocationService())).get(GameListViewModel.class);
        viewModel.getGamesProvider().observe(this, games -> adapter.setData(games));
        binding.btnGamesListCreate.setOnClickListener(view -> new NamesDialog(GamesListActivity.this,
                (names, keepCells) -> {
                    viewModel.createGame(names, keepCells);
                    startActivity(new Intent(GamesListActivity.this, SheetActivity.class));
                }).show());
    }

    private GamesDomain simpleLocationService(){//todo вынести в LS
        App app = ((App) getApplication());
        GamesRepository provider = new GamesRepository(app.getDatabase().gamesDao());
        GamesDomain domain = new GamesDomain(provider);
        domain.setOutport(app.getSheetDomain());
        return domain;
    }
}
