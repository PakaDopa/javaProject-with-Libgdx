/*
package com.mygdx.game.stage;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.base.BaseActor;
import com.mygdx.game.base.BaseScreen;
import com.mygdx.game.textbox.TextBox;
import com.mygdx.game.textbox.TextInputBox;
import com.mygdx.game.utils.Global;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;

public class TestScript extends BaseScreen {

    Stage stage;
    public TestScript(Game game) {
        super(game);
        create();
    }

    @Override
    public void create() {
        stage = new Stage(new FitViewport(Global.VIEW_WIDTH, Global.VIEW_HEIGHT));

        InputMultiplexer im = new InputMultiplexer(TextBox.instance.stage, TextInputBox.instance.stage);
        Gdx.input.setInputProcessor(im);
    }

    @Override
    public void render(float dt) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
        {
            TextBox.instance.setText("\n{SICK}문장이 조금 길어도 가능할까요?\n복합문장이라 버그가 있었던걸까?\n");
        }

        TextBox.instance.render(dt);
    }
}
*/
