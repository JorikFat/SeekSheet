package dev.jorik.cluegame;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public class SelectIconDialog extends AlertDialog {
    protected SelectIconDialog(Context context, Callback callback) {
        super(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_selecticon, null, false);
        view.findViewById(R.id.iv_selectIcon_clear).setOnClickListener(v -> {
            callback.selectIcon(CellValue.EMPTY);
            this.cancel();
        });
        view.findViewById(R.id.iv_selectIcon_check).setOnClickListener(v -> {
            callback.selectIcon(CellValue.CHECK);
            this.cancel();
        });
        view.findViewById(R.id.iv_selectIcon_cross).setOnClickListener(v -> {
            callback.selectIcon(CellValue.CROSS);
            this.cancel();
        });
        view.findViewById(R.id.iv_selectIcon_exclamation).setOnClickListener(v -> {
            callback.selectIcon(CellValue.EXCLAMATION);
            this.cancel();
        });
        view.findViewById(R.id.iv_selectIcon_question).setOnClickListener(v -> {
            callback.selectIcon(CellValue.QUESTION);
            this.cancel();
        });
        setView(view);
        setTitle(R.string.selectIcon_title);
    }

    public interface Callback{
        void selectIcon(CellValue value);
    }
}
