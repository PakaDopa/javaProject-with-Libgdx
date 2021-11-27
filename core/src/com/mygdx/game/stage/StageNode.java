package com.mygdx.game.stage;

import com.mygdx.game.event.Event;

public class StageNode {
    public StageNode parentNode;
    protected Event event;
    public StageNode leftNode;
    public StageNode rightNode;

    public StageNode(StageNode parentNode)
    {
        this.parentNode = parentNode;
    }
    public void setEvent(Event event)
    {
        this.event = event;
    }
    public Event getEvent() {return this.event;}
    public void linkLeft(StageNode leftNode)
    {
        this.leftNode = leftNode;
    }
    public void linkRight(StageNode rightNode)
    {
        this.rightNode = rightNode;
    }

}
