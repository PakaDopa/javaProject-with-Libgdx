
package com.mygdx.game.event;

import com.mygdx.game.event.type.EventType;
import com.mygdx.game.stage.StageNode;

public interface EventInterface {
    void create(StageNode stageNode);
    EventType update(float dt);
}
