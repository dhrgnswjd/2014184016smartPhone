package kr.ac.kpu.game.sdw.blocksample.gameobj;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.sdw.blocksample.R;
import kr.ac.kpu.game.sdw.blocksample.util.FrameAnimationBitmap;
import kr.ac.kpu.game.sdw.blocksample.util.IndexTimer;

public class Ball implements GameObject {

    private static final int FRAMES_PER_SECOND = 6;
    private final FrameAnimationBitmap fab;
    private final int halfSize;
    private float dy;
    private float dx;
    private float x;
    private float y;

    public Ball(Resources res, float x, float y, float dx, float dy){
        fab = FrameAnimationBitmap.load(res,R.mipmap.fireball_128_24f,FRAMES_PER_SECOND,0);
        halfSize = fab.getHeight()/2;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }
    public void update(){
        GameWorld gw = GameWorld.get();

        x += dx;
        if(dx > 0 && x > gw.getRight()-halfSize || dx < 0 && x < gw.getLeft()+halfSize){
            dx *= -1;
        }
        y += dy;
        if(dy > 0 && y > gw.getBottom() - halfSize|| dy < 0 && y < gw.getTop()+halfSize){
            dy *= -1;
        }
    }
    public void draw(Canvas canvas) {
        fab.draw(canvas,x,y);
    }
}
