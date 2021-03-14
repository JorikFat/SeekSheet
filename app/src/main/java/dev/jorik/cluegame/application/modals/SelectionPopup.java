package dev.jorik.cluegame.application.modals;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;

import androidx.core.widget.ImageViewCompat;

import dev.jorik.cluegame.sheet.domain.entity.Cell;
import dev.jorik.cluegame.sheet.domain.entity.Value;
import dev.jorik.cluegame.R;
import dev.jorik.cluegame.sheet.domain.entity.Color;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static dev.jorik.cluegame.utils.View.getIntColor;

public class SelectionPopup {
    private final Context context;
    private ImageView cell;
    private final View view;
    private PopupWindow window;
    private Color color;
    private final ImageView[] icons = new ImageView[4];
    private int verticalOffset;

    public SelectionPopup(Context context, SelectionCallback callback) {
        this.context = context;
        this.view = LayoutInflater.from(context).inflate(R.layout.popup_selecticon, null);
        ImageView clear = view.findViewById(R.id.iv_select_clear);
        clear.setOnClickListener(v -> {
            callback.selectIcon(cell, new Cell(Value.EMPTY, color));
            cell = null;
            window.dismiss();
        });

        ImageView check = view.findViewById(R.id.iv_select_check);
        icons[0] = check;
        check.setOnClickListener(v -> {
                    callback.selectIcon(cell, new Cell(Value.CHECK, color));
                    cell = null;
                    window.dismiss();
                });

        ImageView cross = view.findViewById(R.id.iv_select_cross);
        icons[1] = cross;
        cross.setOnClickListener(v -> {
                    callback.selectIcon(cell, new Cell(Value.CROSS, color));
                    cell = null;
                    window.dismiss();
                });

        ImageView exclamation = view.findViewById(R.id.iv_select_exclamation);
        icons[2] = exclamation;
        exclamation.setOnClickListener(v -> {
            callback.selectIcon(cell, new Cell(Value.EXCLAMATION, color));
            cell = null;
            window.dismiss();
        });

        ImageView question = view.findViewById(R.id.iv_select_question);
        icons[3] = question;
        question.setOnClickListener(v -> {
            callback.selectIcon(cell, new Cell(Value.QUESTION, color));
            cell = null;
            window.dismiss();
        });

        view.findViewById(R.id.view_selector_black).setOnClickListener(view -> setColor(Color.BLACK));
        view.findViewById(R.id.view_selector_red).setOnClickListener(view -> setColor(Color.RED));
        view.findViewById(R.id.view_selector_green).setOnClickListener(view -> setColor(Color.GREEN));
        view.findViewById(R.id.view_selector_blue).setOnClickListener(view -> setColor(Color.BLUE));
        view.findViewById(R.id.view_selector_gray).setOnClickListener(view -> setColor(Color.GRAY));
        view.findViewById(R.id.view_selector_cyan).setOnClickListener(view -> setColor(Color.CYAN));
        view.findViewById(R.id.view_selector_magenta).setOnClickListener(view -> setColor(Color.MAGENTA));
        view.findViewById(R.id.view_selector_yellow).setOnClickListener(view -> setColor(Color.YELLOW));
        this.window = new PopupWindow(view, WRAP_CONTENT, WRAP_CONTENT, true);
        this.color = Color.BLACK;
    }

    public void show(ImageView cell){
        this.cell = cell;
        if (view.getHeight() > 0) verticalOffset = (int) ((view.getHeight() + context.getResources().getDimension(R.dimen.rowHeight)) * -1);
        window.showAsDropDown(cell, 0, verticalOffset);
    }

    private void setColor(Color color){
        this.color = color;
        for(ImageView image :icons){
            ImageViewCompat.setImageTintList(image, ColorStateList.valueOf(getIntColor(context, color)));
        }
    }
}
