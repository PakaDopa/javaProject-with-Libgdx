package com.mygdx.game.event;

import com.mygdx.game.utils.Pair;

public class EventNode {
    public Event currentEvent;
    public EventNode parentNode;
    public EventNode leftNode;
    public EventNode rightNode;

    public EventNode(EventNode parentNode)
    {
        //셋팅
        this.parentNode = parentNode;
        currentEvent = null;
        leftNode = null;
        rightNode = null;
    }
}
