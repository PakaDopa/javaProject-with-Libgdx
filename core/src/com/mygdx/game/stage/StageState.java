package com.mygdx.game.stage;

import com.mygdx.game.event.Event;
import com.mygdx.game.event.type.EventType;

public enum StageState implements StageStateInterface{
    INIT{
        @Override
        public StageState update(StageNode node, float dt) {
            System.out.println("START EVENT");
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
            return ING;
        }
    },
    END{
        Event rest = Event.REST;
        @Override
        public StageState update(StageNode node, float dt) {
            if(!isOneTime)
            {
                isOneTime = true;
                rest.create(node);
            }
            EventType result = rest.update(dt);
            if(result == EventType.MOVE_BACK){
                isOneTime = false;
                return MOVE_BACK;
            }
            else if(result == EventType.MOVE_LEFT){
                isOneTime = false;
                return MOVE_LEFT;
            }
            else if(result == EventType.MOVE_RIGHT){
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
    };


    boolean isOneTime = false;
}
