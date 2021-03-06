package kr.ac.kpu.game.sdw.buldingbreakproject.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.sdw.buldingbreakproject.R;

public class OneBuildingBitmap {
    private static int width;
    private static Bitmap bitmap;
    private static int height;
    private static int h_Half;
    private static int w_Half;


    private OneBuildingBitmap(Resources res) {
        bitmap = BitmapFactory.decodeResource(res, R.mipmap.building3);
        height = bitmap.getHeight() / 5;
        width = bitmap.getWidth();

        h_Half = height/2;
        w_Half = width/2;


    }



    public static OneBuildingBitmap load(Resources res){

        OneBuildingBitmap bb= new OneBuildingBitmap(res);
        return bb;
    }
    public void draw(Canvas canvas, float x, float y, int _layer){
        int layer = 4-_layer;
        Rect rectSrc = new Rect(0,height*layer,width,height*(layer+1));
        RectF rectDst = new RectF(x - w_Half, y - h_Half, x+w_Half,y+h_Half);
        canvas.drawBitmap(bitmap,rectSrc, rectDst,null);

        //Log.d(this.getClass().getName(),"d"+layer);
    }

    public static int getHeight() {
        return height;
    }
    public static int getWidth(){
        return width;
    }
}
