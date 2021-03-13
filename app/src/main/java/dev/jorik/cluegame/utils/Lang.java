package dev.jorik.cluegame.utils;

import java.util.ArrayList;
import java.util.List;

public class Lang {
    public static <T> T[] filter(T[] array, Condition<T> condition){
        List<T> tempList = new ArrayList<>();
        for(T t : array) if(condition.check(t)) tempList.add(t);
        return tempList.toArray(array);
    }

    public static <S, T> List<T> map(List<S> list, Converter<S, T> converter){
        List<T> returnList = new ArrayList<>();
        for(S source : list)returnList.add(converter.map(source));
        return returnList;
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
