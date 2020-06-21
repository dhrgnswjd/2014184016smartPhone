package kr.ac.kpu.game.sdw.buldingbreakproject.gameobj;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

import java.util.ArrayList;

import kr.ac.kpu.game.sdw.buldingbreakproject.util.CollisionHelper;
import kr.ac.kpu.game.sdw.buldingbreakproject.util.FrameAnimationBitmap;
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
    private static float time;
    private int layer;
    private int hp = 50;
    private boolean destroy = false;

    private static ArrayList<GameObject> characters;
    private static ArrayList<GameObject> buildingLayer;
    CollisionHelper collisionHelper = new CollisionHelper();
    private FrameAnimationBitmap fab;

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

        characters = gw.getobjectAt(MainWorld.Layer.player);
        buildingLayer = gw.getobjectAt(MainWorld.Layer.building);

    }

    public void update(float _y, int layer, float speed) {
        BuildingLayer bl = (BuildingLayer)buildingLayer.get(0);
        Character c = (Character)characters.get(0);
        CollisionHelper collisionHelper = new CollisionHelper();
        MainWorld gw = MainWorld.get();
        y = _y;
        this.layer = layer;

        if(!destroy) {
            if (collisionHelper.collides(c, this)) {
                switch (c.getState()) {

                    case idle:
                        c.dgreeLife();
                        break;
                    case jump:
                        c.setJumpPower(speed);
                        break;
                    case attack1_jump:
                        if(c.doneAttack()){
                            bl.setDestroy();
                            destroy = true;
                        }

                        break;
                    case attack2_jump:
                        break;

                    case attack_stand:
                        if(c.doneAttack()){
                            bl.setDestroy();
                            destroy = true;
                        }
                        break;
                    case shield:
                        bl.setShield();
                        break;
                    case power:
                        bl.setDestroy();
                        break;
                    case power_jump:
                        bl.setDestroy();
                        break;
                    default:
                }

                /*if (c.getState() == Character.State.idle) {
                    bl.setDestroy();
                }
                if (c.getState() == Character.State.shield) {
                    bl.setShield();
                }*/
            }
        }





    }

    private void degreeHP() {

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
