package dev.jorik.cluegame.entity;

import java.util.Arrays;

public class SheetState {
    private int[] state;
    private PlayerState[] players;

    public SheetState(){
        this.state = new int[19];
        players = new PlayerState[5];
        Arrays.fill(this.players, new PlayerState());
    }

    public SheetState(int[] state, PlayerState[] players) {
        this.state = state;
        this.players = players;
    }

    public int[] getState() {
        return state;
    }

    public void setState(int[] state) {
        this.state = state;
    }

    public PlayerState[] getPlayers() {
        return players;
    }

    public void setPlayers(PlayerState[] players) {
        this.players = players;
    }

    public void setItemState(int itemIndex, int state){
        this.state[itemIndex] = state;
    }
}
