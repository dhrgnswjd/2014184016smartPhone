package kr.ac.kpu.game.sdw.buldingbreakproject.gameobj;

import android.content.res.Resources;
import android.graphics.Canvas;

import java.util.ArrayList;

import kr.ac.kpu.game.sdw.buldingbreakproject.util.DeltaTime;
import kr.ac.kpu.game.sdw.buldingbreakproject.util.OneBuildingBitmap;

public class BuildingLayer implements GameObject {

    private static final float USER_GRAVITY = 250;
    private ArrayList<Building> b = new ArrayList<>();
    private OneBuildingBitmap ob;
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
    public BuildingLayer() {
        Resources res = gw.getResources();
        ob = OneBuildingBitmap.load(res);
        for(int i = 0 ; i < 5; i++) {
            b.add(new Building(i));
        }
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

        if(y >= gw.getLand(h_half)-200){
            y =gw.getLand(h_half)-200;
        }
        else{
            y += speed * time;
        }
        for(int i = 0 ; i < 5; i ++){
            b.get(i).update(y);
        }
    }

    public void draw(Canvas canvas) {
        for(int i = 0 ; i < 5; i ++){
            b.get(i).draw(canvas);
        }
    }


}
