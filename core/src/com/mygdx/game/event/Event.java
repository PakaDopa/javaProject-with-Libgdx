package com.mygdx.game.event;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.command.CommandType;
import com.mygdx.game.utils.Global;
import com.rafaskoberg.gdx.typinglabel.TypingAdapter;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;

public enum Event implements EventInterface, InputProcessor {

    STAGE_ROOT
    {
        @Override
        public void create()
        {
            stage = new Stage(new FitViewport(Global.VIEW_WIDTH, Global.VIEW_HEIGHT));
            defaultCreate();

            subEvents = new TextEvent[1];
            String token_1 = "안녕\n" +
                "여긴 던전의... {FASTER}입구야! \n" +
                "{WAIT}{NORMAL}조심하도록 해... \n" +
                "\n{SLOW}들어가겠니..? [/yes], [/no]  \n";
            subEvents[0] = new TextEvent(this, CommandType.YES, token_1);
        }
        @Override
        public boolean render(float dt)
        {
            TextEvent textEvent = subEvents[ind];
            boolean result = textEvent.render(dt);
            if(result && subEvents[ind].needCommandType != CommandType.NONE)
            {
                System.out.println("player MP + 100!!!!!");
                return true;
            }
            stage.act(dt);
            stage.draw();
            return false;
        }
    },
    BURNING //불이 덮쳤다. HP - 10!
    {
        //불길에 덮여서 화상을 입음.
        @Override
        public void create()
        {

        }
        @Override
        public boolean render(float dt)
        {
            return false;
        }
    },
    FIND_WATER //마나를 회복한다. MP + 10!
    {
        @Override
        public void create()
        {

        }
        @Override
        public boolean render(float dt)
        {
            return false;
        }
    };

    @Override
    public void defaultCreate()
    {
        textBox = new ScrollPane(null);
        textBox.setFlickScroll(true);
        textBox.setPosition(10,45);
        textBox.setWidth(420);
        textBox.setHeight(265);

        stage.addActor(textBox);

        //텍스트 박스 셋팅
        textField = new TextField("", Global.TEXTFIELDSTYLE);
        textField.setWidth(Global.TEXTBOX_WIDTH);
        textField.setPosition(
                Global.INPUT_TEXTBOX_X,
                Global.INPUT_TEXTBOX_Y
        );
        textField.setVisible(false);
        stage.setKeyboardFocus(textField);
        stage.addActor(textField);

        InputMultiplexer im = new InputMultiplexer(this, stage);
        Gdx.input.setInputProcessor(im);
    }
    @Override
    public void controlHP(int value) {
    }
    @Override
    public void controlMP(int value) {
    }
    @Override
    public boolean keyDown(int keycode)
    {
        return false;
    }
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }
    @Override
    public boolean keyTyped(char character) {
        return false;
    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }
    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }


    protected Stage stage;

    protected TextEvent[] subEvents;
    protected int ind = 0;
    protected String log = "";
    protected ScrollPane textBox;
    protected TextField textField;

}
