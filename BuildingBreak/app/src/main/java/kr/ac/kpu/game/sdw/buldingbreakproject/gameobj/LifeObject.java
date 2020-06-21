package kr.ac.kpu.game.sdw.buldingbreakproject.gameobj;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.sdw.buldingbreakproject.R;
import kr.ac.kpu.game.sdw.buldingbreakproject.world.MainWorld;

public class LifeObject implements GameObject{
    private static final int MAX_LIFE = 5;
    private final Bitmap bitmap;
    private final int height;
    private final int width;
    private final float y;
    private float x;
    private Rect srcRect = new Rect();
    private RectF dstRect = new RectF();
    private int life;


    public LifeObject(float x, float y){
        MainWorld mw = MainWorld.get();
        mw.getResources();
        bitmap = BitmapFactory.decodeResource(mw.getResources(), R.mipmap.life);
        height = bitmap.getHeight();
        width = bitmap.getWidth();
        this.life = MAX_LIFE;
        srcRect.top = 0;
        srcRect.bottom = height;
        dstRect.top = y - height/2;
        dstRect.bottom = y + height/2;
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        for(int i = 0 ; i < MAX_LIFE; i++){
            if(i < life) {
                srcRect.left = 0;
                srcRect.right = width/2;

            }
            else{
                srcRect.left = width/2;
                srcRect.right = width;
            }
            float _x = x,_y =y;
            _x += i*width;
            dstRect.right = _x + width/2;
            dstRect.left = _x - width/2;

            canvas.drawBitmap(bitmap, srcRect, dstRect, null);
        }

    }
    public void setLife(int life){
        this.life = life;
    }
}
