package kr.ac.kpu.game.sdw.buldingbreakproject.util;

import android.graphics.RectF;

import kr.ac.kpu.game.sdw.buldingbreakproject.gameobj.BoxCollidable;

public class CollisionHelper {
    private RectF r1 = new RectF();
    private RectF r2 = new RectF();
    public boolean collides(BoxCollidable o1, BoxCollidable o2){
        o1.getBox(r1);
        o2.getBox(r2);
        if (r1.top <= r2.bottom)
            return true;
        return false;
    }
}
