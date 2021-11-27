package com.mygdx.game.player;

import com.mygdx.game.textbox.TextBox;
import com.mygdx.game.utils.Global;

import java.util.HashMap;
import java.util.Map;

public class Player extends Component{
    protected Inventory inventory;
    protected Map<String, Float> status;
    public static Player instance = new Player();
    private Player()
    {
        super(null); //최상위 클래스

        status = new HashMap<>();

        status.put(PlayerStatus.HP.getKey(), Global.PLAYER_MAX_HP);
        status.put(PlayerStatus.MP.getKey(), Global.PLAYER_MAX_MP);
        status.put(PlayerStatus.DAMAGE.getKey(), Global.PLAYER_DEFENSE);
        status.put(PlayerStatus.DAMAGE.getKey(), Global.PLAYER_DEFAULT_DAMAGE);
        status.put(PlayerStatus.SKILL_DAMAGE.getKey(), Global.PLAYER_DEFAULT_DAMAGE);
        status.put(PlayerStatus.SKILL_DAMAGE.getKey(), Global.PLAYER_COMMAND_DELAY);

        inventory = new Inventory(this);
        containers.add(inventory);

        //containers.add(new Skill(this));
    }
    public void setStatus(String key, float value)
    {
        float currentValue = status.get(key);
        float sum = currentValue + value;
        String token =
                "     " + key + ":" + currentValue + " -> " + sum;
        TextBox.instance.setDirect(token);
        status.put(key, sum);
    }
    public Inventory getInven()
    {
        return inventory;
    }
}
