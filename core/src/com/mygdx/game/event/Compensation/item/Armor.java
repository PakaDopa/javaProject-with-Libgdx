package com.mygdx.game.event.Compensation.item;

import com.mygdx.game.player.Player;
import com.mygdx.game.player.PlayerStatus;
import com.mygdx.game.textbox.TextBox;

public abstract class Armor extends BaseItem{
    protected int armor;
    protected int hp;
    public Armor(String name, int armor, int hp) {
        super(name, ItemType.ARMOR);
        this.armor = armor;
        this.hp = hp;
    }
    @Override
    public void unequipItem() {
        Player.instance.setStatus(PlayerStatus.DEFENSE.getKey(), -armor);
        Player.instance.setStatus(PlayerStatus.DEFENSE.getKey(), -hp);
        TextBox.instance.setDirect("    [!]" + name + "을/를 가방에 넣습니다.");
    }

    @Override
    public void equipItem() {
        Player.instance.setStatus(PlayerStatus.DEFENSE.getKey(), armor);
        Player.instance.setStatus(PlayerStatus.DEFENSE.getKey(), hp);
        TextBox.instance.setDirect("    [!]" + name + "을/를 착용합니다.");
    }
    @Override
    public void showItemInfo() {
        String token =
                itemInfo +
                        "    [!]체력 +" + hp + "\n" +
                        "    [!]방어력 +" + armor + "\n" +
                        "\n";
        TextBox.instance.setDirect(token);
    }
}

class OldShield extends Armor
{
    public OldShield() {
        super("낡은 방패", 4, 0);
        itemInfo = name + ": 낡은 방패이다..\n";
    }

    @Override
    public void passive() {

    }
}
