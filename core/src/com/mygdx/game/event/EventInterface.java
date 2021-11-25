package com.mygdx.game.event;

import com.mygdx.game.command.CommandType;

public interface EventInterface {
    void create();
    boolean update(float dt);
    CommandType defauleUpdate(float dt);
    //void controlHP(int value);
    //void controlMP(int value);
}
