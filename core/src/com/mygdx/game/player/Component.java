package com.mygdx.game.player;

import java.util.ArrayList;
import java.util.List;

public abstract class Component{
    public Player parent;

    public Component(Player parent)
    {
        this.parent = parent;
    }
}
