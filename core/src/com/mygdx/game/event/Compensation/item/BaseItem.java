package com.mygdx.game.event.Compensation.item;

public abstract class BaseItem {
    protected String name = "";
    protected String itemInfo = "";
    protected ItemType type;
    public BaseItem(String name, ItemType type)
    {
        this.name = name;
        this.type = type;
    }
    public String getName() {return name;}
    public ItemType getType() {return type;}

    public abstract void unequipItem();
    public abstract void equipItem();
    public abstract void passive();
    public abstract void showItemInfo();

}
