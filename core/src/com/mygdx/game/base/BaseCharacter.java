package com.mygdx.game.base;

import com.badlogic.gdx.math.MathUtils;

public class BaseCharacter extends BaseActor{
    public float maxHp, maxMp, hp, mp;
    public float attackDelay;
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
    public boolean isDead()
    {
        return hp <= 0;
    }
    public void setHP(float hp)
    {
        this.hp = MathUtils.clamp(this.hp + hp, 0, maxHp);
    }
}
