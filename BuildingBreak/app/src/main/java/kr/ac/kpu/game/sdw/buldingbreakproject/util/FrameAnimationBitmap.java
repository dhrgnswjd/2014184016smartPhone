package kr.ac.kpu.game.sdw.buldingbreakproject.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import java.util.HashMap;

public class FrameAnimationBitmap {
    private int layer;
    private Bitmap bitmap;
    private int height;
    private int frameCount;

    private IndexTimer indexTimer;

    private FrameAnimationBitmap(Resources res, int resId,int count, int layer){
        bitmap = BitmapFactory.decodeResource(res,resId);
        height = bitmap.getHeight()/5;
        int width = bitmap.getWidth();
        frameCount = count;
        this.layer = layer;


    }



    public static FrameAnimationBitmap load(Resources res, int resId, int framePerSecond,int count, int layer){

        FrameAnimationBitmap fab= new FrameAnimationBitmap(res,resId,count,layer);



        fab.indexTimer = new IndexTimer(fab.frameCount, framePerSecond);
        return fab;
    }
    public void draw(Canvas canvas, float x, float y){
        int index = indexTimer.getIndex();
        int size = height;
        int halfSize = height/2;
        Rect rectSrc = new Rect(size*index,size*layer,(size * (index+1)),size*(layer+1));
        RectF rectDst = new RectF(x - halfSize, y - halfSize, x+halfSize,y+halfSize);
        canvas.drawBitmap(bitmap,rectSrc, rectDst,null);

        //Log.d(this.getClass().getName(),"d"+layer);
    }

    public int getHeight() {
        return height;
    }
}
