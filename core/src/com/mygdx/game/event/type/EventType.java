package com.mygdx.game.event.type;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.command.CommandManangement;
import com.mygdx.game.command.CommandType;
import com.mygdx.game.event.LineMaker;
import com.mygdx.game.textbox.TextBox;
import com.mygdx.game.textbox.TextInputBox;

public enum EventType implements EventTypeInterface{
    PRINTING("PRINTING")
    {
        @Override
        public EventType update(LineMaker line) {
            TextBox.instance.setText(line.token);
            return DONE;
        }
    },
    SELECT("SELECT")
    {
        boolean createSetting = false;
        @Override
        public EventType update(LineMaker line) {
            if(!createSetting)
            {
                TextInputBox.instance.setVisible(true);
                TextBox.instance.setDirect(line.token);
                createSetting = true;
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)
                    && TextBox.instance.isEndPrinting())
            {
                String token = TextInputBox.instance.getText();
                CommandType parserType = CommandManangement.instance.parsingCommandType(token);
                TextBox.instance.setDirect(token);

                if(parserType == line.needCommandType[0])
                {
                    //결과를 얻을 수 있음.
                    //return new EventTypeResult();
                    return DONE;
                }
                if(parserType == line.needCommandType[1])
                {
                    //결과를 얻을 수 있음.
                    //return new EventResult();
                    return DONE;
                }
                TextBox.instance.setDirect("SYSTEM: 정확한 명령어를 입력해주세요.");
            }
            return this;
        }
    },
    BATTLE("BATTLE")
    {
        @Override
        public EventType update(LineMaker line) {
            return null;
        }
    },
    RESULT("RESULT")
    {
        @Override
        public EventType update(LineMaker line) {
            return DONE;
        }
    },
    DONE("DONE")
    {
        @Override
        public EventType update(LineMaker line) {
            return this;
        }
    };

    private final String name;
    EventType(String name) {this.name = name;}
    public String getName() {return name;}
}
