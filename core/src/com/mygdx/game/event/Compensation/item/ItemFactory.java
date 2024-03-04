package com.mygdx.game.event.Compensation.item;

public class ItemFactory {
    public static BaseItem getItem(String name)
    {
        if(name.equals("Sword"))
            return new Sword();
        else if(name.equals("spiderWeb"))
            return new spiderWeb();
        else if(name.equals("Excalibur"))
            return new Excalibur();
        else if(name.equals("StoneShield"))
            return new StoneShield();
        else if(name.equals("Armour"))
            return new Armour();
        return null;
    }

}
