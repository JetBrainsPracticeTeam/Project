package com.llaminator.ghostline;

import java.awt.image.Raster;
import java.util.ArrayList;

public class Ghost {

    boolean IsAlive;
    double x;
    double y;


    public Ghost(double x, double y) {
        this.x = x;
        this.y = y;
        IsAlive = true;
    }


    public double GetX(){
        return x;
    }

    public double GetY(){
        return y;
    }

    private void setDeath(){
        IsAlive = false;
    }

    public boolean IsAlive(int x, int y, ArrayList<RouteStep> L, int cnt){
        int supX = 0, supY = 0;
        for (int i = 0; i < cnt; i++) {
            if(L.get(i).getDir() == 0)
                supY += L.get(i).Step.getHeight();
            else
                supX += L.get(i).Step.getHeight();
        }

        /*System.out.println(supX + " " + supY);
        System.out.println(x + " " + y);
        System.out.println();*/
        if ((x + L.get(0).Step.getWidth() / 2 < supX || x > supX + L.get(0).Step.getWidth()) || (y + L.get(0).Step.getHeight()/2 < supY || y > supY + L.get(0).Step.getHeight()))
            setDeath();
        return IsAlive;

    }

    @Override
    public String toString() {
        return "Ghost{" +
                "IsAlive=" + IsAlive +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    public void Death(){
        setDeath();
    }
}