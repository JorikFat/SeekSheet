package dev.jorik.cluegame.games.data.database;

import androidx.room.TypeConverter;

import java.util.Arrays;

import dev.jorik.cluegame.utils.Lang;

public class NamesConverter{
    private final String separator = "_";
    @TypeConverter
    public String[] toData(String names){
        return names.split(separator);
    }

    @TypeConverter
    public String fromData(String[] names){
        return Lang.concat(Arrays.asList(names), separator);
    }
}