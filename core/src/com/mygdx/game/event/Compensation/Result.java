package com.mygdx.game.event.Compensation;

import com.mygdx.game.event.Compensation.item.BaseItem;
import com.mygdx.game.player.Player;
import com.mygdx.game.player.PlayerStatus;
import com.mygdx.game.utils.Pair;

import java.util.ArrayList;
import java.util.List;

public class Result {

    Pair<PlayerStatus, Float> rewardStatus;
    BaseItem rewardItem;
    //BaseSkill skillList;

    public Result(Pair<PlayerStatus, Float> element)
    {
        rewardStatus = element;
    }
    public Result(BaseItem item)
    {
        rewardItem = item;
    }
    public void applyReward()
    {
        if(rewardStatus != null)
        {
            Player.instance.setStatus(rewardStatus.getX().getKey(), rewardStatus.getY());
        }
        else if(rewardItem != null)
        {
            Player.instance.getInven().addItem(rewardItem);
        }
    }
}
