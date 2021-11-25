package com.mygdx.game.event;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.command.CommandType;
import com.mygdx.game.textbox.TextBox;

public enum Event implements EventInterface {

    STAGE_ROOT
    {
        String token, token_yes, token_no;
        @Override
        public void create()
        {
            subEvents = new EventProcedure[1];
            token =
                    "{FASTER}여긴 코딩 지옥이야..\n날 죽여주라...\n->커맨드를 입력하세요.{SICK}'/yes', '/no'{ENDSICK}\n";
            token_yes =
                    "{FASTER}그래!! 고마워.. 나를 죽여줘서.. \n" +
                            "그디어 코딩 지옥에서 벗어나게 되었네.. \n" +
                            "흐하하ㅏㅏ.. 이제 잘 수 있게 되었어. \n" +
                            "개 좆같은.. 프로젝트같으니라고.. 너도 해보면 알거야. \n" +
                            "빠빠이..! \n\n";
            token_no =
                    "{FASTER}{WAIT}........\n" +
                            "치... 너무해.. 그럼 이 프젝을 계속해야해..\n\n";

            subEvents[0] = new EventProcedure(this, token, CommandType.YES, CommandType.NO);
        }
        @Override
        public boolean update(float dt)
        {
            if(eventOver) return true;

            CommandType result = defauleUpdate(dt);
            if(ind == subEvents.length - 1)
            {
                if(result == CommandType.YES)
                {
                    TextBox.instance.setText(token_yes);
                    eventOver = true;
                    return true;
                }
                else if(result == CommandType.NO)
                {
                    TextBox.instance.setText(token_no);
                    eventOver = true;
                    return true;
                }
            }
            return false;
        }
    },
    BURNING //불이 덮쳤다. HP - 10!
    {
        //불길에 덮여서 화상을 입음.
        @Override
        public void create()
        {

        }
        @Override
        public boolean update(float dt)
        {
            return false;
        }
    },
    FIND_WATER //마나를 회복한다. MP + 10!
    {
        @Override
        public void create()
        {

        }
        @Override
        public boolean update(float dt)
        {
            return false;
        }
    },
    SELECT_MAP
    {
        String token, token_1, token_2;
        @Override
        public void create()
        {
            subEvents = new EventProcedure[1];
            token =
                "다음 스테이지를 선택하세요.\n'/move to 1', '/move to 2'\n";
            token_1 =
                "왼쪽 길로 들어섭니다..\n";
            token_2 =
                "오른쪽 길로 들어섭니다..\n";

            subEvents[0] = new EventProcedure(this, token, CommandType.MOVE_TO_FIRST, CommandType.MOVE_TO_SECOND);
        }
        @Override
        public boolean update(float dt)
        {
            if(eventOver) return true;

            CommandType result = defauleUpdate(dt);
            //Result
            if(ind == subEvents.length - 1)
            {
                if(result == CommandType.MOVE_TO_FIRST)
                {
                    TextBox.instance.setText(token_1);
                    eventOver = true;
                    return true;
                }
                else if(result == CommandType.MOVE_TO_SECOND)
                {
                    TextBox.instance.setText(token_2);
                    eventOver = true;
                    return true;
                }
            }
            return false;
        }
    };
    @Override
    public CommandType defauleUpdate(float dt)
    {
        EventProcedure eventProcedure = subEvents[ind];
        CommandType result = eventProcedure.render(dt);
        if(result == CommandType.NONE)
            ind = MathUtils.clamp(ind + 1, ind, subEvents.length - 1);
        return result;
    }

    protected EventProcedure[] subEvents;
    protected int ind = 0;
    protected boolean eventOver = false;
}
