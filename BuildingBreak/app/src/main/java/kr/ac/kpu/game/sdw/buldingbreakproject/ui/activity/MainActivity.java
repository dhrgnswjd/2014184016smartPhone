package kr.ac.kpu.game.sdw.buldingbreakproject.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;

import kr.ac.kpu.game.sdw.buldingbreakproject.R;
import kr.ac.kpu.game.sdw.buldingbreakproject.ui.view.GameView;
import kr.ac.kpu.game.sdw.buldingbreakproject.util.SoundEffects;
import kr.ac.kpu.game.sdw.buldingbreakproject.world.MainWorld;

public class MainActivity extends AppCompatActivity {

    private static final long GAMEVIEW_UPDATE_INTERVAL_MSEC = 30;
    private GameView gameView;
    private static MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        MainWorld.create();
        SoundEffects se = SoundEffects.get();
        se.init(this);
        se.loadAll();
        mp = MediaPlayer.create(this,R.raw.bgm);
        mp.setLooping(true);
        mp.start();
        gameView = new GameView(this);

        setContentView(gameView);


    }


    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            gameView.doAction();
        }
        return true;
    }
}