package com.llaminator.ghostline;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;



class DeathScreen implements Screen {

    final GamePlay game;
    OrthographicCamera camera;
    Texture img;



    public DeathScreen(final GamePlay gam) {
        this.game = gam;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 685,990);
        if (Gdx.input.justTouched()) {
            game.setMainMenuScreen();
        }

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.getBatch().begin();
        //game.getBatch().draw(img, 0, 0);
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
        //game.dispose();
        //game.batch.dispose();

    }
}