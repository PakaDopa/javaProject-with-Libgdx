package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.mygdx.game.event.Event;
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

		//Event가 스테이지 트리에, Integer 퍼센트 확률로 생성됨.
		List<Pair<Integer, Event>> events = new ArrayList<>();
		events.add(new Pair<Integer, Event>(50, Event.FIND_BOX_TYPE_GET_SWORD)); //[50%로 생성] 아이템을 찾는 상자 이벤트
		events.add(new Pair<Integer, Event>(50, Event.FIND_BOX_TYPE_GET_FIRE)); //[50%로 생성] 데미지를 입는 상자 이벤트

		//StageScreen firstStage = StageManangerment.instance.stageList.get(0);
		setScreen(new StageScreen(this, events));

		//TestScript test = new TestScript(this);
		//setScreen(test);
	}
}
