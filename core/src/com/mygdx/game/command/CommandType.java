package com.mygdx.game.command;

public enum CommandType {
    MIX_UP(0), //이지 선다
    ENCOUNTER(1);//마주치다

    private final int value;
    CommandType(int value)
    {
        this.value = value;
    }
    public int getValue()
    {
        return value;
    }
}
