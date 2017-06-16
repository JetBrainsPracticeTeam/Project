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

	boolean Sound = true;


	double x0 = 0, y0 = 0, Score = 0, saveScore = 0, saveX = x0, saveY = y0;

	@Override
	public void create () {

		Mroute = new SpriteBatch();
		Mgh = new SpriteBatch();
		mBatch = new SpriteBatch();
		mFont = new BitmapFont();
		GameRoute = new Route();
		Gh = new Ghost(saveX, saveY);
		Score = 0;
		double x0 = 0, y0 = 0;


		setMainMenuScreen();
	}
	public void setGamePlayScreen(int a) {
		if (a == 0) {
			x0 = 0;
			y0 = 0;
			Gh.x = x0;
			Gh.y = y0;
			Gh.IsAlive = true;
			Score = 0;
			saveScore = 0;
		}
		else {


		}

		this.setScreen(new GamePlayScreen(this));
	}

	public void setMainMenuScreen() {
		Score = 0;
		saveScore = 0;
		this.setScreen(new MainMenuScreen(this));
	}
	public void setDeathScreen() {
		this.setScreen(new DeathScreen(this));
	}
	public void setPauseScreen() {this.setScreen(new PauseScreen(this));}

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
