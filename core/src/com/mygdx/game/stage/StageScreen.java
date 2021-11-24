package com.mygdx.game.stage;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.base.BaseActor;
import com.mygdx.game.base.BaseScreen;
import com.mygdx.game.event.Event;
import com.mygdx.game.event.EventNode;
import com.mygdx.game.utils.GameUtils;
import com.mygdx.game.utils.Global;
import com.mygdx.game.utils.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class StageScreen extends BaseScreen {
    Stage stage;
    TextField textField;

    List<Pair<Integer, Event>> eventList = new ArrayList<>();
    List<Event> shuffleEventList = new ArrayList<>();
    EventNode eventTree;

    //!=====!
    public StageScreen(Game game) {
        super(game);
    }
    public void createEventList(List<Pair<Integer, Event>> events)
    {
        eventList.addAll(events);
        shuffleEventList = GameUtils.makeShuffleArray(eventList);
    }
    public void makeRandomTree(Event openingStageEvent, int percentNodeCreate, int n)
    {
        Queue<EventNode> q = new LinkedList<EventNode>();


        //스테이지 선언
        //현재 이벤트칸에 이벤트 넣어주고,
        //q에 넣어준다.

        EventNode rootNode = new EventNode(null);
        rootNode.currentEvent = openingStageEvent;
        rootNode.currentEvent.create();
        q.add(rootNode);

        while(n != 0) //n은 그 스테이지의 갯수 10개 [20개라고하면 방이 20개가 있는거임.]
        {
            EventNode currentNode = q.poll();
            if(currentNode == null)
            {
                System.out.println("STAGE init Error {makeRandomTree Func} \n Exit Func");
                break;
            }
            EventNode leftNode = currentNode.leftNode;
            EventNode rightNode = currentNode.rightNode;

            //왼쪽 노드 생성에 성공 했을 때
            boolean isLeftNodeCreate = GameUtils.calPercent(percentNodeCreate);
            //오른쪽 노드 생성에 성공 했을 때
            boolean isRightNodeCreate = GameUtils.calPercent(percentNodeCreate);

            if(isLeftNodeCreate && leftNode == null)
            {
                //create leftNode
                leftNode = new EventNode(currentNode);
                leftNode.currentEvent = GameUtils.selectEvent(shuffleEventList);
                //init event data
                leftNode.currentEvent.create();
                //link
                currentNode.leftNode = leftNode;
                //add queue
                q.add(leftNode);
                n--;
            }
            if(isRightNodeCreate && rightNode == null)
            {
                //create rightNode
                rightNode = new EventNode(currentNode);
                rightNode.currentEvent = GameUtils.selectEvent(shuffleEventList);
                rightNode.currentEvent.create();
                //link
                currentNode.rightNode = rightNode;
                //add queue
                q.add(rightNode);
                n--;
            }
            if(!isLeftNodeCreate && !isRightNodeCreate)
            {
                //만약 둘 다 못 넣었을 경우에, 해당 트리를 재시작한다.
                q.add(currentNode);
            }
        }
        q.clear();
        eventTree = rootNode;
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
    }
    @Override
    public void render(float dt) {
        //Image Clear
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(dt);
        stage.draw();

        if(eventTree.currentEvent.render(dt))
        {
            System.out.println("next event!!!");
        }
    }
}
