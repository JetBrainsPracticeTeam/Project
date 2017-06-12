package com.llaminator.ghostline;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Артём on 12.06.2017.
 */

public class GamePlayScreen implements Screen {

    final GamePlay game;
    OrthographicCamera camera;
    double x = 0, y = 0, x0 = 0, y0 = 0;
    double speedX = 64, speedY = 0;
    int cntX = 0, cntY = 0;

    public GamePlayScreen(final GamePlay game) {
        this.game = game;
        camera = new OrthographicCamera(480, 600);
        camera.update();


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.Mroute.setProjectionMatrix(camera.combined);

        game.Mroute.begin();
        x = x0;
        y = y0;
        //System.out.println(GameRoute.Level.size());
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

        //System.out.println(Gh.x + " " + Gh.y);

        game.Gh.x += speedX * Gdx.graphics.getDeltaTime();

        game.Gh.y += speedY * Gdx.graphics.getDeltaTime();

        game.Mgh.draw(new Texture(Gdx.files.internal("ghost.png")), (float)game.Gh.x, (float)game.Gh.y);

        //System.out.println(camera.position.x + " " + camera.position.y);

        camera.position.x = (float) game.Gh.x;
        camera.position.y = (float) game.Gh.y;
        camera.update();

        //camera.position.set( (float)Gh.x, (float)Gh.y, 0 );
        //camera.position.set(camera.viewportWidth, camera.viewportHeight, 0);

        game.Mgh.end();


        if (game.Gh.x - game.GameRoute.Level.get(0).Step.getHeight() * cntX > game.GameRoute.Level.get(0).Step.getHeight()) {
            game.GameRoute.ExpandLevel();
            cntX++;
            //System.out.println(Gh.IsAlive((int)Gh.x, (int)Gh.y, GameRoute.Level, cntX + cntY));
            if (!game.Gh.IsAlive((int)game.Gh.x, (int)game.Gh.y, game.GameRoute.Level, cntX + cntY)) {
                game.Gh.Death();

                //dispose();
            }
        }
        if (game.Gh.y -  game.GameRoute.Level.get(0).Step.getHeight() * cntY > game.GameRoute.Level.get(0).Step.getHeight()) {
            game.GameRoute.ExpandLevel();
            cntY++;

            if (!game.Gh.IsAlive((int)game.Gh.x, (int)game.Gh.y, game.GameRoute.Level, cntX + cntY)) {
                game.Gh.Death();

                //dispose();
            }
        }

        if(!game.Gh.IsAlive)
            dispose();

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

    }
}
