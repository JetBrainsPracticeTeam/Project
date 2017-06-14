package com.llaminator.ghostline;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.sun.corba.se.spi.orbutil.fsm.FSM;
import com.sun.corba.se.spi.orbutil.fsm.State;

import sun.rmi.runtime.Log;

import static java.lang.System.*;


class MainMenuScreen implements Screen {

    final GamePlay game;
    OrthographicCamera camera;
    String startString;
    Texture img;
    Music StartMusic;



    public MainMenuScreen(final GamePlay game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 600,800);
        img = new Texture("menu.jpg");

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        game.getBatch().draw(img, 0, 0);

        this.startString = "Tap to Start";

        game.getFont().draw(game.getBatch(), startString , 280, 500);
        StartMusic = Gdx.audio.newMusic(Gdx.files.internal("MenuMusic.mp3"));
        StartMusic.play();
        StartMusic.setLooping(true);


        if(Gdx.input.justTouched()){

            //img = new Texture("Go.jpg");
            //game.getBatch().draw(img, 0, 0);
            dispose();
            //System.out.println("kek");
            game.setGamePlayScreen();
        }

        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        img.dispose();
        StartMusic.dispose();

    }
}

