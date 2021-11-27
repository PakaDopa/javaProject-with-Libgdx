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
    INFO_SKILL,

    //==Map==
    MOVE_BACK,
    MOVE_TO_FIRST,
    MOVE_TO_SECOND,

    //==Inventory==
    INVENTORY_ITEM_ADD,
    INVENTORY_ITEM_REMOVE,
    INVENTORY_ITEM_EQUIP,
    INVENTORY_ITEM_UNEQUIP,
}
