package com.cvgstudios.pokemonchrome.screens;

import java.util.Vector;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cvgstudios.pokemonchrome.ChromeGame;
import com.cvgstudios.pokemonchrome.gamelogic.PokemonCreature;
import com.cvgstudios.pokemonchrome.images.Graphic;

public class BattleScreen implements Screen, InputProcessor {

	public static final int HUD_BAR_HEIGHT = 180;
	public static final float PLAYER_POKEMON_Y_BACKGROUND_PERCENT_POSITION = 0.12f; // twelve
																					// percent
																					// background
																					// height
																					// from
																					// the
																					// guibar
	public static final float PLAYER_POKEMON_X_PERCENT_POSITION = 0.20f; // twenty
																			// percent
																			// screen
																			// width
																			// from
																			// the
																			// left

	public static final float ENEMY_POKEMON_Y_BACKGROUND_PERCENT_POSITION = 0.46f;
	public static final float ENEMY_POKEMON_X_PERCENT_POSITION = 0.68f;

	BitmapFont guiFont;
	SpriteBatch batch;

	PokemonCreature playerPokemon;
	PokemonCreature enemyPokemon;

	boolean downButton;
	boolean upButton;

	Vector<String> actionList;
	Vector<String> actionNameList;
	int selectedActionIndex;

	BattleState battleState;

	Texture battleBackgroundTexture;
	Texture statBarPlayer;
	Texture statBarEnemy;
	Texture hudBar;

	Texture playerPokemonTexture;
	Texture enemyPokemonTexture;

	Texture playerCharacter;
	Texture enemyCharacter;

	public BattleScreen() {
		super();
		battleState = BattleState.LOADING;
		Gdx.input.setInputProcessor(this);
		batch = new SpriteBatch();
		guiFont = new BitmapFont(Gdx.files.internal("font/pokemon.fnt"), Gdx.files.internal("font/pokemon.png"), false);
		// Something about setting up the fonts, perhaps?
		battleBackgroundTexture = Graphic.GrassBattleSetting.getTexture();
		statBarPlayer = Graphic.PlayerPokemonStatBar.getTexture();
		statBarEnemy = Graphic.EnemyPokemonStatBar.getTexture();
		hudBar = Graphic.BattleScreenMenu.getTexture();
	}

	public void setPlayerPokemon(PokemonCreature pokemon) {
		battleState = BattleState.LOADING;
		playerPokemon = pokemon;
		actionList = new Vector<String>();
		actionNameList = new Vector<String>();

		playerPokemonTexture = playerPokemon.getType().getBackTexture();

		for (int i = 0; i < pokemon.getType().getActions().size(); i++) {
			if (pokemon.getActionAvailability(i)) {
				actionList.add(pokemon.getType().getActions().get(i));
				actionNameList.add(pokemon.getType().getActionNames().get(i));
			}
		}

		battleState = BattleState.PLAYER_CHOOSING_ACTION;

	}

	public void setEnemyPokemon(PokemonCreature pokemon) {
		battleState = BattleState.LOADING;

		enemyPokemon = pokemon;
		enemyPokemonTexture = enemyPokemon.getType().getFrontTexture();

		battleState = BattleState.PLAYER_CHOOSING_ACTION;
	}

	public void render(float delta) {
		int displayWidth = ChromeGame.display.getDisplayWidth();
		int displayHeight = ChromeGame.display.getDisplayHeight();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.begin();

		switch (battleState) {

		case LOADING: {
			guiFont.draw(batch, "Loading...", 100, 100);
			break;
		}
		case PLAYER_CHOOSING_ACTION: {
			guiFont.draw(batch, actionNameList.get(selectedActionIndex), 100, 100);
			// Draws battle background
			batch.draw(battleBackgroundTexture, 0, HUD_BAR_HEIGHT, displayWidth, displayHeight - HUD_BAR_HEIGHT);
			// Draws choice menu
			batch.draw(hudBar, 0, 0, displayWidth, HUD_BAR_HEIGHT);
			// Draws player pokemon
			batch.draw(playerPokemonTexture, (displayWidth * PLAYER_POKEMON_X_PERCENT_POSITION), (HUD_BAR_HEIGHT + PLAYER_POKEMON_Y_BACKGROUND_PERCENT_POSITION * (displayHeight - HUD_BAR_HEIGHT)));
			// Draws enemy pokemon
			batch.draw(enemyPokemonTexture, (displayWidth * ENEMY_POKEMON_X_PERCENT_POSITION), (HUD_BAR_HEIGHT + ENEMY_POKEMON_Y_BACKGROUND_PERCENT_POSITION * (displayHeight - HUD_BAR_HEIGHT)));
			break;
		}
		default: {
			guiFont.drawMultiLine(batch, "NOT loading :D", 100, 100);
			break;
		}

		}

		batch.end();
	}

	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}

	public void show() {
		// TODO Auto-generated method stub
	}

	public void hide() {
		// TODO Auto-generated method stub
	}

	public void pause() {
		// TODO Auto-generated method stub
	}

	public void resume() {
		// TODO Auto-generated method stub
	}

	public void dispose() {
		// TODO Auto-generated method stub
	}

	public boolean keyDown(int keycode) {

		if (battleState == BattleState.PLAYER_CHOOSING_ACTION) { // If it is
																	// currently
																	// your turn

			switch (keycode) {
			case (Keys.DOWN): {

				selectedActionIndex = (selectedActionIndex + 1) % actionNameList.size();
				System.out.println(actionNameList.get(selectedActionIndex));

			}
			case (Keys.UP): {

				selectedActionIndex = (selectedActionIndex - 1) < 0 ? actionNameList.size() - 1 : selectedActionIndex;
				System.out.println(actionNameList.get(selectedActionIndex));

			}

			}

		}

		return false;

	}

	public boolean keyUp(int keycode) {
		return false;
	}

	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}

enum BattleState {
	LOADING, PLAYER_CHOOSING_ACTION, PLAYER_POKEMON_DOING_ACTION, ENEMY_POKEMON_CHOOSING_ACTION, ENEMY_POKEMON_DOING_ACTION, ;

}