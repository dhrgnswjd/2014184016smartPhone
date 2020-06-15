package kr.ac.kpu.game.sdw.buldingbreakproject.util;

import kr.ac.kpu.game.sdw.buldingbreakproject.gameobj.GameWorld;

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
        long elapsed = GameWorld.get().getCurrentTimeNanos();
        int index = (int)((elapsed * fps + 500000000) / 1000000000);
        return index % count;
    }
    public boolean done(){
        long elapsed = GameWorld.get().getCurrentTimeNanos();
        int index = (int)((elapsed * fps + 500000000) / 1000000000);
        return index >= count;
    }

    public void reset() {
        this.time = GameWorld.get().getCurrentTimeNanos();
    }
}
