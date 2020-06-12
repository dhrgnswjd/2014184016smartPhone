package kr.ac.kpu.game.sdw.buldingbreakproject.util;

public class IndexTimer {
    private final int count;
    private final int fps;
    private final long time;

    public IndexTimer(int count , int framePerSecond){
        this.count = count;
        this.fps = framePerSecond;
        this.time = System.currentTimeMillis();
    }
    public int getIndex(){
        long elapsed = System.currentTimeMillis() - time;
        int index = (int)((elapsed * fps + 500) / 1000);
        return index % count;
    }
}
