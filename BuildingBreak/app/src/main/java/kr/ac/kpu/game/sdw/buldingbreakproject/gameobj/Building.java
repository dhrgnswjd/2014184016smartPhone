package kr.ac.kpu.game.sdw.buldingbreakproject.gameobj;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.Log;

import java.sql.Time;
import java.util.ArrayList;

import kr.ac.kpu.game.sdw.buldingbreakproject.util.DeltaTime;
import kr.ac.kpu.game.sdw.buldingbreakproject.util.OneBuildingBitmap;

public class Building implements GameObject{


    private static final int ONE_BUILDING_LAYER = 5;
    private static final float USER_GRAVITY = 500;
    private final ArrayList<OneBuildingBitmap> oneBuilding = new ArrayList<>();
    private final int layer;
    private float x;
    private float y;
    private static int width;
    private static int height;
    private static float w_Half;
    private static float h_Half;
    private float speed = 0;

    GameWorld gw = GameWorld.get();

    public Building(Resources res) {
        this.layer = ONE_BUILDING_LAYER;
        for(int i = 0 ; i < ONE_BUILDING_LAYER; i++){
            oneBuilding.add(OneBuildingBitmap.load(res,i));

        }
        width = OneBuildingBitmap.getWidth();
        height = OneBuildingBitmap.getHeight();
        w_Half = width/2;
        h_Half = height/2;
        this.y = gw.getTop();
        this.x = gw.getRight()/2;
    }

    public void update() {
        DeltaTime dt = DeltaTime.get();
        float time = dt.getDeltaTime();
        speed += time * USER_GRAVITY;
        y += speed * time;
    }

    public void draw(Canvas canvas) {


        for(int i = 0; i < ONE_BUILDING_LAYER; i++){
            oneBuilding.get(i).draw(canvas,x ,y - i*height);
        }
    }
}
