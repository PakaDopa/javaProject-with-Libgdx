package com.mygdx.game.event.Compensation.item;

public enum ItemType {
    WEAPON(0),
    ARMOR(1);

    private final int value;
    ItemType(int value) { this.value = value;}
    public int getValue() {return value;}
}
