package com.mygdx.game.command;

public class CommandManangement{
    // singleTon [Non-Safely multi Thread]
    public static CommandManangement instance = new CommandManangement();
    private CommandManangement() {}

    public PlayCommandList commandList;
    public CommandType commandType;

}
