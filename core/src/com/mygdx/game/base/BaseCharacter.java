package com.mygdx.game.base;

public class BaseCharacter extends BaseActor{
    int maxHp, maxMp, hp, mp;
    float attackDelay;
    public BaseCharacter(int maxHp, int maxMp, float attackDelay)
    {
        super();

        //status Setting
        this.maxHp = maxHp;
        this.maxMp = maxMp;
        this.attackDelay = attackDelay;

        hp = maxHp;
        mp = maxHp;
    }
}
