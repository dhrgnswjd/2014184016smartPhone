package kr.ac.kpu.game.sdw.blocksample.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import kr.ac.kpu.game.sdw.blocksample.R;
import kr.ac.kpu.game.sdw.blocksample.gameobj.Ball;
import kr.ac.kpu.game.sdw.blocksample.gameobj.GameObject;
import kr.ac.kpu.game.sdw.blocksample.gameobj.GameWorld;
import kr.ac.kpu.game.sdw.blocksample.gameobj.Plane;

public class GameView extends View {
    private Rect mainRect;
    private Paint mainPaint;

    private boolean moves;
    private ArrayList<GameObject> object;
    private GameWorld gameWorld;

    public GameView(Context context) {
        super(context);

        initResource();
    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initResource();
    }

    private void initResource() {
        mainPaint = new Paint();
        mainPaint.setColor(0xFFFFEEEE);

        mainRect = new Rect(10,30,100,400);
        gameWorld = GameWorld.get();
        gameWorld.initResources(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mainRect.left = getPaddingLeft();
        mainRect.top = getPaddingTop();
        mainRect.right = getWidth() - getPaddingRight();
        mainRect.bottom = getHeight() - getPaddingBottom();

        gameWorld.setRect(mainRect);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(mainRect,mainPaint);
        gameWorld.draw(canvas);
    }

    public void update() {

        gameWorld.update();

    }

    public void doAction() {
        gameWorld.doAction();
    }
}
