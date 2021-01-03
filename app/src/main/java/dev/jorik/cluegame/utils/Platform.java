package dev.jorik.cluegame.utils;

import android.text.TextWatcher;

public class Platform {
    public interface TextListener extends TextWatcher{
        @Override
        default void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {/*skip*/}

        @Override
        default void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {/*skip*/}
    }
}
