package com.mygdx.game.stage;

import com.badlogic.gdx.Game;
import com.mygdx.game.event.Event;
import com.mygdx.game.utils.Pair;

import java.util.ArrayList;
import java.util.List;

public class StageManangerment {
    // singleTon [Non-Safely multi Thread]
    public static StageManangerment instance = new StageManangerment();
    private StageManangerment() {}


    public List<StageScreen> stageList = new ArrayList<>();
    public void addingStage(Game game, List<Pair<Integer, Event>> events)
    {
        StageScreen stage = new StageScreen(game);
        stage.createEventList(events);
        //test code
        stage.makeRandomTree(Event.STAGE_ROOT,30, 12);
        stageList.add(stage);
    }
}
