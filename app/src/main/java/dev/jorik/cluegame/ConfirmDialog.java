package dev.jorik.cluegame;

import android.app.AlertDialog;
import android.content.Context;

public class ConfirmDialog extends AlertDialog {

    public ConfirmDialog(Context context, int titleId, int messageId, ConfirmCallback callback){
        this(context, context.getString(titleId), context.getString(messageId), callback);
    }

    protected ConfirmDialog(Context context, String title, String message, ConfirmCallback callback) {
        super(context);
        setTitle(title);
        setMessage(message);
        setButton(BUTTON_POSITIVE, context.getString(R.string.yes), (d, i) -> callback.onConfirm());
        setButton(BUTTON_NEGATIVE, context.getString(R.string.cancel), (d, i) -> d.cancel());
    }

    interface ConfirmCallback{
        void onConfirm();
    }
}
