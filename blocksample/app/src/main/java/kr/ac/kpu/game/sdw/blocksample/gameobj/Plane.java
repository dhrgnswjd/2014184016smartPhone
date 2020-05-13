package kr.ac.kpu.game.sdw.blocksample.gameobj;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;

import kr.ac.kpu.game.sdw.blocksample.R;

public class Plane implements GameObject {
    private static Bitmap bitmap;
    private final float dy;
    private final float dx;
    private final Matrix matrix;
    private static int halfSize;
    private float x;
    private float y;

    public Plane(Resources res, float x, float y, float dx, float dy){
        if(bitmap == null) {
            bitmap = BitmapFactory.decodeResource(res, R.mipmap.plane_240);
            halfSize = bitmap.getHeight()/2;
        }
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.matrix = new Matrix();
        matrix.preTranslate(x - halfSize,y - halfSize);
    }
    public void update(){
        matrix.postRotate(5.0f,x,y);
    }
    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmap,matrix,null);
    }
}
