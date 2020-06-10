package kr.ac.kpu.game.sdw.buldingbreakproject.gameobj;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import kr.ac.kpu.game.sdw.buldingbreakproject.R;

public class Ball implements GameObject{
    private static Bitmap bitmap;
    private final float dx;
    private final float dy;
    private float y;
    private float x;

    public Ball(Resources res, float x, float y, float dx, float dy) {
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(res, R.mipmap.soccer_ball_240);
        }
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }

    public void update() {
        x += dx;
        y += dy;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, x, y, null);
    }
}
