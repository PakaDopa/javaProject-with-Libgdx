package com.mygdx.game.event.type;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.command.CommandManangement;
import com.mygdx.game.command.CommandType;
import com.mygdx.game.event.EventNode;
import com.mygdx.game.player.Player;
import com.mygdx.game.textbox.TextBox;
import com.mygdx.game.textbox.TextInputBox;

public enum EventType implements EventTypeInterface{
    PRINTING("PRINTING")
    {
        @Override
        public EventType update(EventNode currentNode) {
            if(!isOneTime) {
                TextBox.instance.setText(currentNode.token);
                isOneTime = true;
            }

            if(TextBox.instance.isEndPrinting())
            {
                isOneTime = false;
                return DONE_LEFT;
            }
            else
                return DONE_YET;
        }
    },
    END_PRINTING("END_PRINTING")
    {
        @Override
        public EventType update(EventNode currentNode) {
            if(!isOneTime) {
                TextBox.instance.setText(currentNode.token);
                isOneTime = true;
            }
            if(TextBox.instance.isEndPrinting())
            {
                isOneTime = false;
                return DONE_END;
            }
            else
                return DONE_YET;
        }
    },
    SELECT("SELECT")
    {
        @Override
        public EventType update(EventNode currentNode) {
            if(!isOneTime)
            {
                TextInputBox.instance.setVisible(true);
                TextInputBox.instance.setText("");
                TextBox.instance.setDirect(currentNode.token);
                isOneTime = true;
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)
                    && TextBox.instance.isEndPrinting())
            {
                String token = TextInputBox.instance.getText();
                CommandType parserType = CommandManangement.instance.parsingCommandType(token);
                TextBox.instance.setDirect(token);

                if(parserType == CommandType.YES)
                {
                    isOneTime = false;
                    return DONE_LEFT;
                }
                if(parserType == CommandType.NO)
                {
                    isOneTime = false;
                    return DONE_RIGHT;
                }
                TextBox.instance.setDirect("SYSTEM: 정확한 명령어를 입력해주세요.");
            }
            return this;
        }
    },
    COMMAND_INPUT("COMMAND_INPUT")
    {
        @Override
        public EventType update(EventNode currentNode) {
            if(!isOneTime)
            {
                isOneTime = true;
                TextInputBox.instance.setVisible(true);
                TextInputBox.instance.setText("");
                TextBox.instance.setDirect(currentNode.token);
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)
                    && TextBox.instance.isEndPrinting())
            {
                String token = TextInputBox.instance.getText();
                CommandType parserType = CommandManangement.instance.parsingCommandType(token);
                TextBox.instance.setDirect(token);

                boolean isCorret = false;
                switch(parserType)
                {
                    case INFO_ALL:
                    case INFO_INVENTORY:
                    case INFO_SKILL:
                        isCorret = true;
                        CommandManangement.instance.showCommand(parserType);
                        break;
                    case INVENTORY_ITEM_EQUIP:
                        isCorret = true;
                        Player.instance.getInven().itemEquip(token);
                        break;
                    case INVENTORY_ITEM_UNEQUIP:
                        isCorret = true;
                        Player.instance.getInven().itemUnequip(token);
                        break;
                    case INFO_ITEM:
                        isCorret = true;
                        Player.instance.getInven().showItemInfo(token);
                        break;
                    case STATUS:
                        isCorret = true;
                        Player.instance.showStatus();
                        break;
                    case MAPINFO:
                        isCorret = true;
                        CommandManangement.instance.showStageInfo(currentNode.stageData);
                        break;
                    case MOVE_BACK:
                        if(currentNode.stageData.parentNode != null)
                        {
                            TextBox.instance.setDirect("SYSTEM: 왔던 길을 돌아갑니다.");
                            return MOVE_BACK;
                        }
                        break;
                    case MOVE_TO_FIRST:
                        if(currentNode.stageData.leftNode != null)
                        {
                            TextBox.instance.setDirect("SYSTEM: 왼쪽 길로 들어섭니다.");
                            return MOVE_LEFT;
                        }
                        break;
                    case MOVE_TO_SECOND:
                        if(currentNode.stageData.rightNode != null)
                        {
                            TextBox.instance.setDirect("SYSTEM: 오른쪽 길로 돌아섭니다.");
                            return MOVE_RIGHT;
                        }
                        break;
                }
                if(!isCorret)
                    TextBox.instance.setDirect("SYSTEM: 정확한 명령어를 입력해주세요.");
            }
            return DONE_YET;
        }
    },
    BATTLE("BATTLE")
    {
        @Override
        public EventType update(EventNode currentNode) {
            return null;
        }
    },
    RESULT("RESULT")
    {
        @Override
        public EventType update(EventNode currentNode)
        {
            TextBox.instance.setDirect(currentNode.token);
            currentNode.result.applyReward();
            return DONE_LEFT;
        }
    },
    MOVE_BACK("MOVE_BACK") {
        @Override
        public EventType update(EventNode currentNode) {
            return this;
        }
    },
    MOVE_LEFT("MOVE_LEFT") {
        @Override
        public EventType update(EventNode currentNode) {
            return this;
        }
    },
    MOVE_RIGHT("MOVE_RIGHT") {
        @Override
        public EventType update(EventNode currentNode) {
            return this;
        }
    },
    DONE_LEFT("DONE_LEFT")
    {
        @Override
        public EventType update(EventNode currentNode) {
            return this;
        }
    },
    DONE_RIGHT("DONE_RIGHT")
    {
        @Override
        public EventType update(EventNode currentNode) {
            return this;
        }
    },
    DONE_END("DONE_END")
    {
        @Override
        public EventType update(EventNode currentNode) {
            return this;
        }
    },
    DONE_YET("DONE_YET") {
        @Override
        public EventType update(EventNode currentNode) {
            return this;
        }
    };

    protected boolean isOneTime = false;
    private final String name;
    EventType(String name) {this.name = name;}
    public String getName() {return name;}
    public void setIsOneTime(boolean isOneTime) {this.isOneTime = isOneTime;}
    public boolean getIsOneTime(boolean isOneTime) {return this.isOneTime;}
}
