package dev.jorik.cluegame.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Lang {
    public static <T> T[] filter(T[] array, Condition<T> condition){
        return filter(Arrays.asList(array), condition).toArray(array);
    }

    public static <T> List<T> filter(List<T> list, Condition<T> condition){
        List<T> tempList = new ArrayList<>();
        for(T t : list) if(condition.check(t)) tempList.add(t);
        return tempList;
    }

    public static <S, T> List<T> map(List<S> list, Converter<S, T> converter){
        List<T> returnList = new ArrayList<>();
        for(S source : list)returnList.add(converter.map(source));
        return returnList;
    }

    public static <T> T find(List<T> list, Condition<T> condition){
        for(T t : list) if(condition.check(t)) return t;
        return null;
    }

    public static String concat(List<String> list, String separator){
        StringBuilder builder = new StringBuilder(list.get(0));
        for(int i = 1; i<list.size(); i++) builder.append(separator).append(list.get(i));
        return builder.toString();
    }

    public interface Converter<S, T>{
        T map(S t);
    }

    public interface Condition<T>{
        boolean check(T t);
    }
}
