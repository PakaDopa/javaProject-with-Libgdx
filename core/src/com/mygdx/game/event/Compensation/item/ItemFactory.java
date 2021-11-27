package com.mygdx.game.event.Compensation.item;

public class ItemFactory {
    public static BaseItem getItem(String name)
    {
        if(name.equals("Sword"))
            return new Sword();
        else if(name.equals("OldShield"))
            return new OldShield();
        return null;
    }
}
