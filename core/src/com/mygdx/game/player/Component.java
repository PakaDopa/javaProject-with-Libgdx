package com.mygdx.game.player;

import java.util.ArrayList;
import java.util.List;

public abstract class Component{
    public Component parent;
    protected List<Component> containers;

    public Component(Component parent)
    {
        containers = new ArrayList<>();
        this.parent = parent;
    }
    public void add(Component object)
    {
        containers.add(object);
    }
    public void remove(Component object)
    {
        containers.remove(object);
    }
}
