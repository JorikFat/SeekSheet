package dev.jorik.cluegame.gameslist;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;

import dev.jorik.cluegame.App;
import dev.jorik.cluegame.R;
import dev.jorik.cluegame.gameslist.presentation.GameListViewModel;
import dev.jorik.cluegame.modals.NamesDialog;

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
        viewModel = new ViewModelProvider(this, new ViewModelFactory()).get(GameListViewModel.class);
        viewModel.getGamesProvider().observe(this, games -> adapter.setData(games));
        add.setOnClickListener(view -> new NamesDialog(GamesListActivity.this,
                (names, keepCells) -> viewModel.createGame(names, keepCells)).show()
        );
    }

    private void showMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public class ViewModelFactory implements ViewModelProvider.Factory{
        @NonNull @Override @SuppressWarnings("unchecked")
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new GameListViewModel(((App) getApplication()).getDatabase().gamesDao());
        }
    }
}
