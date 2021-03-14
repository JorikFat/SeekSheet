package dev.jorik.cluegame.application.modals;

import android.widget.ImageView;

import dev.jorik.cluegame.sheet.domain.entity.Cell;

public interface SelectionCallback{
    void selectIcon(ImageView cellIcon, Cell cell);
}