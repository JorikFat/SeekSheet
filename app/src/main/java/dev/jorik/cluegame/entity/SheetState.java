package dev.jorik.cluegame.entity;

public class SheetState {
    private int[] state;
    private PlayerState[] players;

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
