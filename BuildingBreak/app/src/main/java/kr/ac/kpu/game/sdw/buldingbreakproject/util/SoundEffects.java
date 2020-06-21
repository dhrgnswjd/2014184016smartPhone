package kr.ac.kpu.game.sdw.buldingbreakproject.util;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import java.util.HashMap;

import kr.ac.kpu.game.sdw.buldingbreakproject.R;

public class SoundEffects {
    private static SoundEffects singleton;
    private Context context;
    private AudioAttributes audioAttributes;
    private SoundPool soundPool;

    public static SoundEffects get(){
        if(singleton == null){
            singleton = new SoundEffects();
        }
        return singleton;
    }

    public void init(Context context){
        this.context = context;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();
            this.soundPool = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .setMaxStreams(3)
                    .build();
        }else{
            this.soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC,0);
        }

    }
    private static final int[] SOUND_IDS = {
            R.raw.jump,R.raw.bougyo,R.raw.slash,R.raw.tubure,R.raw.waza,R.raw.slash2,R.raw.bgm
    };
    private HashMap<Integer,Integer> soundIdMap = new HashMap<>();
    public void loadAll(){
        for(int resID: SOUND_IDS){
            int soundId = soundPool.load(context,resID,1);
            soundIdMap.put(resID,soundId);
        }
    }
    public int play(int resId){
        int soundId = soundIdMap.get(resId);
        int streamId = soundPool.play(soundId,1f,1f,1,0,1f);
        return  streamId;
    }
    public int playbgm(int resId){
        int soundId = soundIdMap.get(resId);
        int streamId = soundPool.play(soundId,1f,1f,1,1,1f);
        return  streamId;
    }
}
