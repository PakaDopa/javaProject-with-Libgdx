package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;

public class Global {

    public static final String BACKIMGPATH = "Background/StageScreen.png";

    public static final int VIEW_WIDTH = 460;
    public static final int VIEW_HEIGHT = 800;

    public static final int TEXTBOX_X = 10;
    public static final int TEXTBOX_Y = 20;

    public static final int TEXTBOX_WIDTH = 420;
    public static final int TEXTBOX_HEIGHT = 300;

    public static final int INPUT_TEXTBOX_X = 25;
    public static final int INPUT_TEXTBOX_Y = 22;

    public static BitmapFont FONT;
    public static final String FONTPATH_FNT = "Font/gameFont.fnt";
    public static final String FONTPATH_PNG = "Font/gameFont.png";

    public static LabelStyle LABELSTYLE;
    public static TextFieldStyle TEXTFIELDSTYLE;

    public static void SETTING()
    {
        //setting global font
        FONT = new BitmapFont(
                Gdx.files.internal(FONTPATH_FNT),
                Gdx.files.internal(FONTPATH_PNG ),
                false);
        LABELSTYLE = new LabelStyle(FONT, null);
        TEXTFIELDSTYLE = new TextFieldStyle(FONT, Color.WHITE, null, null, null);


    }
}
