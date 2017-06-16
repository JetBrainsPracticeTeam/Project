package com.llaminator.ghostline;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Random;


public class Route {

    final static int Dirs = 2;
    static int Size = 100;
    ArrayList<RouteStep> Level = new ArrayList<RouteStep>();

    public Route() {
        InitLevel();
    }



    public void InitLevel() {
        Random randomGenerator = new Random();

        for(int i = 0; i < Size; i++) {
            Level.add(i, new RouteStep(new Texture(Gdx.files.internal("routeImg.png")), randomGenerator.nextInt(Dirs)));
        }
    }

    public void ExpandLevel() {
        Random randomGenerator = new Random();
        Level.add(new RouteStep(new Texture(Gdx.files.internal("routeImg.png")), randomGenerator.nextInt(Dirs)));
    }

}
