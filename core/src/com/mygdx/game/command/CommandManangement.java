package com.mygdx.game.command;

import com.mygdx.game.stage.StageNode;
import com.mygdx.game.textbox.TextBox;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CommandManangement{
    //==========
    public final String itemUseCommand = "/inventory use ";
    public final String itemInfoCommand = "/item info ";

    private Map<String, CommandType> commandMap = new HashMap<>();
    // singleTon [Non-Safely multi Thread]
    public static CommandManangement instance = new CommandManangement();
    private CommandManangement() {
        addCommand("ERROR", CommandType.WRONG);
        addCommand("/?", CommandType.INFO_ALL);

        //SELECT
        addCommand("/yes", CommandType.YES);
        addCommand("/no", CommandType.NO);

        //MAP
        addCommand("/move info", CommandType.MAPINFO);
        addCommand("/move to 1", CommandType.MOVE_TO_FIRST);
        addCommand("/move to 2", CommandType.MOVE_TO_SECOND);
        addCommand("/move back", CommandType.MOVE_BACK);

        //INVENTORY
        addCommand("/inventory ?", CommandType.INFO_INVENTORY);
        addCommand("/inventory take off weapon", CommandType.INVENTORY_ITEM_UNEQUIP);
        addCommand("/inventory take off armor", CommandType.INVENTORY_ITEM_UNEQUIP);

        //SKILL
        addCommand("/skill ?", CommandType.INFO_SKILL);

        //PLAYER
        addCommand("/player status", CommandType.STATUS);
        addCommand("/player equip", CommandType.EQUIPT_ITEM);

        //BATTLE
        addCommand("/attack", CommandType.ATTACK);
    }

    public void addCommand(String command, CommandType type)
    {
        commandMap.put(command, type);
    }
    public void showCommand(CommandType type)
    {
        String[] token = new String[commandMap.size()];
        int ind = 0;
        Iterator<String> keys = commandMap.keySet().iterator();

        while(keys.hasNext())
        {
            String key = keys.next();
            switch(type)
            {
                case INFO_ALL:
                    token[ind++] = key;
                    break;
                case INFO_INVENTORY:
                    if(key.contains("inventory")) token[ind++] = key;
                    break;
                case INFO_SKILL:
                    if(key.contains("skill")) token[ind++] = key;
                    break;
            }
        }
        Arrays.sort(token);
        for(int i = 0; i < token.length; i++){
            if(token[i].contains("ERROR") || token[i].contains("yes") || token[i].contains("no"))
                continue;
            TextBox.instance.setDirect(token[i]);
        }
    }
    public void showStageInfo(StageNode stageNode)
    {
        System.out.println("=========================" + stageNode.hashCode());
        String token = "System: 가능한 커맨드는\n";
        if(stageNode.parentNode != null)
            token += "    돌아가기 '/move back'\n";
        if(stageNode.leftNode != null)
            token += "    왼쪽 길 '/move to 1'\n";
        if(stageNode.rightNode != null)
            token += "    오른쪽 길 '/move to 2'\n";
        TextBox.instance.setDirect(token);
    }
    public void addItemAddCommand(String name)
    {
        commandMap.put(String.format(itemUseCommand + "%s", name), CommandType.INVENTORY_ITEM_EQUIP);
        commandMap.put(String.format(itemInfoCommand + "%s", name), CommandType.INFO_ITEM);
        //commandMap.put(String.format(itemUnUseCommand + "%s", name), CommandType.INVENTORY_ITEM_UNEQUIP);
    }
    public void removeItemAddCommand(String name)
    {
        String key = String.format(itemUseCommand + "%s", name);
        String key2 = String.format(itemInfoCommand + "%s", name);
        commandMap.remove(key);
        commandMap.remove(key2);
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
