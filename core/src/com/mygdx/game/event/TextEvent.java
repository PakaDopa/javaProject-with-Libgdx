package com.mygdx.game.event;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.command.CommandManangement;
import com.mygdx.game.command.CommandType;
import com.mygdx.game.utils.Global;
import com.rafaskoberg.gdx.typinglabel.TypingAdapter;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;

public class TextEvent {

    Event parentEvent;
    String token;
    CommandType needCommandType;

    TypingLabel text;
    EventState state;
    boolean is_create = false;

    public TextEvent(Event parentEvent, CommandType needCommandType, String token)
    {
        this.parentEvent = parentEvent;
        this.needCommandType = needCommandType;
        this.token = token;
        state = EventState.TEXTING_START;
    }
    public void create(String token)
    {
        parentEvent.log += token;
        if(text == null) {
            text = new TypingLabel(parentEvent.log, Global.LABELSTYLE);
            text.setTypingListener(new TypingAdapter() {
                @Override
                public void end() {
                    parentEvent.ind = MathUtils.clamp(parentEvent.ind + 1, parentEvent.ind, parentEvent.subEvents.length - 1);
                    System.out.println(parentEvent.subEvents.length - 1);
                    state = EventState.TEXTING_END;
                }
            });
            parentEvent.textBox.setActor(text);
        }
        else
            text.setText(parentEvent.log);
        is_create = true;
    }
    public boolean render(float dt)
    {
        switch(state)
        {
            case TEXTING_START:
                System.out.println(state);
                if(!is_create)
                    create(token);
                state = EventState.TEXTING;
                break;
            case TEXTING:
                System.out.println(state);
                break;
            case TEXTING_END:
                System.out.println(state);
                if(needCommandType != CommandType.NONE)
                {
                    parentEvent.textField.setText("");
                    parentEvent.textField.setVisible(true);
                    state = EventState.INPUT;
                }
                else {
                    System.out.println(state);
                    state = EventState.TEXTING_START;
                    return true;
                }
                break;
            case INPUT:
                System.out.println(state);
                if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
                {
                    String inputData = parentEvent.textField.getText();
                    parentEvent.textField.setText("");

                    CommandType parsingResult = CommandManangement.instance.parsingCommandType(inputData);
                    System.out.println(parsingResult);
                    if(parsingResult == needCommandType)
                    {
                        parentEvent.textField.setVisible(false);
                        return true;
                    }
                    else
                    {
                        parentEvent.textField.setVisible(false);
                        create("{SICK}옳바른 명령어를 입력해주세요 '/yes', '/no'  \n");
                        state = EventState.TEXTING;
                    }
                }
        }
        return false;
    }
}
