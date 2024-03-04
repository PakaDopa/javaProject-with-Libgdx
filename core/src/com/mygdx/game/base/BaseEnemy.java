package com.mygdx.game.base;

import com.mygdx.game.event.Compensation.Result;

public abstract class BaseEnemy extends BaseCharacter{

    public String name;
    public float damage;
    public Result result;
    public BaseEnemy(String name, float damage, int maxHp, int maxMp, float attackDelay) {
        super(maxHp, maxMp, attackDelay);
        this.name = name;
        this.damage = damage;
    }
    public void setResult(Result result)
    {
        this.result = result;
    }
    public abstract void pattern();
}
