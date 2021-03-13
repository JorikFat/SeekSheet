package dev.jorik.cluegame.gameslist;

public class DataSet {
    private static String[] playersName = new String[]{
            "Первый игрок",
            "Второй игрок",
            "Третий игрок",
            "Четвертый игрок",
            "Пятый игрок"
    };

    public static String[] getPlayersName(int count){
        String[] rNames = new String[count];
        for(int i=0; i<count; i++) rNames[i] = playersName[i];
        return rNames;
    }
}
