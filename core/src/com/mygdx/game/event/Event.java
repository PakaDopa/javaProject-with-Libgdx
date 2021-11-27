package com.mygdx.game.event;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.command.CommandType;
import com.mygdx.game.event.Compensation.Result;
import com.mygdx.game.event.Compensation.item.ItemFactory;
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
    FIND_BOX
    {
        @Override
        public void create() {
            Result boxResult = new Result();
            boxResult.add(ItemFactory.getItem("Sword"));

            lineMakers = new ArrayList<>();
            lineMakers.add(new LineMaker("어두운 길을 더듬어 내려가다 상자를 발견했다.", EventType.PRINTING));
            lineMakers.add(new LineMaker("어떻게 할까?? [/yes, /no]]", EventType.SELECT, boxResult));
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
