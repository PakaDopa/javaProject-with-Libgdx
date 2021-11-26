package com.mygdx.game.event.Compensation;

import com.mygdx.game.player.Player;
import com.mygdx.game.player.PlayerStatus;

public class Result {
    String token;

    public Result(PlayerStatus status, float value)
    {
        Player.instance.setStatus(status.getInd(), value);
    }
}
