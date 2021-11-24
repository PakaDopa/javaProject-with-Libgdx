package com.mygdx.game.event;

public interface EventInterface {
    void create();
    boolean render(float dt);
    void defaultCreate();
    void controlHP(int value);
    void controlMP(int value);
}
