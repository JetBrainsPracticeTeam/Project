package com.llaminator.ghostline;

import com.badlogic.gdx.graphics.Texture;

public class RouteStep {
    Texture Step;
    int Dir;

    public RouteStep(Texture step, int dir) {
        Step = step;
        Dir = dir;
    }


    public void setStep(Texture step) {
        Step = step;
    }

    public Texture getStep() {
        return Step;
    }

    public void setDir(int dir) {
        Dir = dir;
    }

    public int getDir() {
        return Dir;
    }
}
