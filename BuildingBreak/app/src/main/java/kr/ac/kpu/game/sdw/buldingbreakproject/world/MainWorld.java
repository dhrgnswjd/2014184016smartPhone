package kr.ac.kpu.game.sdw.buldingbreakproject.world;

import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;

import kr.ac.kpu.game.sdw.buldingbreakproject.R;
import kr.ac.kpu.game.sdw.buldingbreakproject.framework.GameWorld;
import kr.ac.kpu.game.sdw.buldingbreakproject.gameobj.BitmapObject;
import kr.ac.kpu.game.sdw.buldingbreakproject.gameobj.BuildingLayer;
import kr.ac.kpu.game.sdw.buldingbreakproject.gameobj.Character;
import kr.ac.kpu.game.sdw.buldingbreakproject.gameobj.GameObject;
import kr.ac.kpu.game.sdw.buldingbreakproject.gameobj.UserButton;

public class MainWorld extends GameWorld {
    private static final String TAG = MainWorld.class.getSimpleName();

    public static void create() {
        if(singleton != null){
            Log.e(TAG, "object already created");
        }
        singleton = new MainWorld();
    }
    public static MainWorld get(){
        return (MainWorld) singleton;
    }

    @Override
    public void initObjects() {
        add(Layer.building, new BuildingLayer());
        add(Layer.player, new Character());
        add(Layer.ui, new UserButton(500,500,550,550, R.mipmap.enemy_01));
    }

    @Override
    protected int getLayerCount() {
        return Layer.COUNT.ordinal();
    }

    public void add(Layer layer, final GameObject obj){
        add(layer.ordinal(),obj);
    }
    public ArrayList<GameObject> getobjectAt(Layer layer) {
        return getobjectAt(layer.ordinal());
    }

    private enum PlayState{
        normal, paused,gameover
    }
    public enum Layer{
        bg,building,player,ui,COUNT

    }
    public float getLand(int halfszie){
        return rect.bottom - halfszie;
    }


    @Override
    public void update(long frameTimeNanos) {
        super.update(frameTimeNanos);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }
}
