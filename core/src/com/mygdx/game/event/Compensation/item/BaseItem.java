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
    public String getDropMessage()
    {
        String token = "System: 아이템 '" + name + "'을 드랍 했습니다. \n";
        return token;
    }
    public String getEquipMessage()
    {
        String token = "==================\n" +
                "아이템 '" + name + "'을 착용했다. \n" +
                "System: '/inventory {name} info' 로 정보를 확인 할 수 있습니다. \n" +
                "==================\n";
        return token;
    }
    public abstract void unequipItem();
    public abstract void equipItem();
    public abstract void passive();
    public abstract String showItemInfo();

}
