package dev.jorik.cluegame.modals;

import android.widget.ImageView;

import dev.jorik.cluegame.CellValue;

public interface SelectionCallback{
    void selectIcon(ImageView cell, CellValue value);
}