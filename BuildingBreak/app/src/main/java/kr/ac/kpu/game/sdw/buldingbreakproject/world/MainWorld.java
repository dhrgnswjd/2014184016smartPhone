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
    private Character _player;
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

        _player = new Character();
        add(Layer.player,_player);

        UserButton btnJump = new UserButton(rect.right*0.2f,rect.bottom*0.9f,200,150, R.mipmap.enemy_01);
        btnJump.setOnClickRunnable(true, new Runnable() {
            @Override
            public void run() {
                _player.jump();
            }
        });

        add(Layer.ui,btnJump);

        UserButton btnAttack = new UserButton(rect.right*0.8f,rect.bottom*0.9f,200,150, R.mipmap.enemy_01);
        btnAttack.setOnClickRunnable(true, new Runnable() {
            @Override
            public void run() {
                _player.attack();

            }
        });

        add(Layer.ui,btnAttack);

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
