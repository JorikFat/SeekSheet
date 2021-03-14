package dev.jorik.cluegame.application.modals;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;

import androidx.core.widget.ImageViewCompat;

import dev.jorik.cluegame.databinding.PopupSelecticonBinding;
import dev.jorik.cluegame.sheet.domain.entity.Cell;
import dev.jorik.cluegame.sheet.domain.entity.Value;
import dev.jorik.cluegame.R;
import dev.jorik.cluegame.sheet.domain.entity.Color;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static dev.jorik.cluegame.utils.View.getIntColor;

public class SelectionPopup {
    private PopupSelecticonBinding binding;
    private ImageView cell;
    private PopupWindow window;
    private Color color;
    private final ImageView[] icons = new ImageView[4];
    private int verticalOffset;

    public SelectionPopup(PopupSelecticonBinding binding, SelectionCallback callback) {
        this.binding = binding;
        binding.ivSelectClear.setOnClickListener(v -> {
            callback.selectIcon(cell, new Cell(Value.EMPTY, color));
            cell = null;
            window.dismiss();
        });

        icons[0] = binding.ivSelectCheck;;
        binding.ivSelectCheck.setOnClickListener(v -> {
                    callback.selectIcon(cell, new Cell(Value.CHECK, color));
                    cell = null;
                    window.dismiss();
                });

        icons[1] = binding.ivSelectCross;
        binding.ivSelectCross.setOnClickListener(v -> {
                    callback.selectIcon(cell, new Cell(Value.CROSS, color));
                    cell = null;
                    window.dismiss();
                });

        icons[2] = binding.ivSelectExclamation;
        binding.ivSelectExclamation.setOnClickListener(v -> {
            callback.selectIcon(cell, new Cell(Value.EXCLAMATION, color));
            cell = null;
            window.dismiss();
        });

        icons[3] = binding.ivSelectQuestion;
        binding.ivSelectQuestion.setOnClickListener(v -> {
            callback.selectIcon(cell, new Cell(Value.QUESTION, color));
            cell = null;
            window.dismiss();
        });

        binding.viewSelectorBlack.setOnClickListener(view -> setColor(Color.BLACK));
        binding.viewSelectorRed.setOnClickListener(view -> setColor(Color.RED));
        binding.viewSelectorGreen.setOnClickListener(view -> setColor(Color.GREEN));
        binding.viewSelectorBlue.setOnClickListener(view -> setColor(Color.BLUE));
        binding.viewSelectorGray.setOnClickListener(view -> setColor(Color.GRAY));
        binding.viewSelectorCyan.setOnClickListener(view -> setColor(Color.CYAN));
        binding.viewSelectorMagenta.setOnClickListener(view -> setColor(Color.MAGENTA));
        binding.viewSelectorYellow.setOnClickListener(view -> setColor(Color.YELLOW));
        this.window = new PopupWindow(binding.getRoot(), WRAP_CONTENT, WRAP_CONTENT, true);
        this.color = Color.BLACK;
    }

    public void show(ImageView cell){
        this.cell = cell;
        View root = binding.getRoot();
        if (root.getHeight() > 0) verticalOffset = (int) ((root.getHeight() + root.getContext().getResources().getDimension(R.dimen.rowHeight)) * -1);
        window.showAsDropDown(cell, 0, verticalOffset);
    }

    private void setColor(Color color){
        this.color = color;
        Context context = binding.getRoot().getContext();
        for(ImageView image :icons){
            ImageViewCompat.setImageTintList(image, ColorStateList.valueOf(getIntColor(context, color)));
        }
    }
}
