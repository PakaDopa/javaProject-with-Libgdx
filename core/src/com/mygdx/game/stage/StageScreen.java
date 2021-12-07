package com.mygdx.game.stage;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.crashinvaders.vfx.VfxManager;
import com.crashinvaders.vfx.effects.*;
import com.mygdx.game.base.BaseActor;
import com.mygdx.game.base.BaseScreen;
import com.mygdx.game.enemy.BattleManagement;
import com.mygdx.game.event.Event;
import com.mygdx.game.player.Player;
import com.mygdx.game.textbox.TextBox;
import com.mygdx.game.textbox.TextInputBox;
import com.mygdx.game.utils.GameUtils;
import com.mygdx.game.utils.Global;
import com.mygdx.game.utils.Pair;

import java.util.ArrayList;
import java.util.List;

public class StageScreen extends BaseScreen {
    Stage stage;
    Event event;
    StageNode stageNode;
    StageState stageState;

    VfxManager vfxManager;
    public StageScreen(Game game, List<Pair<Integer, Event>> events) {
        super(game);
        create(events);

        vfxManager = new VfxManager(Pixmap.Format.RGBA8888);

        //vfxManager.addEffect(new OldTvEffect());
        vfxManager.addEffect(new FilmGrainEffect());
        vfxManager.addEffect(new BloomEffect());
        //vfxManager.addEffect(new VignettingEffect(false));
    }

    public void create(List<Pair<Integer, Event>> events)
    {
        stageState = StageState.INIT; //INIT

        List<Event> randomEventList = new ArrayList<>();
        randomEventList = GameUtils.makeShuffleArray(events);

        stageNode = GameUtils.getStageRootTree(randomEventList, 10);
    }

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
    }
    @Override
    public void render(float dt) {
        vfxManager.cleanUpBuffers();
        vfxManager.beginInputCapture();

        //Image Clear
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //draw background
        stage.act(dt);
        stage.draw();

        //==============
        stageState = stageState.update(stageNode, dt);
        if(stageState == StageState.MOVE_BACK)
        {
            stageNode = stageNode.parentNode;
            stageState = StageState.INIT;
        }
        else if(stageState == StageState.MOVE_LEFT){
            System.out.println(stageNode.toString());
            stageNode = stageNode.leftNode;
            stageState = StageState.INIT;
        }
        else if(stageState == StageState.MOVE_RIGHT)
        {
            System.out.println(stageNode.toString());
            stageNode = stageNode.rightNode;
            stageState = StageState.INIT;
        }
        //==============
        //draw and act TextInputBox, TextBox
        TextInputBox.instance.update(dt);
        TextBox.instance.update(dt);
        BattleManagement.instance.update(dt);

        vfxManager.update(dt);
        vfxManager.endInputCapture();
        vfxManager.applyEffects();
        vfxManager.renderToScreen();
    }
}
