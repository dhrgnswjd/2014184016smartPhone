package kr.ac.kpu.game.sdw.buldingbreakproject.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.Image;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Choreographer;
import android.view.View;

import androidx.annotation.Nullable;

import kr.ac.kpu.game.sdw.buldingbreakproject.gameobj.GameWorld;
import kr.ac.kpu.game.sdw.buldingbreakproject.util.IndexTimer;

public class GameView extends View {
    private Rect mainRect;
    private Paint mainPaint;

    private boolean moves;
    private GameWorld gameWorld;
    private IndexTimer timer;


    public GameView(Context context) {
        super(context);
    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initResources();
    }

    private void initResources() {
        mainPaint = new Paint();
        mainPaint.setColor(0xFFFFEEEE);

        mainRect = new Rect();
        gameWorld = GameWorld.get();

        timer = new IndexTimer(10,1);
        postFrameCallBack();

    }

    private void postFrameCallBack() {
        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
            @Override
            public void doFrame(long frameTimeNanos) {
                update(frameTimeNanos);
                invalidate();
                postFrameCallBack();
            }
        });
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mainRect.left = getPaddingLeft();
        mainRect.top = getPaddingTop();
        mainRect.right = getWidth() - getPaddingRight();
        mainRect.bottom = getHeight() - getPaddingBottom();

        gameWorld.setRect(mainRect);
        gameWorld.initResources(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(mainRect,mainPaint);
        gameWorld.draw(canvas);


    }

    private int count;
    public void update(long frameTimeNanos) {

        gameWorld.update(frameTimeNanos);
        count ++;
        if(timer.done()){
            Log.d(this.getClass().getName(),"FrameTime = " + count);
            count = 0;
            timer.reset();
        }

    }

    public void doAction() {
        moves = !moves;
    }
}
