package com.mygdx.game.base;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class BaseScreen implements Screen
{
    //게임 전체를 관리하는 Game 오브젝트 선언
    protected Game game;

    //카메라의 크기를 고정 <- 이해가 안간다면 질문
    public final int viewWidth = 460;
    public final int viewHeight = 800;

    //그리져는 default Scale의 크기를 [2]로 고정
    public final int baseScale = 2;

    //게임의 일시정지판단 변수
    private boolean paused;

    public BaseScreen(Game game)
    {
        this.game = game;
        create();
    }
    public abstract void create();
    public abstract void render(float dt);

    public void show() {

    }
    public void resize(int width, int height) {

    }
    public void pause() {

    }
    public void resume() {

    }
    public void hide() {

    }
    public void dispose() {

    }
}
