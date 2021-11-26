package com.mygdx.game.event.Compensation.item;

import com.mygdx.game.player.Player;
import com.mygdx.game.player.PlayerStatus;

public abstract class Armor extends BaseItem{
    protected int armor;

    public Armor(String name, int armor) {
        super(name, ItemType.ARMOR);
        this.armor = armor;
    }
    @Override
    public void unequipItem() {
        Player.instance.setStatus(PlayerStatus.DEFENSE.getInd(), -armor);
    }

    @Override
    public void equipItem() {
        Player.instance.setStatus(PlayerStatus.DEFENSE.getInd(), armor);
    }

    @Override
    public String showItemInfo() {
        String token =
                        itemInfo +
                        "    [!]방어력 +" + armor + "\n";
        return token;
    }
}

class OldShield extends Armor
{
    public OldShield(String name) {
        super(name, 4);
        itemInfo = "낡은 방패이다..\n";
    }

    @Override
    public void passive() {

    }
}
