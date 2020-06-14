package kr.ac.kpu.game.sdw.buldingbreakproject.gameobj;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.Log;

import java.sql.Time;
import java.util.ArrayList;

import kr.ac.kpu.game.sdw.buldingbreakproject.util.DeltaTime;
import kr.ac.kpu.game.sdw.buldingbreakproject.util.OneBuildingBitmap;

public class Building implements GameObject{

    private static final float USER_GRAVITY = 150;
    private ArrayList<OneBuildingBitmap> ob = new ArrayList<>();
    private int layer;
    private float x;
    private float y;
    private int height;
    private int h_half;
    private int width;
    private int w_half;
    private float speed;
    private float time;
    GameWorld gw = GameWorld.get();
    public Building(Resources res) {
        for(int i = 0 ; i < 5; i++) {
            ob.add(OneBuildingBitmap.load(res, i));
        }
        this.layer = layer;
        height = OneBuildingBitmap.getHeight();
        width = OneBuildingBitmap.getWidth();
        h_half = height/2;
        w_half = width/2;

        x = gw.getRight()/2;
        y = gw.getTop();
    }

    public void update() {
        DeltaTime dt = DeltaTime.get();
        time = dt.getDeltaTime();
        speed += time * USER_GRAVITY;
        y += speed * time;
    }

    public void draw(Canvas canvas) {
        for(int i = 0 ; i < 5 ; i++) {
            ob.get(i).draw(canvas, x, y - i * height);
        }
    }


}
