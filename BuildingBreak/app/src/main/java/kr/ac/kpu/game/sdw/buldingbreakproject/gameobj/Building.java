package kr.ac.kpu.game.sdw.buldingbreakproject.gameobj;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.ArrayList;

import kr.ac.kpu.game.sdw.buldingbreakproject.framework.GameWorld;
import kr.ac.kpu.game.sdw.buldingbreakproject.util.CollisionHelper;
import kr.ac.kpu.game.sdw.buldingbreakproject.util.OneBuildingBitmap;
import kr.ac.kpu.game.sdw.buldingbreakproject.world.MainWorld;

public class Building implements BoxCollidable{

    private static final float USER_GRAVITY = 150;
    private OneBuildingBitmap ob;

    private static float x;
    private static float y;
    private static int height;
    private static int h_half;
    private static int width;
    private static int w_half;
    private static float speed;
    private static float time;
    private int layer;
    CollisionHelper collisionHelper = new CollisionHelper();

    public Building(int layer) {
        MainWorld gw = MainWorld.get();
        Resources res = gw.getResources();
        this.ob = OneBuildingBitmap.load(res);
        this.layer = layer;
        height = OneBuildingBitmap.getHeight();
        width = OneBuildingBitmap.getWidth();
        h_half = height/2;
        w_half = width/2;
        x = gw.getRight()/2;
        y = gw.getTop();
    }

    public void update(float _y,int layer) {

        CollisionHelper collisionHelper = new CollisionHelper();
        MainWorld gw = MainWorld.get();
        y = _y;
        this.layer = layer;
        ArrayList<GameObject> characters = gw.getobjectAt(MainWorld.Layer.player);
        ArrayList<GameObject> buildingLayer = gw.getobjectAt(MainWorld.Layer.building);
        BuildingLayer bl = (BuildingLayer)buildingLayer.get(0);
        Character c = (Character)characters.get(0);
        if(collisionHelper.collides(c,this)){
            //bl.setIndex();

            if(c.getState()== Character.State.idle){
                bl.setIndex();
            }
            if(c.getState() == Character.State.shield){
                bl.setShield();
            }
        }





    }

    public void draw(Canvas canvas) {
        ob.draw(canvas,x,y-layer*height,layer);
    }


    @Override
    public void getBox(RectF rect) {
        rect.left = x - w_half;
        rect.right = x + w_half;
        rect.bottom = y + h_half - (height*layer);
        rect.top = y - h_half;
    }

    public void logging() {

    }
}
