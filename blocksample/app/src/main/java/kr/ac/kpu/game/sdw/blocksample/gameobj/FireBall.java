package kr.ac.kpu.game.sdw.blocksample.gameobj;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.Log;

import kr.ac.kpu.game.sdw.blocksample.R;
import kr.ac.kpu.game.sdw.blocksample.util.FrameAnimationBitmap;

public class FireBall implements GameObject {

    private static final int FRAMES_PER_SECOND = 10;
    private static final int FRAME_COUNT_FIRE = 2;
    private static final int FRAME_COUNT_FLY = 6;
    private static final String TAG = FireBall.class.getSimpleName();
    private final FrameAnimationBitmap fabFire;
    private final FrameAnimationBitmap fabFly;


    private final int speed;

    private float x;
    private float y;

    public void fire() {
        if(state != State.fire){
            return;
        }
        state = State.fly;
        fabFly.reset();
    }

    private enum State {
        fire, fly
    }

    private State state = State.fire;

    public FireBall(float x, float y, int speed) {
        GameWorld gw = GameWorld.get();
        Resources res = gw.getResources();
        fabFire = FrameAnimationBitmap.load(res, R.mipmap.hadoken1, FRAMES_PER_SECOND, FRAME_COUNT_FIRE);
        fabFly = FrameAnimationBitmap.load(res, R.mipmap.hadoken2, FRAMES_PER_SECOND, FRAME_COUNT_FLY);
        this.x = x;
        this.y = y;
        this.speed = speed;

    }

    public void update() {
        x += speed;
        GameWorld gw = GameWorld.get();


        if (x > gw.getRight() + 10) {
            gw.remove(this);
            return;
        }
        if (state == State.fire) {
            boolean done = fabFire.done();
            if (done) {
                state = State.fly;
                fabFly.reset();
            }
        }
    }

    public void draw(Canvas canvas) {
        if (state == State.fire)
            fabFire.draw(canvas, x, y);
        else
            fabFly.draw(canvas, x, y);
    }
}
