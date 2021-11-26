package com.mygdx.game.player;

import com.mygdx.game.utils.Global;

import java.util.List;

public class Player extends Component{
    protected float[] status;
    public static Player instance = new Player();
    private Player()
    {
        super(null); //최상위 클래스

        status = new float[10];
        status[PlayerStatus.HP.getInd()] = Global.PLAYER_MAX_HP;
        status[PlayerStatus.MP.getInd()] = Global.PLAYER_MAX_MP;
        status[PlayerStatus.DAMAGE.getInd()] = Global.PLAYER_DEFAULT_DAMAGE;

        containers.add(new Inventory(this));
        //containers.add(new Skill(this));
    }

    public void setStatus(Integer ind, float value)
    {
        status[ind] += value;
    }
}
