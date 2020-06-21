package kr.ac.kpu.game.sdw.buldingbreakproject.framework;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import kr.ac.kpu.game.sdw.buldingbreakproject.gameobj.GameObject;
import kr.ac.kpu.game.sdw.buldingbreakproject.gameobj.Touchable;

public abstract class GameWorld {


    private static final String TAG = GameWorld.class.getSimpleName();
    protected View view;
    protected Resources res;
    protected long frameTimeNanos;
    protected long timeDiffNanos;
    private int scoreValue;
    private Paint scorePaint;

    public static GameWorld get() {
        if (singleton == null) {
    //        singleton = new GameWorld();
            Log.e(TAG,"GameWorld subclass not created");
        }
        return singleton;
    }

    protected static GameWorld singleton;
    //private ArrayList<GameObject> object;
    protected Rect rect;

    protected GameWorld() {
    }

    public ArrayList<GameObject> getobjectAt(int index) {
        return layers.get(index);
    }



    public void initResources(View view) {
        this.view = view;
        this.res = view.getResources();
        initLayers();
        initObjects();
    }
    public void initObjects() {
    }

    public void add(final int index, final GameObject obj) {
        view.post(new Runnable() {
            @Override
            public void run() {
                ArrayList<GameObject> objects = layers.get(index);
                objects.add(obj);
            }
        });

    }

    protected ArrayList<ArrayList<GameObject>> layers;
    private void initLayers() {
        layers = new ArrayList<>();
        int layerCount = getLayerCount();
        for(int i = 0 ; i < layerCount;i++){
            ArrayList<GameObject> layer = new ArrayList<>();
            layers.add(layer);
        }
    }

    abstract  protected int getLayerCount() ;


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

    public int getRight() {
        return rect.right;
    }

    public int getBottom() {
        return rect.bottom;
    }

    public int getTop() {
        return rect.top;
    }
    public int getLeft() {
        return rect.left;
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

    public boolean onTouchEvent(MotionEvent event) {
        //Log.d(TAG,"down");
        for (ArrayList<GameObject> objects: layers) {
            for (GameObject o : objects) {
                if (o instanceof Touchable) {
                    boolean ret = ((Touchable) o).onTouchEvent(event);
                    if (ret) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    protected boolean done;
    public void setAttacking(boolean done){

        this.done = done;
    }

    public boolean getAttacking(){
        return this.done;
    }
}
