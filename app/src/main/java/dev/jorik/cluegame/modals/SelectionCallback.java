package dev.jorik.cluegame.modals;

import android.widget.ImageView;

import dev.jorik.cluegame.CellValue;
import dev.jorik.cluegame.entity.Color;

public interface SelectionCallback{
    void selectIcon(ImageView cell, CellValue value);
    void selectColor(Color color);
}