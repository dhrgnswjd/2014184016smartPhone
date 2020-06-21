package kr.ac.kpu.game.sdw.buldingbreakproject.gameobj;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

import java.util.ArrayList;

import kr.ac.kpu.game.sdw.buldingbreakproject.R;
import kr.ac.kpu.game.sdw.buldingbreakproject.util.DeltaTime;
import kr.ac.kpu.game.sdw.buldingbreakproject.util.FrameAnimationBitmap;
import kr.ac.kpu.game.sdw.buldingbreakproject.world.MainWorld;

public class Character implements GameObject,BoxCollidable{
    public static final int FRAME_PER_SECOND = 20;
    private static final float JUMP_POWER = -1500;
    private static final float USER_GRAVITY = 1000;
    private FrameAnimationBitmap fab;
    private final FrameAnimationBitmap fabIdle;
    private final FrameAnimationBitmap fabJump;
    private final FrameAnimationBitmap fabAttack_stand;
    private final FrameAnimationBitmap fabAttack1_jump;
    private final FrameAnimationBitmap fabAttack2_jump;
    private final FrameAnimationBitmap fabPower;
    private final FrameAnimationBitmap fabShield;
    private final FrameAnimationBitmap fabPower_Jump;
    private final int height;
    private final int h_half;
    private final int width;
    private final int w_half;
    private static float land;
    private static float time;
    private static float speed;


    private static boolean jumping = false;
    private static boolean attacking = false;


    private float y;
    private float x;
    int halfSize;
    MainWorld gw = MainWorld.get();
    private static ArrayList<GameObject> buildingLayer;
    private DeltaTime dt = new DeltaTime();

    @Override
    public void getBox(RectF rect) {
        if(attacking) {
            rect.top = y - height*0.75f;
        }
        else {
            rect.left = x - w_half;
            rect.right = x + w_half;
            rect.bottom = y + h_half;
            rect.top = y - h_half;
        }
    }

    public State getState(){
        return state;
    }


    public void dgreeLife() {
    }

    public void setJumpPower(float speed) {
        this.speed = speed + 10;
    }

    public boolean doneAttack() {
        return fab.doneAttack();
    }


    public static enum State{
        jump,idle,attack_stand,attack1_jump,attack2_jump,shield,power,power_jump
    }
    private static State state = State.idle;

    private RectF character_Jump;

    public Character() {
        Resources res = gw.getResources();
        fabIdle = FrameAnimationBitmap.load(res, R.mipmap.character, 1,1,0);
        fabJump = FrameAnimationBitmap.load(res, R.mipmap.character, 1,1,2);
        fabAttack_stand = FrameAnimationBitmap.load(res, R.mipmap.character, FRAME_PER_SECOND,5,0);
        fabAttack1_jump = FrameAnimationBitmap.load(res, R.mipmap.character, FRAME_PER_SECOND,5,2);
        fabAttack2_jump = FrameAnimationBitmap.load(res, R.mipmap.character, FRAME_PER_SECOND,5,3);
        fabPower = FrameAnimationBitmap.load(res, R.mipmap.character, 1,1,1);
        fabShield = FrameAnimationBitmap.load(res, R.mipmap.character, 1,1,4);
        fabPower_Jump = FrameAnimationBitmap.load(res, R.mipmap.character, FRAME_PER_SECOND,5,1);

        height = fabIdle.getHeight();
        width = fabIdle.getWidth();
        w_half = width/2;
        h_half = height/2;
        halfSize = fabIdle.getHeight()/2;
        this.y = gw.getLand(h_half) - 200;
        this.x = gw.getRight()/2;
        land = this.y;
        fab = fabIdle;

        buildingLayer = gw.getobjectAt(MainWorld.Layer.building);

    }

    public void update() {
        time = dt.getDeltaTime();
        //Log.d(this.getClass().getName(),"time" + time);
        speed += USER_GRAVITY*time;
        y += speed * time;

        if( y >= land){//땅
            y = land;
            jumping = false;
            if(!attacking){//노말
                setAnimState(State.idle);

            }else{//공격
                setAnimState(State.attack_stand);
                if(fab.done()){
                    fab.reset();
                    attacking = false;
                }
            }
        }else{//점프

            if(!attacking){//노말
                setAnimState(State.jump);
                jumping = true;
            }else{//공격
                setAnimState(State.attack1_jump);
                if(fab.done()){
                    attacking = false;
                    fab.reset();
                }
            }
        }

    }

    public void draw(Canvas canvas) {

        fab.draw(canvas,x,y);

    }
    public void setAnimState(State state) {
        this.state = state;

        switch(state){
            case idle:
                fab = fabIdle;
                break;
            case jump:
                fab = fabJump;
                //Log.d(this.getClass().getName(), state.name());
                break;
            case attack1_jump:
                fab = fabAttack1_jump;

                break;
            case attack2_jump:
                fab = fabAttack2_jump;
                break;
            case attack_stand:
                fab = fabAttack_stand;
                break;
            case shield:
                fab = fabShield;
                break;
            case power:
                fab = fabPower;
                break;
            case power_jump:
                fab = fabPower_Jump;
                break;
            default:

                break;
        }
    }

    public void attack() {
        Log.d(this.getClass().getName(),"jump");
        if(!attacking) {
            attacking = true;
            fabAttack_stand.reset();
            fabAttack1_jump.reset();

        }
    }
    public void jump() {
        if(!jumping) {

            speed += JUMP_POWER;
            jumping = true;

            if (speed > JUMP_POWER) {
                speed = JUMP_POWER;
            }
        }

    }

}
