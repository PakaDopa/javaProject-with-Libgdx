package com.mygdx.game.player;

public enum PlayerStatus {
    HP(0),
    MP(1),
    DEFENSE(2),
    DAMAGE(3),
    SKILL_DAMAGE(4),
    COMMAND_DELAY(5);

    private final int ind;
    PlayerStatus(int ind) {this.ind = ind;}
    public int getInd() {return ind;}
}
