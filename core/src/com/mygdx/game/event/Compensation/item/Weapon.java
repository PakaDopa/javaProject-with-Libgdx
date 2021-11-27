package com.mygdx.game.event.Compensation.item;

import com.mygdx.game.player.Player;
import com.mygdx.game.player.PlayerStatus;

public abstract class Weapon extends BaseItem{
    protected int damage;
    protected float plusCommandDelay = 0f; //무기 공격 속도
    protected String itemInfo = "\n";

    public Weapon(String name, int damage, float plusCommandDelay) {
        super(name, ItemType.WEAPON);
        this.damage = damage;
        this.plusCommandDelay = plusCommandDelay;
    }

    @Override
    public void unequipItem() {
        Player.instance.setStatus(PlayerStatus.DAMAGE.getInd(), -damage);
        Player.instance.setStatus(PlayerStatus.COMMAND_DELAY.getInd(), -plusCommandDelay);
    }
    @Override
    public void equipItem() {
        Player.instance.setStatus(PlayerStatus.DAMAGE.getInd(), damage);
        Player.instance.setStatus(PlayerStatus.DAMAGE.getInd(), plusCommandDelay);
    }
    @Override
    public String showItemInfo() {
        String token =
                        itemInfo +
                        "    [!]데미지 +" + damage + "\n" +
                        "    [!]커맨드 딜레이 +" + plusCommandDelay + "\n";
        return token;
    }
}

class Sword extends Weapon
{
    public Sword()
    {
        super("낡은 검", 2, 0f);
        itemInfo = "낡은 검이다.\n";
    }

    @Override
    public void passive() {

    }
}
