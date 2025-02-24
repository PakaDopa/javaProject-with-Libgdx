package com.mygdx.game.stage;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.event.Event;
import com.mygdx.game.event.type.EventType;
import com.mygdx.game.textbox.TextBox;

import java.util.Timer;

public enum StageState implements StageStateInterface{
    INIT{
        @Override
        public StageState update(StageNode node, float dt) {
            TextBox.instance.setDirect("========================[" + node.stageDeath + "-" + node.stageDirection + "]=========================");
            if(node.isVisit)
                return END;
            node.isVisit = true;
            node.getEvent().create(node);
            return ING;
        }
    },
    ING{
        @Override
        public StageState update(StageNode node, float dt) {
            EventType result = node.getEvent().update(dt);
            if(result == EventType.DONE_END)
            {
                System.out.println("END EVENT");
                return END;
            }
            if(result == EventType.PLAYER_DIE)
            {
                System.out.println("END EVENT");
                return PLAYER_DIE;
            }
            return ING;
        }
    },
    END{
        Event rest = Event.REST; //커맨드 존
        @Override
        public StageState update(StageNode node, float dt) {
            if(!isOneTime)
            {
                isOneTime = true;
                rest.create(node);
                TextBox.instance.setDirect("===================[COMMAND_ZONE]========================");
            }
            EventType result = rest.update(dt);
            if(result == EventType.MOVE_BACK)
            {
                isOneTime = false;
                return MOVE_BACK;
            }
            else if(result == EventType.MOVE_LEFT)
            {
                isOneTime = false;
                return MOVE_LEFT;
            }
            else if(result == EventType.MOVE_RIGHT)
            {
                isOneTime = false;
                return MOVE_RIGHT;
            }
            return END;
        }
    },
    MOVE_BACK {
        @Override
        public StageState update(StageNode node, float dt) {
            System.out.println(node.event.getClass().getName());
            return this;
        }
    },
    MOVE_LEFT {
        @Override
        public StageState update(StageNode node, float dt) {
            System.out.println(node.event.getClass().getName());
            return this;
        }
    },
    MOVE_RIGHT {
        @Override
        public StageState update(StageNode node, float dt) {
            System.out.println(node.event.getClass().getName());
            return this;
        }
    },
    PLAYER_DIE
    {
        @Override
        public StageState update(StageNode node, float dt)
        {
            if(!isOneTime) {
                isOneTime = true;
                TextBox.instance.setDirect("===========================");
                TextBox.instance.setDirect("...여기서 끝이군..");
                TextBox.instance.setText("system: 플레이어의 체력이 0 아래로 떨어졌습니다.");
                TextBox.instance.setText("");
                TextBox.instance.setText("system: ..게임을 종료합니다.");
                TextBox.instance.setDirect("===========================");
            }
            gamesetTimer += Gdx.graphics.getDeltaTime();
            if(gamesetTimer >= 3.0f)
                Gdx.app.exit();

            return this;
        }
    };

    float gamesetTimer = 0.0f;
    boolean isOneTime = false;
}
