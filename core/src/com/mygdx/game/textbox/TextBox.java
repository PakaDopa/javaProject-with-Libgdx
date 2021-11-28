package com.mygdx.game.textbox;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.sound.SoundMananger;
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
            @Override
            public void onChar(Character ch) {
                Sound effectSound = SoundMananger.instance.randomPick();
                effectSound.play();
            }
        });

        stage.addActor(text);
    }
    private TextBox() {

        stage = new Stage(new FitViewport(Global.VIEW_WIDTH, Global.VIEW_HEIGHT));
        log = new Label("", Global.LABELSTYLE);
        initTypingLabel();

        textBox = new ScrollPane(log);
        textBox.setFlickScroll(false);
        textBox.setForceScroll(true, false);
        textBox.setPosition(15,80);
        textBox.setWidth(420);
        textBox.setHeight(225);
        //textBox.set

        stage.addActor(textBox);
        stage.addActor(text);
    }
    private void setPrintState(PrintState state) {this.state = state;}
    public void setText(String token)
    {
        //아래 연출바에 텍스트가 TypingLabel처럼 나오게 됨.
        //State Setting
        setPrintState(PrintState.TEXTING);
        token += "\n";

        //Init
        initTypingLabel();

        //logic
        /*
        * 1. 아래 연출바에 현재 값 token으로 셋팅해준다.
        * 2. 이전에 올아와있던 버퍼를 log로 셋팅해준다.
        * 3. 버퍼에 지금 연출 바에 올려진 token을 더해준다.
        * */
        text.setText(token);
        log.setText(buffer);
        buffer += token;
        textBox.scrollTo(0, 0, 0, 0);
    }
    public void setDirect(String token)
    {
        setPrintState(PrintState.TEXTING);
        token += "\n";

        //logic
        /*
        * 1. 버퍼에 바로 token을 더해줌
        * 2. 연출 타이핑 라벨은 "" 처리해줌.
        * 3. 텍스트 박스도 "" 처리해줌
        * 4. log에 바로 뿌러줌.
        * */
        buffer += token;
        text.setText("");
        TextInputBox.instance.setText("");
        log.setText(buffer);
    }
    public boolean isEndPrinting() {return state == PrintState.TEXTING_END;}
    public void render(float dt)
    {
        textBox.scrollTo(0, 0, 0, 0);
        stage.draw();
        stage.act(dt);
    }
}
