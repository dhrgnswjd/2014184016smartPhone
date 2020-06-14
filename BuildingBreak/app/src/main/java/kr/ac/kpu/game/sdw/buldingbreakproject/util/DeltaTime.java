package kr.ac.kpu.game.sdw.buldingbreakproject.util;

import android.util.Log;

public class DeltaTime {
    private static float lastTime = System.nanoTime();
    public static DeltaTime singleton;

    public static DeltaTime get(){
        if(singleton == null)
            singleton = new DeltaTime();
        return singleton;
    }



    private DeltaTime(){
    }
    public float getDeltaTime(){
        long time = System.nanoTime();
        int delta_time = (int)((time - lastTime)/10000);
        float d_t = (float)(delta_time * 0.00001);
        lastTime = time;
        Log.d(this.getClass().getName(),"deltatime = " + d_t);
        return d_t;
    }
}
