package com.mygdx.game.event;

import com.mygdx.game.base.BaseEnemy;
import com.mygdx.game.event.Compensation.Result;
import com.mygdx.game.event.type.EventType;
import com.mygdx.game.stage.StageNode;

public class EventNode{

    public String token;
    public StageNode stageData;
    public EventType eventType;
    public EventNode leftNode;
    public EventNode rightNode;
    public boolean isVisit = false;

    //===
    public Result result;
    public BaseEnemy enemy;

    public EventNode(String token, EventType eventType)
    {
        this.leftNode = null;
        this.rightNode = null;
        this.token = token;
        this.eventType = eventType;
    }
    public EventNode(String token, EventType eventType, StageNode stageData)
    {
        this.leftNode = null;
        this.rightNode = null;
        this.stageData = stageData;
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
    public EventNode(String token, EventType eventType, BaseEnemy enemy)
    {
        this.leftNode = null;
        this.rightNode = null;
        this.token = token;
        this.eventType = eventType;
        this.enemy = enemy;
    }

    public void link(EventNode node)
    {
        leftNode = node;
        //leftNode.stageData = stageData;
    }
    public void link(EventNode leftNode, EventNode rightNode)
    {
        this.leftNode = leftNode;
        //this.leftNode.stageData = stageData;
        this.rightNode = rightNode;
        //this.rightNode.stageData = stageData;
    }
}
