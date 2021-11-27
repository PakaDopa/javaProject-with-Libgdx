package com.mygdx.game.event.Compensation;

import com.mygdx.game.event.Compensation.item.BaseItem;
import com.mygdx.game.player.Player;
import com.mygdx.game.player.PlayerStatus;
import com.mygdx.game.utils.Pair;

import java.util.ArrayList;
import java.util.List;

public class Result {

    List<Pair<PlayerStatus, Float>> statusList;
    List<BaseItem> itemList;

    public Result()
    {
        statusList = new ArrayList<>();
        itemList = new ArrayList<>();
    }

    public void add(Pair<PlayerStatus, Float> element)
    {
        statusList.add(element);
    }
    public void add(BaseItem item)
    {
        itemList.add(item);
    }
    public List<Pair<PlayerStatus, Float>> getStatusList()
    {
        return statusList;
    }
    public List<BaseItem> getItemList()
    {
        return itemList;
    }
}
