package com.mygdx.game.event;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.command.CommandType;
import com.mygdx.game.event.type.EventType;
import com.mygdx.game.event.type.EventTypeInterface;
import com.mygdx.game.textbox.TextBox;

import java.util.ArrayList;

public enum Event implements EventInterface {

    STAGE_ROOT
    {
        @Override
        public void create()
        {
            lineMakers = new ArrayList<>();
            lineMakers.add(new LineMaker("???:흠.. 못보던 사람인데? 어디 사람이지?..", EventType.PRINTING));
            lineMakers.add(new LineMaker("???:그대는 무슨 일을 하는 사람인가?..", EventType.PRINTING));
            lineMakers.add(new LineMaker("???:위험한 곳이니 조심하는 것이 좋을 걸세..", EventType.PRINTING));
            lineMakers.add(new LineMaker("???:안쪽으로 들어가는건 권하지 않는데..", EventType.PRINTING));
            lineMakers.add(new LineMaker("???:왜냐니!! 당연히 위험하기 때문이지", EventType.PRINTING));
            lineMakers.add(new LineMaker("???:..그래서 들어가겠는가? [/yes, /no] ", EventType.SELECT, CommandType.YES, CommandType.NO));
        }
    },
    BURNING //불이 덮쳤다. HP - 10!
    {
        //불길에 덮여서 화상을 입음.
        @Override
        public void create()
        {

        }
    },
    FIND_WATER //마나를 회복한다. MP + 10!
    {
        @Override
        public void create()
        {

        }
    },
    SELECT_MAP
    {
        String token, token_1, token_2;
        @Override
        public void create()
        {
        }
    };

    @Override
    public boolean update(float dt)
    {
        LineMaker lineMaker = lineMakers.get(ind);
        EventType type = lineMaker.update();
        if(lineMaker.isDone(type))
        {
            lineMakers.get(ind).setDone();
            if(TextBox.instance.isEndPrinting())
                ind = MathUtils.clamp(ind + 1, ind, lineMakers.size() - 1);

            if(ind == lineMakers.size() - 1)
                return true;
        }
        return false;
    }
    protected int ind = 0;
    protected ArrayList<LineMaker> lineMakers;
    protected boolean eventOver = false;
}
