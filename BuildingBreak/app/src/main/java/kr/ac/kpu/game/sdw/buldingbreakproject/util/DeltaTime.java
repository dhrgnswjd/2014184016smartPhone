package kr.ac.kpu.game.sdw.buldingbreakproject.util;

import android.util.Log;

public class DeltaTime {
    private float lastTime = System.nanoTime();

    public DeltaTime(){
    }
    public float getDeltaTime(){
        float time = System.nanoTime();
        int delta_time = (int)((time - lastTime)/1000);

        float d_t = (float)(delta_time*0.000001);
        //Log.d("delta","time" + d_t);
        lastTime = time;
        //Log.d(this.getClass().getName(),"deltatime = " + d_t);
        return d_t;
    }
}
