package com.mygdx.game.utils;

import com.mygdx.game.event.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
}
