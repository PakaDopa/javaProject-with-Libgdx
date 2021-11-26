package com.mygdx.game.event;

import com.mygdx.game.command.CommandType;
import com.mygdx.game.event.type.EventType;
import com.mygdx.game.event.type.EventTypeInterface;

public class LineMaker {
    public String token = "";
    public EventType type;
    public CommandType[] needCommandType;
    public LineMaker(String token, EventType type) {
        this.token = token;
        this.type = type;
    }
    public LineMaker(String token, EventType type, CommandType... commandTypes){
        this.token = token;
        this.type = type;
        needCommandType = commandTypes;
    }
    //public LineMaker(String token, EventState state, Compensation compensation, CommandType... commandTypes){}
    //public LineMaker(String token, EventState state, Enemy enemy){}

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
