package com.llaminator.ghostline;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;



public class GamePlayScreen implements Screen {

    final GamePlay game;
    OrthographicCamera camera;
    Music GamePlayMusic;

    double x = 0, y = 0, x0 = 0, y0 = 0;
    double speedX = 64, speedY = 0;
    int cntX = 0, cntY = 0;


    public GamePlayScreen(final GamePlay game) {
        this.game = game;
        camera = new OrthographicCamera(600, 800);
        camera.update();
        GamePlayMusic = Gdx.audio.newMusic(Gdx.files.internal("GamePlayMusic.mp3"));
        GamePlayMusic.play();
        GamePlayMusic.setLooping(true);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.Mroute.setProjectionMatrix(camera.combined);



        game.Mroute.begin();
        x = x0;
        y = y0;

        for (int i = 0; i < game.GameRoute.Level.size(); i++){
            game.Mroute.draw(game.GameRoute.Level.get(i).Step, (float)x, (float) y);
            if (game.GameRoute.Level.get(i).getDir() == 1)
                x += game.GameRoute.Level.get(i).Step.getWidth();
            else
                y += game.GameRoute.Level.get(i).Step.getHeight();
        }
        game.Mroute.end();


        game.Mgh.setProjectionMatrix(camera.combined);

        game.Mgh.begin();

        game.Gh.x += speedX * Gdx.graphics.getDeltaTime();

        game.Gh.y += speedY * Gdx.graphics.getDeltaTime();

        game.Mgh.draw(new Texture(Gdx.files.internal("ghost.png")), (float)game.Gh.x, (float)game.Gh.y);


        camera.position.x = (float) game.Gh.x;
        camera.position.y = (float) game.Gh.y;
        camera.update();

        game.Score = game.GameRoute.Level.size() - game.GameRoute.Size;
        System.out.println(game.Score);

        game.Mgh.end();

        game.getBatch().begin();
        game.getFont().draw(game.getBatch(), ("Score:" + Integer.toString((int)game.Score)),50,50);
        game.getBatch().end();




        if (game.Gh.x - game.GameRoute.Level.get(0).Step.getHeight() * cntX > game.GameRoute.Level.get(0).Step.getHeight()) {
            game.GameRoute.ExpandLevel();
            cntX++;
            if (!game.Gh.IsAlive((int)game.Gh.x, (int)game.Gh.y, game.GameRoute.Level, cntX + cntY)) {
                game.Gh.Death();
            }
        }
        if (game.Gh.y -  game.GameRoute.Level.get(0).Step.getHeight() * cntY > game.GameRoute.Level.get(0).Step.getHeight()) {
            game.GameRoute.ExpandLevel();
            cntY++;

            if (!game.Gh.IsAlive((int)game.Gh.x, (int)game.Gh.y, game.GameRoute.Level, cntX + cntY)) {
                game.Gh.Death();
            }
        }

        if(!game.Gh.IsAlive) {
            dispose();
            game.setDeathScreen();
        }

        if (Gdx.input.justTouched()) {
            double tmp = speedX;
            speedX = speedY;
            speedY = tmp;
        }

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
        GamePlayMusic.dispose();

    }
}
