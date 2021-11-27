package com.mygdx.game.stage;

import com.mygdx.game.textbox.TextBox;

public enum StageState implements StageStateInterface{
    INIT{
        @Override
        public StageState update(StageNode node, float dt) {
            System.out.println("START EVENT");
            node.getEvent().create();
            return ING;
        }
    },
    ING{
        @Override
        public StageState update(StageNode node, float dt) {
            boolean result = node.getEvent().update(dt);
            if(result)
            {
                System.out.println("END EVENT");
                return END;
            }
            return ING;
        }
    },
    END{
        @Override
        public StageState update(StageNode node, float dt) {

            return END;
        }
    }
}
