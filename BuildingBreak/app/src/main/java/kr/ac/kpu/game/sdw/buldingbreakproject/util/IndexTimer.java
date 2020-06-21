package kr.ac.kpu.game.sdw.buldingbreakproject.util;

import android.util.Log;

import kr.ac.kpu.game.sdw.buldingbreakproject.framework.GameWorld;

public class IndexTimer {
    private final int count;
    private final int fps;
    private long time;

    public IndexTimer(int count , int framePerSecond){
        this.count = count;
        this.fps = framePerSecond;
        this.time = GameWorld.get().getCurrentTimeNanos();
    }
    public int getIndex(){
        /*long elapsed = GameWorld.get().getCurrentTimeNanos();
        int index = (int)((elapsed * fps + 500000000) / 1000000000);
        return index % count;*/
        int index = getRawIndex();
        //Log.d("this", "this" + index%count);
        return index % count;
    }

    public int getRawIndex() {
        long elapsed = GameWorld.get().getCurrentTimeNanos() - this.time;

        return (int) (((elapsed * fps + 500) / 1000000000));
    }

    public boolean done(){
        /*int i = getIndex();
        Log.d(this.getClass().getName(),"false" + i + " "+ count );
        return i >= (count-1);*/
        int index = getIndex();
        return index >= count-1;
    }
    public boolean doneAttack(){
        /*int i = getIndex();
        Log.d(this.getClass().getName(),"false" + i + " "+ count );
        return i >= (count-1);*/
        int index = getIndex();
        return index == (count-1)/2;
    }

    public void reset() {
        this.time = GameWorld.get().getCurrentTimeNanos();
    }
}
