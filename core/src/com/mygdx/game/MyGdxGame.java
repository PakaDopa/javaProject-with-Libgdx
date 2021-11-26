package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.mygdx.game.event.Event;
import com.mygdx.game.stage.StageManangerment;
import com.mygdx.game.stage.StageScreen;
import com.mygdx.game.utils.Global;
import com.mygdx.game.utils.Pair;

import java.util.*;

public class MyGdxGame extends Game {

	@Override
	public void create ()
	{
		//글로벌 변수 초기화
		Global.SETTING();

		//이벤트 리스트
		//Waring: event percent는 총 합 100이 되도록 하는게 좋다. [QA 쉬워짐]
		List<Pair<Integer, Event>> events = new ArrayList<>();
		events.add(new Pair(50, Event.BURNING));
		events.add(new Pair(50, Event.FIND_WATER));

		//stage[1]
		StageManangerment.instance.addingStage(this, events);

		StageScreen firstStage = StageManangerment.instance.stageList.get(0);
		//TestScript test = new TestScript(this);
		setScreen(firstStage);
		//setScreen(test);
	}
}
