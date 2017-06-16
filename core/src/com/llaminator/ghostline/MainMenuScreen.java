package com.llaminator.ghostline;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;


class MainMenuScreen implements Screen {

    final GamePlay game;
    SpriteBatch SoundButton;
    OrthographicCamera camera;
    String startString;
    Texture img;
    Music StartMusic;


    Texture button;

    int buttonLocationX = 0, buttonLocationY = 0;



    public MainMenuScreen(final GamePlay game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 600, 800);
        img = new Texture("menu.jpg");
        StartMusic = Gdx.audio.newMusic(Gdx.files.internal("MenuMusic.mp3"));
        if (game.Sound)
            StartMusic.play();

        SoundButton = new SpriteBatch();

        button = new Texture(Gdx.files.internal("SoundButton.jpg"));


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

        Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);

        if(Gdx.input.isTouched())
            if ((touchPos.x > buttonLocationX && touchPos.x < buttonLocationX + button.getHeight()*Gdx.app.getGraphics().getWidth()/540) && (Gdx.app.getGraphics().getHeight() - touchPos.y > buttonLocationY && Gdx.app.getGraphics().getHeight() - touchPos.y < button.getHeight()*Gdx.app.getGraphics().getHeight()/720)) {

                if (game.Sound == false) {
                    StartMusic.play();
                    StartMusic.setLooping(true);
                    game.Sound = true;
                }
                else {
                    if (game.Sound == true) {
                        StartMusic.pause();
                        game.Sound = false;
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        if(Gdx.input.isTouched())
            if (!((touchPos.x > buttonLocationX && touchPos.x < buttonLocationX + button.getHeight()) && (800 - touchPos.y > buttonLocationY && 800 - touchPos.y < button.getHeight()))) {
                dispose();
                game.setGamePlayScreen(0);
            }

        game.getBatch().end();

        SoundButton.begin();
        SoundButton.draw(button, buttonLocationX, buttonLocationY);
        SoundButton.end();
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
        StartMusic.stop();
    }
}

