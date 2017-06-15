package com.llaminator.ghostline;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.llaminator.ghostline.GamePlay;


public class PauseScreen implements Screen {

    final GamePlay game;
    OrthographicCamera camera;
    String startString;
    Texture img, addPause;



    public PauseScreen(GamePlay game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 600, 800);
        img = new Texture("pause.jpg");
        addPause = new Texture("add_pause.png");
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
        game.getBatch().draw(addPause, 50, 100);
        this.startString = "Tap to Continue";
        game.getFont().draw(game.getBatch(), startString, 280, 500);

        if (Gdx.input.isTouched()) {

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

    }
}

