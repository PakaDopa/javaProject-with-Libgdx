package com.mygdx.game.player;

public enum PlayerStatus {
    MAXHP("최대 체력"),
    MAXMP("최대 마나"),
    HP("체력"),
    MP("마나"),
    DEFENSE("방어력"),
    DAMAGE("공격력"),
    SKILL_DAMAGE("스킬 공격력"),
    COMMAND_DELAY("커맨드 지연 시간");

    private final String key;
    PlayerStatus(String key) {this.key = key;}
    public String getKey() {return key;}
}
