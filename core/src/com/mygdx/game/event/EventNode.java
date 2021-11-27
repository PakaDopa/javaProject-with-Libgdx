package com.mygdx.game.event;

import com.mygdx.game.event.Compensation.Result;
import com.mygdx.game.event.type.EventType;

public class EventNode{

    public String token;
    public EventType eventType;
    public EventNode leftNode;
    public EventNode rightNode;

    //===
    public Result result;

    public EventNode(String token, EventType eventType)
    {
        this.leftNode = null;
        this.rightNode = null;
        this.token = token;
        this.eventType = eventType;
    }

    public EventNode(String token, EventType eventType, Result result)
    {
        this.leftNode = null;
        this.rightNode = null;
        this.token = token;
        this.eventType = eventType;
        this.result = result;
    }

    public void link(EventNode node)
    {
        leftNode = node;
    }
    public void link(EventNode leftNode, EventNode rightNode)
    {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }
}
