package kr.ac.kpu.game.sdw.buldingbreakproject.gameobj;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import java.util.ArrayList;

public class GameWorld {


    private View view;
    private Resources res;
    private long frameTimeNanos;
    private long timeDiffNanos;

    public static GameWorld get() {
        if (singleton == null) {
            singleton = new GameWorld();
        }
        return singleton;
    }

    public static GameWorld singleton;
    //private ArrayList<GameObject> object;
    private Rect rect;

    private GameWorld() {
    }

    public ArrayList<GameObject> getobjectAt(Layer layer) {
        return layers.get(layer.ordinal());
    }

    public enum Layer{
        bg,building,player,ui,COUNT
    }

    public void initResources(View view) {
        this.view = view;
        this.res = view.getResources();
        initLayers();
        initObjects();
    }
    public void initObjects() {
        add(Layer.building, new BuildingLayer());
        add(Layer.player, new Character());
    }

    private void add(final Layer layer, final GameObject obj) {
        view.post(new Runnable() {
            @Override
            public void run() {
                ArrayList<GameObject> objects = layers.get(layer.ordinal());
                objects.add(obj);
            }
        });

    }

    protected ArrayList<ArrayList<GameObject>> layers;
    private void initLayers() {
        layers = new ArrayList<>();
        for(int i = 0 ; i < Layer.COUNT.ordinal();i++){
            ArrayList<GameObject> layer = new ArrayList<>();
            layers.add(layer);
        }
    }

    public void draw(Canvas canvas) {
        for(ArrayList<GameObject> object : layers) {
            for (GameObject o : object) {
                o.draw(canvas);
            }
        }
    }

    public void update(long frameTimeNanos) {
        this.timeDiffNanos = frameTimeNanos - this.frameTimeNanos;
        this.frameTimeNanos = frameTimeNanos;
        if (rect == null) {
            return;
        }
        for(ArrayList<GameObject> object : layers) {
            for (GameObject o : object) {
                o.update();
            }
        }
    }

    public void setRect(Rect rect) {

        this.rect = rect;



    }

    public int getLeft() {
        return rect.left;
    }

    public int getRight() {
        return rect.right;
    }

    public int getBottom() {
        return rect.bottom;
    }

    public int getTop() {
        return rect.top;
    }

    public Resources getResources() {
        return res;
    }
    public long getTimeDiffNanos(){
        return timeDiffNanos;
    }
    public float getTimeDiffInSecond(){
        return (float)(timeDiffNanos/1000000000.0);
    }
    public long getCurrentTimeNanos(){
        return frameTimeNanos;
    }
    public float getLand(int halfszie){
        return rect.bottom - halfszie;
    }
}
