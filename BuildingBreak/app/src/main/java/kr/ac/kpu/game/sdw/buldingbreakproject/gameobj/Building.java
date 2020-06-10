package kr.ac.kpu.game.sdw.buldingbreakproject.gameobj;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Random;

import kr.ac.kpu.game.sdw.buldingbreakproject.R;

public class Building implements GameObject{
    private static Bitmap bitmap;
    private static final float BOTTOM_HEIGHT = 500;
    private float y;
    private float x;
    private int layer;
    private static final float BUILDING_X_POSITION = 500;
    GameWorld gw = GameWorld.get();


    public Building(Resources res, int layer) {
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(res, R.mipmap.buildingtest1);
        }
        this.y = BOTTOM_HEIGHT+layer*bitmap.getHeight();

        this.layer = layer;
    }

    public void update() {
    }

    public void draw(Canvas canvas) {


        this.x = (gw.getRight()+gw.getLeft()-bitmap.getWidth())*0.5f;
        canvas.drawBitmap(bitmap, x, y, null);
    }
}
