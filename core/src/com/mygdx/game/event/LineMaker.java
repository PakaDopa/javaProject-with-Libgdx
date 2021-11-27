package com.mygdx.game.event;

import com.mygdx.game.command.CommandType;
import com.mygdx.game.event.Compensation.Result;
import com.mygdx.game.event.type.EventType;
import com.mygdx.game.event.type.EventTypeInterface;

import javax.sound.sampled.Line;

public class LineMaker {
    public String token = "";
    public EventType type;
    public CommandType[] needCommandType;
    public Result result;

    public LineMaker(String token, EventType type) {
        this.token = token;
        this.type = type;
    }
    public LineMaker(String token, EventType type, CommandType... commandTypes){
        this.token = token;
        this.type = type;
        needCommandType = commandTypes;
    }
    public LineMaker(String token, EventType type, Result result, CommandType... commandTypes){
        this.token = token;
        this.type = type;
        this.result = result;
        needCommandType = commandTypes;
    }
    public LineMaker(String token, EventType type, Result result){
        this.token = token;
        this.type = type;
        this.result = result;
    }
    //public LineMaker(String token, EventType type, Enemy enemy, CommandType... commandTypes){}

    public EventType update()
    {
        if(type == null) return null;
        return type.update(this);
    }
    public boolean isDone(EventType type)
    {
        return (type.getName().equals(EventType.DONE.getName()));
    }
    public void setDone()
    {
        type = EventType.DONE;
    }
}
