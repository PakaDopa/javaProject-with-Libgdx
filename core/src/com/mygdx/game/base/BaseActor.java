package com.mygdx.game.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.utils.GifDecoder;
import com.mygdx.game.utils.Global;


public class BaseActor extends Actor {
    public TextureRegion region;
    public Animation<TextureRegion> animationRegion;
    SpriteBatch spriteBatch;
    float elapsed = 0.0f;
    public Rectangle boundary;

    public BaseActor()
    {
        super();
        region = new TextureRegion();
        boundary = new Rectangle();
        spriteBatch = new SpriteBatch();
    }

    public void setTexture(Texture t)
    {
        int w = t.getWidth();
        int h = t.getHeight();
        setWidth(w);
        setHeight(h);
        region.setRegion(t);
    }
    public void setAnimationRegion(String path)
    {
        animationRegion = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP,
                Gdx.files.internal(path).read());
    }
    public void setAnimationPosition()
    {
    }
    public void act(float dt)
    {
        super.act(dt);
    }
    public void draw(Batch batch, float parentAlpha)
    {
        Color c = getColor();
        batch.setColor(c.r, c.g, c.b, c.a);
        if(isVisible())
        {
            if(animationRegion == null) {
                batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
                        getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
            }
            else
            {
                elapsed += Gdx.graphics.getDeltaTime();
                spriteBatch.begin();
                spriteBatch.draw(animationRegion.getKeyFrame(elapsed),
                        (float)Global.VIEW_WIDTH / 2 - (72),
                        (float)Global.VIEW_HEIGHT / 2 + 120);
                spriteBatch.end();
            }
        }
    }

}
