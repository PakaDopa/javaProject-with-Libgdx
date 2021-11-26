package com.mygdx.game.base;

public abstract class BaseEnemy extends BaseCharacter{

    public BaseEnemy(int maxHp, int maxMp, float attackDelay) {
        super(maxHp, maxMp, attackDelay);
    }
    protected abstract void pattern();
}
