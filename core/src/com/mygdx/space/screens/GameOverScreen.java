package com.mygdx.space.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.space.SpaceInvaders;

/**
 * Created by Admin on 17.11.2016.
 */
public class GameOverScreen implements Screen {

    private final SpaceInvaders game;
    private OrthographicCamera camera;
    private Texture background;
    private final Texture scorePanel;
    private int score;
    private int shots;
    private double accuracy;
    private boolean win;

    public GameOverScreen(final SpaceInvaders game, int score, int shots, boolean win){
        this.game = game;
        this.score = score;
        this.shots = shots;
        this.win = win;
        if (shots == 0){
            accuracy = 0;
        } else this.accuracy = Math.round(GameScreen.ENEMY_COLUMN*GameScreen.ENEMY_ROW/(double)shots*10000)/100.0;
        background = new Texture("spaceBackground.png");
        scorePanel = new Texture("panel.png");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SpaceInvaders.WIDTH, SpaceInvaders.HEIGTH);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(background, 0,0);
        game.batch.draw(scorePanel, SpaceInvaders.WIDTH/2 - scorePanel.getWidth()/2, SpaceInvaders.HEIGTH/2-scorePanel.getHeight()/2);
        if (win){
            game.font.draw(game.batch, "You win!", 280, 500);
            game.font.draw(game.batch, "Accuracy: " + accuracy + "%", 140, 400);
        } else {
            game.font.draw(game.batch, "You loose!", 275, 500);
            game.font.draw(game.batch, "Accuracy: 0%", 140, 400);
        }
        game.font.draw(game.batch, "Shots: " + shots, 140, 450);
        game.font.draw(game.batch, "Score: " + score, 140, 350);
        game.font.draw(game.batch, "Tap for new game.", 250, 200);

        game.batch.end();

        if(Gdx.input.isTouched()){
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void dispose() {
        background.dispose();
        scorePanel.dispose();
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
    public void show() {

    }
}
