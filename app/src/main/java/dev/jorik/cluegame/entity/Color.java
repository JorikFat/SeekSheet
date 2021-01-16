package dev.jorik.cluegame.entity;

import java.util.Arrays;

public enum Color implements Indexed { BLACK, RED, GREEN, BLUE, GRAY, CYAN, MAGENTA, YELLOW;

    @Override
    public int index() {
        return Arrays.asList(values()).indexOf(this);
    }
}
