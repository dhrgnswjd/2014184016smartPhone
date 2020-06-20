package kr.ac.kpu.game.sdw.buldingbreakproject.util;

import android.graphics.RectF;
import android.util.Log;

import kr.ac.kpu.game.sdw.buldingbreakproject.gameobj.BoxCollidable;

public class CollisionHelper {
    private final String TAG = this.getClass().getName();
    private RectF r1 = new RectF();
    private RectF r2 = new RectF();
    public boolean collides(BoxCollidable o1, BoxCollidable o2){
        o1.getBox(r1);
        o2.getBox(r2);
        if (r1.top <= r2.bottom)
            return true;
        return false;
    }
    public boolean collides(BoxCollidable o1, float x, float y) {
        o1.getBox(r1);
        //Log.d(TAG,"x = " + r1.left);
        if (r1.left < x && x < r1.right) {
            if (r1.top < y && y < r1.bottom) {
                Log.d(TAG,"collide!!");
                return true;
            }
        }
        return false;
    }
}
