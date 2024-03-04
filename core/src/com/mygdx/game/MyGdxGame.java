package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.mygdx.game.event.Event;
import com.mygdx.game.player.Player;
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
		//이벤트의 진행여부를 플레이어가 선택할 수 있음 (yes/no)
		List<Pair<Integer, Event>> events = new ArrayList<>();
		events.add(new Pair<Integer, Event>(5, Event.FIND_BOX_TYPE_GET_SWORD));
		events.add(new Pair<Integer, Event>(5, Event.FIND_BOX_TYPE_GET_FIRE));
		events.add(new Pair<Integer, Event>(5, Event.FIND_WATER_DROWN));
		events.add(new Pair<Integer, Event>(5, Event.FIND_WATER_GET_HEAL));
		events.add(new Pair<Integer, Event>(5, Event.LOOK_HEAL_TREE));
		events.add(new Pair<Integer, Event>(7, Event.ROCK_SLIDE_AREA_DAMAGE));
		events.add(new Pair<Integer, Event>(7, Event.ROCK_SLIDE_AREA_GET));
		events.add(new Pair<Integer, Event>(7, Event.FINE_BONFIRE));
		events.add(new Pair<Integer, Event>(8, Event.LOOK_THORNBUSH));

		events.add(new Pair<Integer, Event>(5, Event.MEET_BAT_ATTACK));
		events.add(new Pair<Integer, Event>(5, Event.MEET_SPIDER_GET_WEB));
		events.add(new Pair<Integer, Event>(7, Event.MEET_ALLIGATOR));
		events.add(new Pair<Integer, Event>(8, Event.ENCOUNTER_MURLOC));

		events.add(new Pair<Integer, Event>(10, Event.NO_GET_EXCALIBUR));
		events.add(new Pair<Integer, Event>(3, Event.GET_EXCALIBUR));



		//StageScreen firstStage = StageManangerment.instance.stageList.get(0);
		setScreen(new StageScreen(this, events));
	}

}
