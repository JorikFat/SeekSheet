package dev.jorik.cluegame.entity;

public class PlayerState {
    private String playerName;
    private int[] state;

    public PlayerState(){
        state = new int[19];
    }

    public PlayerState(String playerName, int[] state) {
        this.playerName = playerName;
        this.state = state;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int[] getState() {
        return state;
    }

    public void setState(int[] state) {
        this.state = state;
    }

    public void setItemState(int itemIndex, int state){
        this.state[itemIndex] = state;
    }
}
