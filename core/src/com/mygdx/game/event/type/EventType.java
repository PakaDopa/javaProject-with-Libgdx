package com.mygdx.game.event.type;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.command.CommandManangement;
import com.mygdx.game.command.CommandType;
import com.mygdx.game.event.EventNode;
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
                return DONE_LEFT;
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
                return DONE_END;
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
                    return DONE_LEFT;
                }
                if(parserType == CommandType.NO)
                {
                    return DONE_RIGHT;
                }
                TextBox.instance.setDirect("SYSTEM: 정확한 명령어를 입력해주세요.");
            }
            return this;
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
            if(!isOneTime)
            {
                isOneTime = true;
                TextBox.instance.setDirect(currentNode.token);
                currentNode.result.applyReward();
            }
            return DONE_LEFT;
        }
    },
    DONE_LEFT("DONE_LEFT")
    {
        @Override
        public EventType update(EventNode currentNode) {
            return DONE_LEFT;
        }
    },
    DONE_RIGHT("DONE_RIGHT")
    {
        @Override
        public EventType update(EventNode currentNode) {
            return DONE_RIGHT;
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
