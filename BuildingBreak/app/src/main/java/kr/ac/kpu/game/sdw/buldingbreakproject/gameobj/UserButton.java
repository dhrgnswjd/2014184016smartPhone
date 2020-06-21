package kr.ac.kpu.game.sdw.buldingbreakproject.gameobj;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

import android.util.Log;
import android.view.MotionEvent;


import kr.ac.kpu.game.sdw.buldingbreakproject.util.CollisionHelper;
import kr.ac.kpu.game.sdw.buldingbreakproject.world.MainWorld;

public class UserButton implements GameObject,BoxCollidable,Touchable{
    private final String TAG = this.getClass().getName();
    private Bitmap bitmap;
    private int height;
    private int width;
    private int user_height;
    private int user_width;
    private RectF dstRect;
    private float x;
    private float y;
    private boolean capturing, pressed;
    private Runnable onClickRunnable;
    private boolean runOnDown;

    public UserButton(float x, float y, int _width, int _height, int resId) {
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
        //rect = new RectF(x-user_width*0.5f,y-user_height*0.5f,x+user_width*0.5f,y+user_height*0.5f);
        rect.left = x-user_width*0.5f;
        rect.right = x+user_width*0.5f;
        rect.bottom = y+user_height*0.5f;
        rect.top = y-user_height*0.5f;
        //Log.d(TAG,"x = " + rect.left);
    }

    @Override
    public void getBox(RectF rect, int x) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        CollisionHelper ch = new CollisionHelper();
        if(e.getAction() == MotionEvent.ACTION_DOWN){
            if(ch.collides(this,e.getX(),e.getY())){
                capturing = true;
                pressed = true;

                if (onClickRunnable != null && runOnDown) {
                    onClickRunnable.run();
                }
                return true;
            }
        }
        //Log.d(TAG,"down");
        //Log.d(TAG,"x = " + e.getX() + "y = " + e.getY());

        return false;
    }
    public void setOnClickRunnable(Runnable runnable) {
        setOnClickRunnable(false, runnable);
    }
    public void setOnClickRunnable(boolean runOnDown, Runnable runnable) {
        this.runOnDown = runOnDown;
        this.onClickRunnable = runnable;
    }
}

