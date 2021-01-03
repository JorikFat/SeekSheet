package dev.jorik.cluegame;

import java.util.Arrays;

public enum CellValue { EMPTY, CHECK, CROSS, EXCLAMATION, QUESTION;

    public int index(){
        return Arrays.asList(values()).indexOf(this);
    }
}
