
package com.mygdx.game.event;

import com.mygdx.game.command.CommandType;

public interface EventInterface {
    void create();
    boolean update(float dt);
}
