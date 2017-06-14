package com.llaminator.ghostline;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class GamePlay extends Game {

	SpriteBatch Mroute;
	SpriteBatch Mgh;

	private SpriteBatch mBatch;
	private BitmapFont mFont;

	Route GameRoute;
	Ghost Gh;


	double x0 = 0, y0 = 0, Score = 0;

	@Override
	public void create () {

		Mroute = new SpriteBatch();
		Mgh = new SpriteBatch();
		mBatch = new SpriteBatch();
		mFont = new BitmapFont();
		GameRoute = new Route();
		Gh = new Ghost(x0, y0);

		this.setScreen(new MainMenuScreen(this));
	}
	public void setGamePlayScreen() {
		this.setScreen(new GamePlayScreen(this));
	}

	public void setMainMenuScreen() {
		this.setScreen(new MainMenuScreen(this));
	}
	public void setDeathScreen() {
		this.setScreen(new DeathScreen(this));
	}

	public SpriteBatch getBatch () {return mBatch;}
	public BitmapFont getFont () {return mFont;}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void dispose () {
		super.dispose();
		Mroute.dispose();
		Mgh.dispose();
	}

}
