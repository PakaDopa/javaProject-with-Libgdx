package com.mygdx.game.event;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.command.CommandManangement;
import com.mygdx.game.command.CommandType;
import com.mygdx.game.event.state.EventState;
import com.mygdx.game.textbox.TextBox;
import com.mygdx.game.textbox.TextInputBox;

public class EventProcedure {

    Event parentEvent;
    String token;
    CommandType[] needCommandType;

    public EventProcedure(Event parentEvent, String token, CommandType... needCommandType)
    {
        this.parentEvent = parentEvent;
        this.needCommandType = needCommandType;
        this.token = token;
        TextBox.instance.setTextState(EventState.TEXTING_START);
    }

    public CommandType render(float dt)
    {
        switch(TextBox.instance.getTextState())
        {
            case TEXTING_START:
                System.out.println(TextBox.instance.getTextState());

                TextBox.instance.setText(token);
                TextBox.instance.setTextState(EventState.TEXTING);
                break;
            case TEXTING:
                System.out.println(TextBox.instance.getTextState());
                break;
            case TEXTING_END:
                System.out.println(TextBox.instance.getTextState());
                if(needCommandType[0] != CommandType.NONE)
                {
                    TextInputBox.instance.setText("");
                    TextInputBox.instance.setVisible(true);
                    TextBox.instance.setTextState(EventState.INPUT);
                }
                else {
                    System.out.println(TextBox.instance.getTextState());
                    TextBox.instance.setTextState(EventState.TEXTING_START);

                    return CommandType.NONE;
                }
                break;
            case INPUT:
                System.out.println(TextBox.instance.getTextState());
                if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
                {
                    String inputData = TextInputBox.instance.getText();
                    TextInputBox.instance.setText("");
                    CommandType parsingResult = CommandManangement.instance.parsingCommandType(inputData);
                    System.out.println(parsingResult);

                    TextInputBox.instance.setVisible(false);
                    for(int i = 0; i < needCommandType.length; i++)
                        if(parsingResult == needCommandType[i])
                            return parsingResult;
                    TextInputBox.instance.setVisible(false);
                    TextBox.instance.setText("{FASTER}옳바른 명령어를 입력하세요. '/yes', '/no' {ENDSICK}\n");
                    TextBox.instance.setTextState(EventState.TEXTING);

                }
        }
        return CommandType.WRONG;
    }
}
