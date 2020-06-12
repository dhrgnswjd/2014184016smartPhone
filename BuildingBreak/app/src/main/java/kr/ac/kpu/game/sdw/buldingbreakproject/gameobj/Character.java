package kr.ac.kpu.game.sdw.buldingbreakproject.gameobj;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import kr.ac.kpu.game.sdw.buldingbreakproject.R;
import kr.ac.kpu.game.sdw.buldingbreakproject.util.FrameAnimationBitmap;
import kr.ac.kpu.game.sdw.buldingbreakproject.util.IndexTimer;

public class Character implements GameObject{
    public static final int FRAME_PER_SECOND = 6;
    private final FrameAnimationBitmap fabIdle;
    private final FrameAnimationBitmap fabJump;
    private final FrameAnimationBitmap fabAttack_stand;
    private final FrameAnimationBitmap fabAttack1_jump;
    private final FrameAnimationBitmap fabAttack2_jump;
    private final FrameAnimationBitmap fabPower;
    private final FrameAnimationBitmap fabShield;
    private final FrameAnimationBitmap fabPower_Jump;


    private float y;
    private float x;
    int halfSize;
    GameWorld gw = GameWorld.get();
    private enum State{
        jump,idle,attack_stand,attack1_jump,attack2_jump,shield,power,power_jump
    }
    private State state = State.idle;

    private RectF character_Jump;

    public Character(Resources res) {
        //FabInit(res);
        fabIdle = FrameAnimationBitmap.load(res, R.mipmap.character, FRAME_PER_SECOND,1,0);
        fabJump = FrameAnimationBitmap.load(res, R.mipmap.character, FRAME_PER_SECOND,1,2);
        fabAttack_stand = FrameAnimationBitmap.load(res, R.mipmap.character, FRAME_PER_SECOND,5,0);
        fabAttack1_jump = FrameAnimationBitmap.load(res, R.mipmap.character, FRAME_PER_SECOND,5,2);
        fabAttack2_jump = FrameAnimationBitmap.load(res, R.mipmap.character, FRAME_PER_SECOND,5,3);
        fabPower = FrameAnimationBitmap.load(res, R.mipmap.character, FRAME_PER_SECOND,1,1);
        fabShield = FrameAnimationBitmap.load(res, R.mipmap.character, FRAME_PER_SECOND,1,4);
        fabPower_Jump = FrameAnimationBitmap.load(res, R.mipmap.character, FRAME_PER_SECOND,5,1);

        halfSize = fabIdle.getHeight()/2;

    }

    public void update() {

    }

    public void draw(Canvas canvas) {
        this.y = gw.getBottom() - 200;
        this.x = (gw.getRight()+gw.getLeft()-halfSize)*0.5f;
        switch(state){
            case idle:
                fabIdle.draw(canvas,x,y);
                break;
            case jump:
                fabJump.draw(canvas,x,y);
                //Log.d(this.getClass().getName(), state.name());
                break;
            case attack1_jump:
                fabAttack1_jump.draw(canvas,x,y);

                break;
            case attack2_jump:
                fabAttack2_jump.draw(canvas,x,y);
                break;
            case attack_stand:
                fabAttack_stand.draw(canvas,x,y);
                break;
            case shield:
                fabShield.draw(canvas,x,y);
                break;
            case power:
                fabPower.draw(canvas,x,y);
                break;
            case power_jump:
                fabPower_Jump.draw(canvas,x,y);
                break;
            default:

                break;
        }


    }

}
