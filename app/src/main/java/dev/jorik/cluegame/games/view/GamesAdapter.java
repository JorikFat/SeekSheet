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
import dev.jorik.cluegame.databinding.ItemGameBinding;
import dev.jorik.cluegame.games.domain.Game;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.GameViewHolder> {
    private final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private final List<Game> data = new ArrayList<>();
    private final Callback callback;

    public GamesAdapter(Callback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new GameViewHolder(ItemGameBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        holder.bind(data.get(position));
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
        void onItemHold(View holder, Game game);
    }

    class GameViewHolder extends RecyclerView.ViewHolder{
        private final ItemGameBinding binding;

        public GameViewHolder(ItemGameBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(view -> callback.onItemClick(data.get(getAdapterPosition())));
            itemView.setOnLongClickListener(v -> {
                callback.onItemHold(itemView, data.get(getAdapterPosition()));
                return true;
            });
        }

        public void bind(Game game){
            binding.tvGameItemDate.setText(dateFormat.format(game.creatingDate));
            LinearLayout names = binding.LLGameItemPlayers;
            int countDiff = names.getChildCount() - game.getPlayersName().length;
            if (countDiff > 0){
                for(int i=0; i<countDiff; i++) names.removeViewAt(names.getChildCount()-1);
            } else if (countDiff < 0){
                for(int i=countDiff; i<0; i++) names.addView(new TextView(itemView.getContext()));
            }
            for (int i=0; i<game.getPlayersName().length; i++){
                String playerName = game.getPlayersName()[i];
                String name = playerName.isEmpty() ? itemView.getContext().getString(R.string.gameitem_emptyName) : playerName;
                ((TextView)names.getChildAt(i)).setText(name);
            }
        }
    }
}
