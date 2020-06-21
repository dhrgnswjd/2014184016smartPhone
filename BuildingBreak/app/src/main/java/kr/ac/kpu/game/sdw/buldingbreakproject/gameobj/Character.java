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
    private static final float USER_GRAVITY = 1200;
    private static final float STATE_TIME = 10;
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
    private float land;
    private float time;
    private float speed;
    private float stateTime = 0;
    private float lifeCoolTime = 0;
    private int jumpCount = 0;


    private boolean jumping = false;
    private boolean attacking = false;
    private boolean shileding = false;
    private boolean powering = false;
    private boolean specialKey = false;
    private boolean lifeCooling = false;

    private static int life = 5;
    private float y;
    private float x;
    int halfSize;
    MainWorld gw = MainWorld.get();
    private static ArrayList<GameObject> buildingLayer;
    private DeltaTime dt = new DeltaTime();
    ScoreObject so = ScoreObject.get();


    @Override
    public void getBox(RectF rect) {
        if(attacking == true){
            rect.top = y - h_half * 1.5f;
        }else {
            rect.left = x - w_half;
            rect.right = x + w_half;
            rect.bottom = y + h_half;
            rect.top = y - h_half * 0.3f;
        }

    }

    public State getState(){
        return state;
    }


    public void dgreeLife() {
        if(lifeCooling == false) {
            life--;
            lifeCooling = true;
            if(life <= 0){
                life = 0;
            }
        }
        gw.setLife(life);
    }

    public void setJumpPower(float speed) {
        if(speed < 0)
            this.speed = -speed;
        else
            this.speed = speed;
    }

    public boolean doneAttack() {
        return fab.doneAttack();
    }
    public boolean done(){

        return fab.done();

    }
    public boolean standAttackdone(){
        return fabAttack_stand.done();
    }
    public boolean jumpAttackdone(){
        return fabAttack1_jump.done();
    }

    public void reset() {
        fab.reset();
        attacking = false;
    }

    public boolean getAttacking() {
        return attacking;
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
        fabPower_Jump = FrameAnimationBitmap.load(res, R.mipmap.character, 7,5,1);

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
        if(lifeCooling == true) {
            lifeCoolTime += time * 1;
            if(lifeCoolTime >= 3){
                lifeCoolTime = 0;
                lifeCooling = false;
            }
        }
        if(specialKey == false) {

            if (y >= land) {//땅
                y = land;
                jumping = false;
                jumpCount = 0;
                if (!attacking) {//노말
                    setAnimState(State.idle);

                } else {//공격
                    setAnimState(State.attack_stand);
                    if (fab.done()) {
                        fab.reset();
                        attacking = false;

                    }
                    gw.setAttacking(fab.done());
                }
            } else {//점프

                if (!attacking) {//노말
                    setAnimState(State.jump);
                    jumping = true;
                } else {//공격
                    setAnimState(State.attack1_jump);
                    if (fab.done()) {
                        attacking = false;
                        fab.reset();
                    }
                }
            }
        }else{
            if (y >= land) {//땅
                y = land;
                jumping = false;
            }
            if(shileding == true){
                setAnimState(State.shield);
                stateTime += time*STATE_TIME;
                if(stateTime >= STATE_TIME){
                    shileding = false;
                    specialKey = false;
                }
            }
            if(powering == true){
                setAnimState(State.power_jump);
                stateTime += time*STATE_TIME;
                if(stateTime >= STATE_TIME){
                    powering = false;
                    specialKey = false;
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
        //Log.d(this.getClass().getName(),"jump");
        if(attacking == false && specialKey ==false) {
            attacking = true;
            fabAttack_stand.reset();
            fabAttack1_jump.reset();


        }
    }
    public void jump() {
        if(specialKey ==false && jumpCount < 2) {

            speed = JUMP_POWER;
            jumping = true;
            jumpCount++;
            /*if (speed > JUMP_POWER) {
                speed = JUMP_POWER;

            }*/
        }

    }

    public void shield() {
        int score = so.getScore();
        if(shileding == false && specialKey ==false && powering == false&& score >= 100) {
            stateTime = 0;
            shileding = true;
            specialKey = true;
            fabShield.reset();
            so.minusScore(100);
        }
    }
    public void power() {
        int score = so.getScore();
        if(powering == false && specialKey ==false && shileding == false && score>= 1000) {
            stateTime = 0;
            powering = true;
            specialKey = true;
            fabPower_Jump.reset();
            speed = -2000;
            so.minusScore(1000);
        }
    }

}
