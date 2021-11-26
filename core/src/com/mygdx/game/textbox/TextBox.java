package com.mygdx.game.textbox;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.textbox.state.PrintState;
import com.mygdx.game.utils.Global;
import com.rafaskoberg.gdx.typinglabel.TypingAdapter;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;

public class TextBox {
    public Stage stage;

    protected String buffer = "";
    protected TypingLabel text;
    protected Label log;
    protected ScrollPane textBox;
    protected PrintState state;

    public static TextBox instance = new TextBox();
    private void initTypingLabel()
    {
        if(text != null) {
            for (int i = 0; i < stage.getActors().items.length; i++) {
                if (text == stage.getActors().items[i]) {
                    stage.getActors().items[i].remove();
                }
            }
        }
        text = new TypingLabel("", Global.LABELSTYLE);
        text.setPosition(22,50);
        text.setTypingListener(new TypingAdapter() {
            @Override
            public void end() {
                setPrintState(PrintState.TEXTING_END);
            }
        });

        stage.addActor(text);
    }
    private TextBox() {

        stage = new Stage(new FitViewport(Global.VIEW_WIDTH, Global.VIEW_HEIGHT));
        log = new Label("", Global.LABELSTYLE);
        initTypingLabel();

        textBox = new ScrollPane(log);
        textBox.setFlickScroll(true);
        textBox.setPosition(15,80);
        textBox.setWidth(420);
        textBox.setHeight(225);

        stage.addActor(textBox);
        stage.addActor(text);
    }
    private void setPrintState(PrintState state) {this.state = state;}
    public void setText(String token)
    {
        setPrintState(PrintState.TEXTING);
        token += "\n";

        initTypingLabel();

        text.setText(token);
        log.setText(buffer);
        buffer += token;
    }
    public void setDirect(String token)
    {
        setPrintState(PrintState.TEXTING);

        token += "\n";
        buffer += token;
        text.setText("");
        TextInputBox.instance.setText("");
        log.setText(buffer);
    }
    public boolean isEndPrinting() {return state == PrintState.TEXTING_END;}
    public void render(float dt)
    {
        stage.draw();
        stage.act(dt);
    }
}
