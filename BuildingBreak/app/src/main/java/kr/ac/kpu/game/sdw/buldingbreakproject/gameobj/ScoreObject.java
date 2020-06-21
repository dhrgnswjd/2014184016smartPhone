package kr.ac.kpu.game.sdw.buldingbreakproject.gameobj;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import kr.ac.kpu.game.sdw.buldingbreakproject.R;
import kr.ac.kpu.game.sdw.buldingbreakproject.world.MainWorld;

public class ScoreObject implements GameObject{
    static private ScoreObject singleton;
    private static Bitmap bitmap;
    static private float x;
    static private float y;
    private static int width;
    private static int height;
    private static int scoreValue = 0;
    private static ObjectAnimator scoreAnimator;
    private static int scoreDisplay;
    private static Rect srcRect = new Rect();
    private static RectF dstRect = new RectF();
    private static float right,top;

    public static ScoreObject get(){
        if(singleton == null){
            singleton = new ScoreObject();
        }
        return singleton;
    }

    public static void initScoreObject(float _x, float _y){
        MainWorld mw = MainWorld.get();
        bitmap = BitmapFactory.decodeResource(mw.getResources(), R.mipmap.combo);
        width = bitmap.getWidth()/10;
        height = bitmap.getHeight();
        scoreAnimator = ObjectAnimator.ofInt(ScoreObject.get(),"scoreDisplay",0);
        x=_x;
        y=_y;
        right = x;
        top = y;
        srcRect.top = 0;
        srcRect.bottom = height;



    }
    private ScoreObject(){}

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        dstRect.right = right;
        dstRect.top = top;
        dstRect.left = right - width;
        dstRect.bottom = top + height;
        int value = scoreDisplay;
        while(value>0){
            int digit = value %10;
            srcRect.left = digit *width;
            srcRect.right = srcRect.left +width;
            canvas.drawBitmap(bitmap,srcRect,dstRect,null);
            value /= 10;
            dstRect.left -= width;
            dstRect.right -= width;
        }
    }
    public static void addScore(int score){
        scoreValue += score;
        scoreAnimator.setIntValues(scoreDisplay,scoreValue);
        scoreAnimator.setDuration(500);
        scoreAnimator.start();
        Log.d("scoreObject","score = " + scoreValue);
    }
    public void setScoreDisplay(int scoreDisplay){
        this.scoreDisplay = scoreDisplay;
    }
    public int getScore(){
        return scoreValue;
    }
    public void minusScore(int score){
        scoreValue -= score;
    }
}
