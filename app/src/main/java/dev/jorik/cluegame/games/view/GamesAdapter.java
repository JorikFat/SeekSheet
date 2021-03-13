package dev.jorik.cluegame.games.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import dev.jorik.cluegame.R;
import dev.jorik.cluegame.games.domain.Game;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.GameViewHolder> {
    private DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private List<Game> data = new ArrayList<>();
    private Callback callback;

    public GamesAdapter(Callback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_game, parent, false);
        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        Game game = data.get(position);
        holder.bind(game);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Game> games){
        data.clear();
        data.addAll(games);
        notifyDataSetChanged();
    }

    public interface Callback{
        void onItemClick(Game game);
    }

    class GameViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout names;
        private TextView date;

        public GameViewHolder(@NonNull View itemView) {
            super(itemView);
            names = itemView.findViewById(R.id.LL_gameItem_players);
            date = itemView.findViewById(R.id.tv_gameItem_date);
            itemView.setOnClickListener(view -> callback.onItemClick(data.get(getAdapterPosition())));
        }

        public void bind(Game game){
            date.setText(dateFormat.format(game.creatingDate));
            int countDiff = names.getChildCount() - game.getPlayersName().length;
            if (countDiff > 0){
                for(int i=0; i<countDiff; i++) names.removeViewAt(names.getChildCount()-1);
            } else if (countDiff < 0){
                for(int i=countDiff; i<0; i++) names.addView(new TextView(itemView.getContext()));
            }
            for (int i=0; i<game.getPlayersName().length; i++){
                ((TextView)names.getChildAt(i)).setText(game.getPlayersName()[i]);
            }
        }
    }
}
