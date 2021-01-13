package dev.jorik.cluegame.modals;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;

import dev.jorik.cluegame.CellValue;
import dev.jorik.cluegame.R;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class SelectionPopup {
    private ImageView cell;
    private View view;
    private PopupWindow window;

    public SelectionPopup(Context context, SelectionCallback callback) {
        this.view = LayoutInflater.from(context).inflate(R.layout.dialog_selecticon, null);
        view.findViewById(R.id.iv_selectIcon_clear).setOnClickListener(v -> {
            callback.selectIcon(cell, CellValue.EMPTY);
            cell = null;
            window.dismiss();
        });
        view.findViewById(R.id.iv_selectIcon_check).setOnClickListener(v -> {
            callback.selectIcon(cell, CellValue.CHECK);
            cell = null;
            window.dismiss();
        });
        view.findViewById(R.id.iv_selectIcon_cross).setOnClickListener(v -> {
            callback.selectIcon(cell, CellValue.CROSS);
            cell = null;
            window.dismiss();
        });
        view.findViewById(R.id.iv_selectIcon_exclamation).setOnClickListener(v -> {
            callback.selectIcon(cell, CellValue.EXCLAMATION);
            cell = null;
            window.dismiss();
        });
        view.findViewById(R.id.iv_selectIcon_question).setOnClickListener(v -> {
            callback.selectIcon(cell, CellValue.QUESTION);
            cell = null;
            window.dismiss();
        });

        this.window = new PopupWindow(view, WRAP_CONTENT, WRAP_CONTENT, true);
    }

    public void show(ImageView cell){
        this.cell = cell;
        window.showAsDropDown(cell, 0, (int)(-view.getHeight()*1.7));
    }
}
