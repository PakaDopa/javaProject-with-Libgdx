package com.mygdx.game.player;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.textbox.TextBox;
import com.mygdx.game.utils.Global;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Player{
    protected Inventory inventory;
    protected Map<String, Float> status;
    protected List<Component> containers;
    public static Player instance = new Player();
    private Player()
    {

        status = new HashMap<>();

        status.put(PlayerStatus.HP.getKey(), Global.PLAYER_MAX_HP);
        status.put(PlayerStatus.MP.getKey(), Global.PLAYER_MAX_MP);
        status.put(PlayerStatus.DEFENSE.getKey(), Global.PLAYER_DEFENSE);
        status.put(PlayerStatus.DAMAGE.getKey(), Global.PLAYER_DEFAULT_DAMAGE);
        status.put(PlayerStatus.SKILL_DAMAGE.getKey(), Global.PLAYER_DEFAULT_DAMAGE);
        status.put(PlayerStatus.COMMAND_DELAY.getKey(), Global.PLAYER_COMMAND_DELAY);

        inventory = new Inventory(this);
        containers.add(inventory);
    }
    public void setStatus(String key, float value)
    {
        float currentValue = status.get(key);
        float sum = currentValue + value;
        if(PlayerStatus.HP.getKey().equals(key))
            sum = MathUtils.clamp(sum, 0, Global.PLAYER_MAX_HP);
        else if(PlayerStatus.MP.getKey().equals(key))
            sum = MathUtils.clamp(sum, 0, Global.PLAYER_MAX_MP);
        String sign = "";
        if(value >= 0)
            sign = "+";
        else
            sign = "";

        String token =
                "     " + key + ":" + currentValue + " -> " + sum + "  " + sign + value;
        TextBox.instance.setDirect(token);
        status.put(key, sum);
    }
    public void showStatus()
    {
        Iterator<String> keys = status.keySet().iterator();
        while(keys.hasNext())
        {
            String key = keys.next();

            TextBox.instance.setDirect("    " + key + ":" + status.get(key));
        }
        TextBox.instance.setDirect("");
    }
    public float getStatus(String key)
    {
        return this.status.get(key);
    }
    public Inventory getInven()
    {
        return inventory;
    }
}
