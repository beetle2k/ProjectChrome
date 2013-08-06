package com.cvgstudios.pokemonchrome.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cvgstudios.pokemonchrome.ChromeGame;
import com.cvgstudios.pokemonchrome.game.PlayWorld;

public class NewGameIntroScreen implements Screen, InputProcessor {
	ChromeGame game;

	String[] script;
	String[] hold;

	private int counter = 0;
	private int len;

	BitmapFont font = new BitmapFont();
	SpriteBatch batch;

	private String lineOne = "";
	private String lineTwo = "";

	String playerName = "Ash";
	String playerGender = "male";

	Sprite bg, box, prof;

	private boolean optionsBoxVisible = false;
//	private boolean profVisible = true;
	private Music m = Gdx.audio.newMusic(Gdx.files
			.internal("music/ProfessorIntro.mp3"));

	private int optionsScreen = 1;

	public NewGameIntroScreen(ChromeGame game) {
		this.game = game;
		m.play();
		m.setLooping(true);
		Gdx.input.setInputProcessor(this);
		script = NGScript.importScript();
		len = script.length - 1;
		getLines();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.begin();
		bg.draw(batch);
		if (optionsBoxVisible) {
			box.draw(batch);
		}
		prof.draw(batch);
		font.draw(batch, lineOne, 190, 125);
		font.draw(batch, lineTwo, 190, 75);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		bg = new Sprite(new Texture("imgs/WelcomeBG.png"));
		box = new Sprite(new Texture("imgs/OptionBox.png"));
		prof = new Sprite(new Texture("imgs/Professor.png"));
		prof.setX(500);

		batch = new SpriteBatch();
		font.setColor(Color.BLACK);
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case (Keys.SPACE):
			if (counter < len) {
				counter++;
				if (script[counter].contains("(NAME)")) {
					script[counter] = script[counter].replace("(NAME)",
							playerName);
				} else if (script[counter].contains("(OPTION)")) {
					// show option box
				} else if (script[counter].contains("(GENDERPICK)")) {
					// allow user to choose gender
				} else if (script[counter].contains("(NAME CREATION)")) {
					// allow user to enter a name
				} else if (script[counter].contains("(GENDER)")) {
					script[counter] = script[counter].replace("(GENDER)",
							playerGender);
				}
				getLines();
			} else {
				m.stop();
				game.setScreen(new PlayWorld(game));
			}
			break;
		case (Keys.UP):
			if (optionsScreen == 1) {

			}
			break;
		case (Keys.DOWN):
			if (optionsScreen == 1) {

			}
			break;
		}

		return false;
	}

	private void getLines() {
		hold = script[counter].split(";");
		lineOne = hold[0];
		lineTwo = hold[1];
	}

	@Override
	public boolean keyUp(int keycode) {

		return false;
	}

	@Override
	public boolean keyTyped(char character) {

		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {

		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {

		return false;
	}

	@Override
	public boolean scrolled(int amount) {

		return false;
	}

}