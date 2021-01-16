package dev.jorik.cluegame.utils;

import android.content.Context;

import androidx.annotation.ColorInt;
import androidx.core.content.ContextCompat;

import dev.jorik.cluegame.R;
import dev.jorik.cluegame.entity.Color;

public class View {
    public static @ColorInt int getIntColor(Context context, Color color){
        int colorId;
        switch (color){
            case RED: colorId = R.color.red; break;
            case GREEN: colorId = R.color.green; break;
            case BLUE: colorId = R.color.blue; break;
            case CYAN: colorId = R.color.cyan; break;
            case MAGENTA: colorId = R.color.magenta; break;
            case YELLOW: colorId = R.color.yellow; break;
            case GRAY: colorId = R.color.gray; break;
            default: colorId = R.color.black; break;
        }
        return ContextCompat.getColor(context, colorId);
    }
}
