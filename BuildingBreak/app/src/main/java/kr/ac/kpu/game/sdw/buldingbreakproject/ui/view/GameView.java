package kr.ac.kpu.game.sdw.buldingbreakproject.ui.view;

import android.app.Service;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import kr.ac.kpu.game.sdw.buldingbreakproject.R;
import kr.ac.kpu.game.sdw.buldingbreakproject.framework.GameWorld;
import kr.ac.kpu.game.sdw.buldingbreakproject.util.IndexTimer;

public class GameView extends View {
    private Rect mainRect;
    private Paint mainPaint;

    private boolean moves;
    private GameWorld gameWorld;
    private IndexTimer timer;
    Button btn;

    public GameView(Context context) {
        super(context);
        initResources();
    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        AttributeSet attributeSet;
        btn = new Button(context,attrs);

    }

    private void initResources() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Service.WINDOW_SERVICE);
        Point size = new Point();
        wm.getDefaultDisplay().getSize(size);
        mainRect = new Rect(0,0,size.x,size.y);

        mainPaint = new Paint();
        mainPaint.setColor(0xFFFFEEEE);


        gameWorld = GameWorld.get();
        gameWorld.setRect(mainRect);
        gameWorld.initResources(this);


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


   /* @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mainRect.left = getLeft();
        mainRect.top = getPaddingTop();
        mainRect.right = getWidth() - getPaddingRight();
        mainRect.bottom = getHeight() - getPaddingBottom();

        gameWorld.setRect(mainRect);

    }*/

    @Override
    protected void onDraw(Canvas canvas) {
        //canvas.drawRect(mainRect,mainPaint);
        gameWorld.draw(canvas);


    }

    private int count;
    public void update(long frameTimeNanos) {

        gameWorld.update(frameTimeNanos);
        count ++;
        /*if(timer.done()){
            //Log.d(this.getClass().getName(),"FrameTime = " + count);
            count = 0;
            timer.reset();
        }*/

    }

    public void doAction() {
        moves = !moves;
    }
    public boolean onTouchEvent(MotionEvent event) {
        return gameWorld.onTouchEvent(event);
    }
}
