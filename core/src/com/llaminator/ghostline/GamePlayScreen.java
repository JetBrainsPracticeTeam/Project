package com.llaminator.ghostline;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;



public class GamePlayScreen implements Screen {

    final GamePlay game;
    OrthographicCamera camera;
    Music GamePlayMusic;
    String HighScore;
    Texture Pause;

    static int DeltaSpeed = 5;
    double x = 0, y = 0;
    double speedX = 0, speedY = 0, speedIncTime = DeltaSpeed,  resScore = 0, saveSpeedX = 64, saveSpeedY = 0;
    int cntX = 0, cntY = 0;


    public GamePlayScreen(final GamePlay game) {
        this.game = game;
        camera = new OrthographicCamera(540, 720);
        camera.update();
        speedX = saveSpeedX;
        speedY = saveSpeedY;

        game.Gh.x = game.saveX;
        game.Gh.y = game.saveY;
        Pause = new Texture("pause.png");
        game.Score -= game.saveScore;

        GamePlayMusic = Gdx.audio.newMusic(Gdx.files.internal("GamePlayMusic.mp3"));
        if (game.Sound) {
            GamePlayMusic.play();
            GamePlayMusic.setLooping(true);
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*Scanner in = null;
        try {
            in = new Scanner(new FileReader("HighScore.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        while(in.hasNext()) {
            sb.append(in.next());
        }
        in.close();
        HighScore = sb.toString();*/
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.Mroute.setProjectionMatrix(camera.combined);

        if (speedIncTime == 0) {
            if(speedX != 0)
                speedX+=5;
            else
                speedY+=5;
            speedIncTime = DeltaSpeed;
        }


        game.Mroute.begin();

        x = game.x0;
        y = game.y0;


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


        game.Mgh.end();

        game.getBatch().begin();
        game.getBatch().draw(Pause, 0, 0);


        game.getFont().draw(game.getBatch(), ("Score:" + Integer.toString((int)game.Score)),50,50);
        //game.getFont().draw(game.getBatch(), ("Highest Score:" + HighScore),50,100);
        if (game.Score - resScore != 0)
            speedIncTime--;
        resScore = game.Score;
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
        System.out.println((int)game.Gh.x + " " + (int)game.Gh.y);

        if(!game.Gh.IsAlive) {
            dispose();
            game.setDeathScreen();
        }

        Vector3 touchPos = new Vector3 (Gdx.input.getX(), Gdx.input.getY(), 0);

        if(Gdx.input.justTouched()) {
            if ((touchPos.x > 0 && touchPos.x < Pause.getWidth()) && (Gdx.app.getGraphics().getHeight() - touchPos.y > 0 && Gdx.app.getGraphics().getHeight() - touchPos.y < Pause.getHeight())) {
                pause();
            } else {
                double tmp = speedX;
                speedX = speedY;
                speedY = tmp;
            }
        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        game.saveX = game.Gh.x;
        game.saveY = game.Gh.y;

        saveSpeedX = speedX;
        speedX = 0;
        saveSpeedY = speedY;
        speedY = 0;
        game.saveScore = game.Score;
        GamePlayMusic.dispose();
        game.setPauseScreen();
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        game.Gh.IsAlive = true;
        game.Gh.x = game.x0;
        game.Gh.y = game.y0;
        game.Score = 0;
        GamePlayMusic.dispose();

    }
}
