package com.mygdx.game.player;

import com.mygdx.game.event.Compensation.item.BaseItem;
import com.mygdx.game.event.Compensation.item.ItemType;

import java.util.HashMap;
import java.util.Map;

public class Inventory extends Component{

    protected Map<String, BaseItem> inventory;
    protected BaseItem[] equipItem;

    public Inventory(Component parent) {
        super(parent);

        inventory = new HashMap<>();
        equipItem = new BaseItem[2];
        equipItem[ItemType.WEAPON.getValue()] = null;
        equipItem[ItemType.ARMOR.getValue()] = null;
    }
    public boolean addItem(BaseItem dropItem)
    {
        if(!inventory.containsKey(dropItem.getName()))
        {
            inventory.put(dropItem.getName(), dropItem);
            return true;
        }
        return false;
    }
    public boolean removeItem(BaseItem dropItem)
    {
        if(inventory.containsKey(dropItem.getName()))
        {
            inventory.remove(dropItem.getName());
            return true;
        }
        return false;
    }
    public boolean itemEquip(String name)
    {
        if(inventory.containsKey(name))
        {
            BaseItem item = inventory.get(name);

        }
    }
}
