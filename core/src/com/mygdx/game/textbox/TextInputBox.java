package com.mygdx.game.textbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.utils.Global;

public class TextInputBox implements InputProcessor {
    protected TextField textField;
    public Stage stage;

    public static TextInputBox instance = new TextInputBox();
    private TextInputBox()
    {
        stage = new Stage(new FitViewport(Global.VIEW_WIDTH, Global.VIEW_HEIGHT));

        //텍스트 박스 셋팅
        textField = new TextField("", Global.TEXTFIELDSTYLE);
        textField.setWidth(Global.TEXTBOX_WIDTH);
        textField.setPosition(
                Global.INPUT_TEXTBOX_X,
                Global.INPUT_TEXTBOX_Y
        );
        textField.setVisible(false);
        textField.setClipboard(null);

        stage.setKeyboardFocus(textField);
        stage.addActor(textField);

        InputMultiplexer im = new InputMultiplexer(stage);
        Gdx.input.setInputProcessor(im);
    }
    public void setText(String token)
    {
        textField.setText(token);
    }
    public String getText()
    {
        return textField.getText();
    }
    public void setVisible(boolean bool)
    {
        textField.setVisible(bool);
    }
    public void setDefault()
    {
        setText("");
        setVisible(false);
    }
    public void render(float dt)
    {
        stage.act(dt);
        stage.draw();
    }

    @Override
    public boolean keyDown(int keycode) {
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
}
