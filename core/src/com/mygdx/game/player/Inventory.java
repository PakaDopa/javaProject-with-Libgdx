package com.mygdx.game.player;

import com.mygdx.game.command.CommandManangement;
import com.mygdx.game.event.Compensation.item.BaseItem;
import com.mygdx.game.event.Compensation.item.ItemType;
import com.mygdx.game.textbox.TextBox;

import java.util.HashMap;
import java.util.Map;

public class Inventory extends Component{

    protected Map<String, BaseItem> inventory;
    protected BaseItem[] equipItem;

    public Inventory(Player parent) {
        super(parent);

        inventory = new HashMap<>();
        equipItem = new BaseItem[2];
        equipItem[ItemType.WEAPON.getValue()] = null;
        equipItem[ItemType.ARMOR.getValue()] = null;
    }
    public void addItem(BaseItem dropItem, boolean printing)
    {
        if(!inventory.containsKey(dropItem.getName()))
        {
            inventory.put(dropItem.getName(), dropItem);
            CommandManangement.instance.addItemAddCommand(dropItem.getName());
            TextBox.instance.setDirect("    [!]" + dropItem.getName() +"을(를) 획득했습니다!");
        }
        else
        {
            TextBox.instance.setDirect("    [!]" + dropItem.getName() +"은 이미 소지하고 있습니다.");
        }
    }
    public boolean removeItem(BaseItem dropItem, boolean printing)
    {
        if(inventory.containsKey(dropItem.getName()))
        {
            CommandManangement.instance.removeItemAddCommand(dropItem.getName());
            inventory.remove(dropItem.getName());
            TextBox.instance.setDirect("    [!]" + dropItem.getName() +"을(를) 버렸습니다.");
            return true;
        }
        return false;
    }
    public boolean itemEquip(String token)
    {
        String name = token.replace(CommandManangement.instance.itemUseCommand, "");
        if(inventory.containsKey(name))
        {
            BaseItem item = inventory.get(name);
            int ind = item.getType().getValue();
            if(equipItem[ind] == null)
            {
                equipItem[ind] = item;
                equipItem[ind].equipItem();
                return true;
            }
            else return false;
        }
        return false;
    }
    public boolean itemUnequip(String token)
    {
        String type = token.replace(CommandManangement.instance.itemUseCommand, "");
        ItemType itemType;
        if(type.contains("weapon"))
            itemType = ItemType.WEAPON;
        else
            itemType = ItemType.ARMOR;
        if(equipItem[itemType.getValue()] == null)
            TextBox.instance.setDirect("해제 할 아이템이 없습니다.");
        else{
            equipItem[itemType.getValue()].unequipItem();
            TextBox.instance.setDirect(equipItem[itemType.getValue()].getName() + "을" +
                    "가방에 집에 넣었습니다.");
            equipItem[itemType.getValue()] = null;
        }
        return false;
    }
    public void showItemInfo(String token)
    {
        String name = token.replace(CommandManangement.instance.itemInfoCommand, "");
        inventory.get(name).showItemInfo();
    }
    public void showEquipItem()
    {
        if(equipItem[ItemType.WEAPON.getValue()] != null)
            equipItem[ItemType.WEAPON.getValue()].showItemInfo();
        else if(equipItem[ItemType.ARMOR.getValue()] != null)
            equipItem[ItemType.ARMOR.getValue()].showItemInfo();
    }
}
