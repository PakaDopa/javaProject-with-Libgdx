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
*/
