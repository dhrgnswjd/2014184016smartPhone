package kr.ac.kpu.game.sdw.buldingbreakproject.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MotionEvent;

import kr.ac.kpu.game.sdw.buldingbreakproject.R;
import kr.ac.kpu.game.sdw.buldingbreakproject.ui.view.GameView;
import kr.ac.kpu.game.sdw.buldingbreakproject.world.MainWorld;

public class MainActivity extends AppCompatActivity {

    private static final long GAMEVIEW_UPDATE_INTERVAL_MSEC = 30;
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        MainWorld.create();
        gameView = new GameView(this);

        setContentView(gameView);

        //postUpdate();

    }

    /*private void postUpdate() {
        gameView.postDelayed((new Runnable() {
            @Override
            public void run() {
                gameView.update();
                gameView.invalidate();
                postUpdate();
            }
        }),GAMEVIEW_UPDATE_INTERVAL_MSEC);
    }*/

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