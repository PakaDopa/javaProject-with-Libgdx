package com.mygdx.game.event;

public interface EventInterface {
    void create();
    void render(float dt);
    void defaultCreate(String token);
    void controlHP(int value);
    void controlMP(int value);

    /*
    void controlSkill();
    void controlItem();
    void controlInventory();
    void findNextRoom();
    */
}
