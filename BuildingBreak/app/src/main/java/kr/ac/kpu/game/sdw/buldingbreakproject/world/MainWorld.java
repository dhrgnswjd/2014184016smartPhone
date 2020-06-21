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
import kr.ac.kpu.game.sdw.buldingbreakproject.gameobj.LifeObject;
import kr.ac.kpu.game.sdw.buldingbreakproject.gameobj.ScoreObject;
import kr.ac.kpu.game.sdw.buldingbreakproject.gameobj.UserButton;

public class MainWorld extends GameWorld {
    private static final String TAG = MainWorld.class.getSimpleName();
    private Character _player;
    private LifeObject lo;
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
        add(Layer.bg, new BitmapObject(rect.right/2,rect.bottom/2,rect.right,rect.bottom,R.mipmap.bg));
        add(Layer.building, new BuildingLayer());

        _player = new Character();
        add(Layer.player,_player);

        UserButton btnJump = new UserButton(rect.right*0.2f,rect.bottom*0.9f,150,150, R.mipmap.button0);
        btnJump.setOnClickRunnable(true, new Runnable() {
            @Override
            public void run() {
                _player.jump();
            }
        });

        add(Layer.ui,btnJump);

        UserButton btnAttack = new UserButton(rect.right*0.8f,rect.bottom*0.9f,150,150, R.mipmap.button1);
        btnAttack.setOnClickRunnable(true, new Runnable() {
            @Override
            public void run() {
                _player.attack();

            }
        });

        add(Layer.ui,btnAttack);

        UserButton btnShield = new UserButton(rect.right*0.8f,rect.bottom*0.8f,150,150, R.mipmap.sheild);
        btnShield.setOnClickRunnable(true, new Runnable() {
            @Override
            public void run() {
                _player.shield();

            }
        });

        add(Layer.ui,btnShield);

        UserButton btnPower = new UserButton(rect.right*0.2f,rect.bottom*0.8f,150,150, R.mipmap.special);
        btnPower.setOnClickRunnable(true, new Runnable() {
            @Override
            public void run() {
                _player.power();

            }
        });

        add(Layer.ui,btnPower);

        ScoreObject so = ScoreObject.get();
        so.initScoreObject(rect.right*0.95f,rect.bottom*0.05f);
        add(Layer.ui,so);

        lo = new LifeObject(rect.right*0.05f,rect.bottom*0.08f);
        add(Layer.ui,lo);

    }
    public void setLife(int life){
        lo.setLife(life);
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
