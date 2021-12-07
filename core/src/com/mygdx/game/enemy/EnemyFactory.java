package com.mygdx.game.enemy;

import com.mygdx.game.base.BaseEnemy;

public class EnemyFactory
{
    public static BaseEnemy getEnemy(String name)
    {
        if(name.equals("Murloc"))
            return new Murloc();
        else if(name.equals("닭꼬치"))
            return new ChickenSkewer();
        return null;
    }
}
