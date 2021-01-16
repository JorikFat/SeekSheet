package dev.jorik.cluegame.entity;

import java.util.Arrays;

public enum Value implements Indexed{ EMPTY, CHECK, CROSS, EXCLAMATION, QUESTION;

    @Override
    public int index(){
        return Arrays.asList(values()).indexOf(this);
    }
}
