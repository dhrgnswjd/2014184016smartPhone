package kr.ac.kpu.game.sdw.buldingbreakproject.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.Image;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import kr.ac.kpu.game.sdw.buldingbreakproject.gameobj.GameWorld;

public class GameView extends View {
    private Rect mainRect;
    private Paint mainPaint;

    private boolean moves;
    private GameWorld gameWorld;


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



    }




    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mainRect.left = getPaddingLeft();
        mainRect.top = getPaddingTop();
        mainRect.right = getWidth() - getPaddingRight();
        mainRect.bottom = getHeight() - getPaddingBottom();

        gameWorld.setRect(mainRect);
        gameWorld.initResources(getResources());
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
        moves = !moves;
    }
}
