package com.mygdx.game.utils;

import com.mygdx.game.event.Event;
import com.mygdx.game.stage.StageNode;

import java.util.*;

public class GameUtils {
    public static List<Event> makeShuffleArray(List<Pair<Integer, Event>> eventList)
    {
        List<Event> result = new ArrayList<Event>();
        //Setting List
        for(int i = 0; i < eventList.size(); i++)
        {
            int percent = eventList.get(i).getX();
            Event event = eventList.get(i).getY();
            for(int j = 0; j < percent; j++)
                result.add(event);
        }

        //Shuffle
        shuffleEvent(result, 100); //default 100;
        return result;
    }
    public static void shuffleEvent(List<Event> list, int repeat)
    {
        Event tep, tep2;
        int randNum, randNum2;

        for(int i = 0; i < repeat; i++)
        {
            randNum = (int)(Math.random()*list.size());
            tep = list.get(randNum);
            randNum2 = (int)(Math.random()*list.size());
            tep2 = list.get(randNum2);

            list.set(randNum, tep2);
            list.set(randNum2, tep);
        }
    }
    public static boolean calPercent(int percent)
    {
        Random random = new Random();
        int value = random.nextInt(100); //0~99
        return value <= percent;
    }
    public static Event selectEvent(List<Event> eventList)
    {
        Random random = new Random();
        int value = random.nextInt(eventList.size());
        return eventList.get(value);
    }
    public static StageNode getStageRootTree(List<Event> eventList, int stageNum)
    {
        //시작 이벤트 셋팅
        StageNode rootNode = new StageNode(null);
        rootNode.setEvent(Event.STAGE_ROOT);

        Queue<StageNode> q = new LinkedList<StageNode>();
        q.add(rootNode);

        while(stageNum > 0)
        {
            StageNode currentNode = q.poll();
            if(currentNode == null)
                continue;
            boolean isCreateLeft = calPercent(Global.MAP_CREATE_PERCENT);
            boolean isCreateRight = calPercent(Global.MAP_CREATE_PERCENT);

            if(isCreateLeft && currentNode.leftNode == null)
            {
                StageNode newNode = new StageNode(currentNode);
                newNode.setEvent(selectEvent(eventList));
                currentNode.leftNode = newNode;
                q.add(newNode);
                stageNum--;
            }
            if(isCreateRight && currentNode.rightNode == null)
            {
                StageNode newNode = new StageNode(currentNode);
                newNode.setEvent(selectEvent(eventList));
                currentNode.rightNode = newNode;
                q.add(newNode);
                stageNum--;
            }

            if(!isCreateLeft && !isCreateRight)
                q.add(currentNode); //재시도
        }
        return rootNode;
    }
}
