package kr.ac.kpu.game.sdw.blocksample.util;

public class IndexTimer {
    private final int count;
    private final int fps;
    private long time;
    private long elapsed;

    public IndexTimer(int count, int framePerSecond) {
        this.count = count;
        this.fps = framePerSecond;
        this.time = System.currentTimeMillis();
    }
    public int getIndex() {
        elapsed = System.currentTimeMillis() - time;
        int index = (int) ((elapsed * fps + 500) / 1000);
        return index % count;
    }

    public void reset() {
        this.time = System.currentTimeMillis();
    }

    public boolean done() {
        elapsed = System.currentTimeMillis() - time;
        int index = (int) ((elapsed * fps + 500) / 1000);
        return index >= count;
    }
}
