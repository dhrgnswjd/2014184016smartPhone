package kr.ac.kpu.game.sdw.buldingbreakproject.gameobj;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;


import kr.ac.kpu.game.sdw.buldingbreakproject.world.MainWorld;

public class BitmapObject implements GameObject,BoxCollidable{
    private Bitmap bitmap;
    private int height;
    private int width;
    private int user_height;
    private int user_width;
    private RectF dstRect;
    private float x;
    private float y;
    public BitmapObject(float x, float y, int _width, int _height, int resId) {
        MainWorld mw = MainWorld.get();
        bitmap = BitmapFactory.decodeResource(mw.getResources(), resId);
        this.height = bitmap.getHeight();
        this.width = bitmap.getWidth();
        user_height = _height;
        user_width = _width;
        this.x = x;
        this.y = y;
        this.dstRect = new RectF(x - user_width * 0.5f, y - user_height * 0.5f, x + user_width * 0.5f, y + user_height * 0.5f);
    }
    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap,null,dstRect,null);
    }

    @Override
    public void getBox(RectF rect) {
        rect = new RectF(x-user_width*0.5f,y-user_height*0.5f,x+user_width*0.5f,y+user_height*0.5f);
    }
}
