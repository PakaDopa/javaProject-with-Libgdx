package com.mygdx.game.enemy;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.compression.lzma.Base;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.base.BaseEnemy;
import com.mygdx.game.player.Player;
import com.mygdx.game.player.PlayerStatus;
import com.mygdx.game.utils.Global;

public class BattleManagement {
    protected Stage stage;
    protected boolean isBattle = false;
    public static BattleManagement instance = new BattleManagement();
    private BattleManagement(){
        stage = new Stage(new FitViewport(Global.VIEW_WIDTH, Global.VIEW_HEIGHT));
    }

    public void update(float dt)
    {
        if(isBattle)
        {
            BaseEnemy enemy = (BaseEnemy) stage.getRoot().getChildren().items[0];
            if(enemy.isDead())
            {
                isBattle = false;
                enemy.remove();
            }
            else
            {
                if(Player.instance.getStatus(PlayerStatus.HP.getKey()) > 0)
                    enemy.pattern();
            }
        }
        stage.draw();
        stage.act(dt);
    }
    public void settingEnemy(BaseEnemy enemy)
    {
        stage.addActor(enemy);
        isBattle = true;
    }
}
