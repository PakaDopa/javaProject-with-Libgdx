package com.mygdx.game.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.base.BaseEnemy;
import com.mygdx.game.event.Compensation.Result;
import com.mygdx.game.event.Compensation.item.ItemFactory;
import com.mygdx.game.player.Player;
import com.mygdx.game.textbox.TextBox;
import com.mygdx.game.utils.Global;
import org.w3c.dom.Text;

class Murloc extends BaseEnemy
{
    float deltaTime = 0;
    public Murloc()
    {
        super("멀록", 10,100, 10, 4.0f);
        setResult(new Result(ItemFactory.getItem("Sword")));
        setAnimationRegion("Enemy/Enemy01.gif");
    }
    @Override
    public void pattern() {
        deltaTime += Gdx.graphics.getDeltaTime();
        if(deltaTime >= attackDelay)
        {
            deltaTime = 0;
            TextBox.instance.setDirect("!=!=!");
            TextBox.instance.setDirect(name + "의 손톱에 의해 체력이 " + damage + "만큼 감소했다!!");
            TextBox.instance.setDirect("");
            Player.instance.setStatus("체력", -damage);
        }
    }
}
class ChickenSkewer extends BaseEnemy
{
    public ChickenSkewer() {
        super("닭꼬치", 10,120, 10, 3.0f);
        setResult(new Result(ItemFactory.getItem("OldShield")));
    }

    @Override
    public void pattern() {
        //패턴
    }
}
