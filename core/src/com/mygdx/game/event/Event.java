package com.mygdx.game.event;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.FitViewport;
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
            state = EventState.TEXTING;
            //인풋 프로시저 셋팅
            String token =
                    "{FASTER}하하하 스테이지에 찾아온 것을 환영하오.\n" +
                    "이런 곳에는 잘 오질 않는데.. 무슨 볼 일이 있나보구려.\n" +
                    "아차차,, 늙다리가 말이 많았구려...\n" +
                    "어서 가던 길 가시게..\n" +
                    "부디 무운을 비네... 조심하시게! \n" +
                    "하하하 스테이지에 찾아온 것을 환영하오.\n" +
                    "이런 곳에는 잘 오질 않는데.. 무슨 볼 일이 있나보구려.\n" +
                    "아차차,, 늙다리가 말이 많았구려...\n" +
                    "어서 가던 길 가시게..\n" +
                    "부디 무운을 비네... 조심하시게! \n" +
                    "하하하 스테이지에 찾아온 것을 환영하오.\n" +
                    "이런 곳에는 잘 오질 않는데.. 무슨 볼 일이 있나보구려.\n" +
                    "아차차,, 늙다리가 말이 많았구려...\n" +
                    "어서 가던 길 가시게..\n" +
                    "부디 무운을 비네... 조심하시게! \n" +
                    "하하하 스테이지에 찾아온 것을 환영하오.\n" +
                    "이런 곳에는 잘 오질 않는데.. 무슨 볼 일이 있나보구려.\n" +
                    "아차차,, 늙다리가 말이 많았구려...\n" +
                    "어서 가던 길 가시게..\n" +
                    "부디 무운을 비네... 조심하시게! \n";
            defaultCreate(token);
        }
        @Override
        public void render(float dt)
        {
            //인풋받아야함
            if(state == EventState.TEXTING_END)
            {
                state = EventState.INPUT;
                textField.setText("");
                textField.setVisible(true);
            }

            stage.act(dt);
            stage.draw();
        }
    },
    BURNING
    {
        //불길에 덮여서 화상을 입음.
        @Override
        public void create()
        {

        }
        @Override
        public void render(float dt)
        {

        }
    },
    FIND_WATER
    {
        @Override
        public void create()
        {

        }
        @Override
        public void render(float dt)
        {

        }
    };

    @Override
    public void defaultCreate(String token)
    {
        text = new TypingLabel(token, Global.LABELSTYLE);
        text.setTypingListener(new TypingAdapter()
        {
            @Override
            public void end()
            {
                System.out.println("end line");
                state = EventState.TEXTING_END;
            }
        });
        ScrollPane textBox = new ScrollPane(text);
        textBox.setFlickScroll(false);
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
    public boolean keyUp(int keycode) {
        return false;
    }
    @Override
    public boolean keyDown(int keycode) {
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
    protected EventState state;

    protected TextField textField;
    protected TypingLabel text;
}
