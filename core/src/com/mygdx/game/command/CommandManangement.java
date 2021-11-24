package com.mygdx.game.command;

import com.mygdx.game.stage.StageManangerment;

import java.util.HashMap;
import java.util.Map;

public class CommandManangement{
    private Map<String, CommandType> commandMap = new HashMap<>();
    // singleTon [Non-Safely multi Thread]
    public static CommandManangement instance = new CommandManangement();
    private CommandManangement() {
        addCommand("ERROR", CommandType.WRONG);

        addCommand("/?", CommandType.INFO_ALL);
        addCommand("/inventory ?", CommandType.INFO_INVENTORY);
        addCommand("/skill ?", CommandType.INFO_SKILL);

        addCommand("/yes", CommandType.YES);
        addCommand("/no", CommandType.NO);

        addCommand("/move to 1", CommandType.MOVE_TO_FIRST);
        addCommand("/move to 2", CommandType.MOVE_TO_SECOND);
        addCommand("/move back", CommandType.MOVE_BACK);
    }

    public void addCommand(String command, CommandType type)
    {
        commandMap.put(command, type);
        /*
        * /?
        * /inventory
        * /use
        * /move to 1
        * /move to 2
        * /move back
        * /item use "~"
        * /skill use "~"
        * /skill
        * /*/
    }

    public CommandType parsingCommandType(String rowCommandText)
    {
        if(commandMap.containsKey(rowCommandText))
            return commandMap.get(rowCommandText);
        else{
            //없는 커맨드
            return commandMap.get("ERROR");
        }
    }
}
