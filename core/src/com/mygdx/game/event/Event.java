package com.mygdx.game.event;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.command.CommandType;
import com.mygdx.game.textbox.TextBox;

public enum Event implements EventInterface {

    STAGE_ROOT
    {
        @Override
        public void create()
        {
            subEvents = new EventProcedure[1];
            String token_1 = "{SPEED=0.55}안녕 거기 누구 있니..?\n" +
                "난 이 좆같은 프로젝트에 갇혀 버렸어..\n" +
                "{WAIT}좆.같..은.. 코딩 지옥에 말야..\n" +
                "\n제발.. 날 {SICK}죽여주겠니??..{ENDSICK} [/yes], [/no]  \n";

            subEvents[0] = new EventProcedure(this, token_1, CommandType.YES, CommandType.NO);
        }
        @Override
        public boolean update(float dt)
        {
            EventProcedure eventProcedure = subEvents[ind];
            CommandType result = eventProcedure.render(dt);
            if(result == CommandType.NONE)
                ind = MathUtils.clamp(ind + 1, ind, subEvents.length - 1);
            //Result
            if(ind == subEvents.length - 1)
            {
                if(result == CommandType.YES)
                {
                    String token = "{SLOWER}그래!! 고마워.. 나를 죽여줘서.. \n" +
                            "그디어 코딩 지옥에서 벗어나게 되었네.. \n" +
                            "흐하하ㅏㅏ.. 이제 잘 수 있게 되었어. \n" +
                            "개 좆같은.. 프로젝트같으니라고.. 너도 해보면 알거야. \n" +
                            "빠빠이..! \n\n";
                    TextBox.instance.setText(token);
                    return true;
                }
                else if(result == CommandType.NO)
                {
                    String token = "{SLOWER}{WAIT}........\n" +
                            "치... 너무해.. 그럼 이 프젝을 계속해야해..\n\n";
                    TextBox.instance.setText(token);
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
        @Override
        public void create()
        {

        }
        @Override
        public boolean update(float dt)
        {
            return false;
        }
    };

    protected EventProcedure[] subEvents;
    protected int ind = 0;

}
