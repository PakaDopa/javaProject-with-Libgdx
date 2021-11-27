package com.mygdx.game.stage;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.base.BaseActor;
import com.mygdx.game.base.BaseScreen;
import com.mygdx.game.event.Event;
import com.mygdx.game.textbox.TextBox;
import com.mygdx.game.textbox.TextInputBox;
import com.mygdx.game.utils.Global;
import com.mygdx.game.utils.Pair;
import java.util.List;

public class StageScreen extends BaseScreen {
    Stage stage;

    Event event;
    //!=====!
    public StageScreen(Game game) {
        super(game);
    }
    public void createEventList(List<Pair<Integer, Event>> events)
    {
        //eventList.addAll(events);
        //shuffleEventList = GameUtils.makeShuffleArray(eventList);
    }
    //!=====!

    @Override
    public void create() {
        //백그라운드 셋팅
        stage = new Stage(new FitViewport(Global.VIEW_WIDTH, Global.VIEW_HEIGHT));
        BaseActor backgroundActor = new BaseActor();
        backgroundActor.setTexture(new Texture(Global.BACKIMGPATH));
        backgroundActor.setPosition(0,0);
        stage.addActor(backgroundActor);

        InputMultiplexer im = new InputMultiplexer(TextBox.instance.stage, TextInputBox.instance.stage);
        Gdx.input.setInputProcessor(im);

        event = Event.STAGE_ROOT;
        event.create();
    }
    @Override
    public void render(float dt) {
        //Image Clear
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        //boolean eventing = eventTree.currentEvent.update(dt);
        Event.STAGE_ROOT.update(dt);

        //draw background
        stage.act(dt);
        stage.draw();

        //draw and act TextInputBox, TextBox
        TextInputBox.instance.render(dt);
        TextBox.instance.render(dt);
    }
}
