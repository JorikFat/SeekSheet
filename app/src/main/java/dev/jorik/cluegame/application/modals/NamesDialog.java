package dev.jorik.cluegame.application.modals;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import dev.jorik.cluegame.R;

import static dev.jorik.cluegame.utils.Platform.getString;

public class NamesDialog extends AlertDialog {
    private final EditText[] nameFields = new EditText[5];
    private final CheckBox keepPlayers;

    public NamesDialog(Context context, CreateCallback callback) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_playersname, null, false);
        nameFields[0] = view.findViewById(R.id.et_playersName_name1);
        nameFields[1] = view.findViewById(R.id.et_playersName_name2);
        nameFields[2] = view.findViewById(R.id.et_playersName_name3);
        nameFields[3] = view.findViewById(R.id.et_playersName_name4);
        nameFields[4] = view.findViewById(R.id.et_playersName_name5);
        keepPlayers = view.findViewById(R.id.cb_playersName_keepUnnamedPlayers);
        setView(view);
        setTitle(R.string.sheet_initPlayersNames);
        setButton(BUTTON_POSITIVE, context.getString(R.string.start), (d, i) -> {
            List<String> names = new ArrayList<>();
            for (EditText field : nameFields) names.add(getString(field));
            callback.onCreate(names.toArray(new String[0]), keepPlayers.isChecked());
        });
        setCancelable(false);
    }

    public interface CreateCallback{
        void onCreate(String[] names, boolean keepCells);
    }
}
