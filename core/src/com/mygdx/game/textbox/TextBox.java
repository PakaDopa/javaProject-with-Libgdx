package com.mygdx.game.textbox;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.utils.StringBuilder;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.event.state.EventState;
import com.mygdx.game.utils.Global;
import com.rafaskoberg.gdx.typinglabel.TypingAdapter;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;

public class TextBox {
    public Stage stage;

    protected EventState state;
    protected TypingLabel text;
    protected ScrollPane textBox;
    protected String log;
    public static TextBox instance = new TextBox();
    private void createTypingLabel()
    {
        text = new TypingLabel("", Global.LABELSTYLE);
        text.setTypingListener(new TypingAdapter() {
            @Override
            public void end() {
                state = EventState.TEXTING_END;
            }
        });
        textBox.setActor(text);
    }
    private TextBox() {
        stage = new Stage(new FitViewport(Global.VIEW_WIDTH, Global.VIEW_HEIGHT));

        text = new TypingLabel("", Global.LABELSTYLE);
        text.setTypingListener(new TypingAdapter() {
            @Override
            public void end() {
                state = EventState.TEXTING_END;
            }
        });
        textBox = new ScrollPane(text);
        textBox.setFlickScroll(true);
        textBox.setPosition(10,45);
        textBox.setWidth(420);
        textBox.setHeight(265);

        stage.addActor(textBox);
    }
    public void setText(String token)
    {
        createTypingLabel();
        text.setText(token);
        log = text.getText().toString();
    }
    public void setTextState(EventState state)
    {
        this.state = state;
    }
    public void setDefault()
    {
        setTextState(EventState.TEXTING_START);
    }
    public EventState getTextState()
    {
        return state;
    }
    public void render(float dt)
    {
        stage.draw();
        stage.act(dt);
    }




}
