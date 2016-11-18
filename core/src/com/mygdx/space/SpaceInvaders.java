package com.mygdx.space;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.mygdx.space.screens.*;


public class SpaceInvaders extends Game {

	public static final int WIDTH = 600;
	public static final int HEIGTH = 700;
	public static final String TITLE = "Space Invaders";

	private Music backgroundMusic;

	public SpriteBatch batch;
	public BitmapFont font;

	@Override
	public void create () {
		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("backgroundMusic.mp3"));
		backgroundMusic.setVolume(0.2f);
		backgroundMusic.setLooping(true);
		backgroundMusic.play();


		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new MenuScreen(this));



	}




	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		backgroundMusic.dispose();
		batch.dispose();
		font.dispose();
	}
}
