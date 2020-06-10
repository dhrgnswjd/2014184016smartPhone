package kr.ac.kpu.game.sdw.buldingbreakproject.gameobj;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.ArrayList;

import kr.ac.kpu.game.sdw.buldingbreakproject.R;

public class BuildingLayer implements GameObject{

    private ArrayList<Building> building;


    public BuildingLayer(Resources res,int layer) {
        building = new ArrayList<>();
        for(int i = 0 ; i < layer; i++){
            building.add(new Building(res,i));
        }

    }

    public void update() {

    }

    public void draw(Canvas canvas) {
        for(Building o : building){
            o.draw(canvas);
        }
    }
}
