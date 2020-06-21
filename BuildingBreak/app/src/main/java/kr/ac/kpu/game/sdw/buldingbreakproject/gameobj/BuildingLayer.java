package kr.ac.kpu.game.sdw.buldingbreakproject.gameobj;

import android.content.res.Resources;
import android.graphics.Canvas;

import java.util.ArrayList;

import kr.ac.kpu.game.sdw.buldingbreakproject.framework.GameWorld;
import kr.ac.kpu.game.sdw.buldingbreakproject.util.DeltaTime;
import kr.ac.kpu.game.sdw.buldingbreakproject.util.OneBuildingBitmap;
import kr.ac.kpu.game.sdw.buldingbreakproject.world.MainWorld;

public class BuildingLayer implements GameObject {

    private static final float USER_GRAVITY = 100;
    private static final float USER_UP_FORCE = 500;
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
    private int index;
    MainWorld gw = MainWorld.get();
    private float shild;
    private float force;
    DeltaTime dt = new DeltaTime();

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
        index = 0;
        x = gw.getRight()/2;
        y = gw.getTop();
        force = 0;
    }

    public void update() {

        time = dt.getDeltaTime();
        speed += time * USER_GRAVITY;

        y += speed * time;
        //Log.d(this.getClass().getName(),"call" + index);
        if(y >= gw.getLand(h_half)-200 + index*height){
            y =gw.getLand(h_half)-200 + index*height;
        }

        for(int i = index ; i < 5; i ++){
            b.get(i).update(y, i,this.speed);
        }

    }

    public void draw(Canvas canvas) {
        for(int i = index; i < 5; i ++){
            b.get(i).draw(canvas);
        }

    }
    public ArrayList<Building> objectsAt(){
        return b;
    }
    public void setDestroy(){
        this.index++;
        if(index >= 5){
            spawn();
        }
    }

    private void spawn() {
        b.clear();
        for(int i = 0 ; i < 5; i++) {
            b.add(new Building(i));
        }
        y = gw.getTop();
        index = 0;
        speed = 0;
        force = 0;
    }


    public void setShield() {
        speed = -USER_UP_FORCE;
    }

}
