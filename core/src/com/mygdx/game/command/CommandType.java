package com.mygdx.game.command;

public enum CommandType {
    //==Error==
    NONE,
    WRONG,

    //==Selection==
    YES,
    NO,

    //==Info Command==
    INFO_ALL,
    INFO_INVENTORY,
    INFO_ITEM,
    INFO_SKILL,

    //==Map==
    MAPINFO,
    MOVE_BACK,
    MOVE_TO_FIRST,
    MOVE_TO_SECOND,

    //==Inventory==
    INVENTORY_ITEM_EQUIP,
    INVENTORY_ITEM_UNEQUIP,

    //==Skill
    //==BATTLE
    ATTACK,
    //==ITEM==

    //==Player==
    STATUS,
    EQUIPT_ITEM,
}
