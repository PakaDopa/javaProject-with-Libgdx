package com.mygdx.game.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SoundMananger {
    private SoundMananger(){
        setting();
    }
    public static SoundMananger instance = new SoundMananger();
    public List<Sound> list;

    private void setting()
    {
        list = new ArrayList<>();
        String format = "Sound/textsound";
        for(int i = 1; i <= 10; i++)
        {
            String extension = i + ".wav";
            String path = "Sound/textsound" + extension;
            list.add(Gdx.audio.newSound(Gdx.files.internal(path)));
        }
    }

    public Sound randomPick()
    {
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }
}
