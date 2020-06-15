package kr.ac.kpu.game.sdw.buldingbreakproject.gameobj;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import java.util.ArrayList;

public class GameWorld {


    private View view;
    private Resources res;

    public static GameWorld get() {
        if (singleton == null) {
            singleton = new GameWorld();
        }
        return singleton;
    }

    public static GameWorld singleton;
    private ArrayList<GameObject> object;
    private Rect rect;

    private GameWorld() {
    }

    public void initResources(View view) {
        this.view = view;
        this.res = view.getResources();
        object = new ArrayList<>();
        object.add(new Character());
        object.add(new BuildingLayer());
    }

    public void draw(Canvas canvas) {
        for (GameObject o : object) {
            o.draw(canvas);
        }
    }


    public void update() {

        if (rect == null) {
            return;
        }
        for (GameObject o : object) {
            o.update();
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

}
