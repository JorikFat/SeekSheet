package dev.jorik.cluegame.utils;

import java.util.Arrays;

public class Language {
    public static <E> int index(E[] array, E element){
        return Arrays.asList(array).indexOf(element);
    }
}
