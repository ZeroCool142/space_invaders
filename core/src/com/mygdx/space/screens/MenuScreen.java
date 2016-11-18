package com.mygdx.space.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.space.SpaceInvaders;

/**
 * Created by Admin on 16.11.2016.
 */
public class MenuScreen implements Screen {

    private final SpaceInvaders game;
    private OrthographicCamera camera;
    private Texture background;



    public MenuScreen(final SpaceInvaders game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SpaceInvaders.WIDTH, SpaceInvaders.HEIGTH);
        background = new Texture(Gdx.files.internal("spaceBackground.png"));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(background, 0,0);
        game.font.draw(game.batch, "Welcome to Space Invaders!", 240, SpaceInvaders.HEIGTH/2+50);
        game.font.draw(game.batch, "For play tap to screen.", 255, SpaceInvaders.HEIGTH/2-50);
        game.batch.end();

        if(Gdx.input.isTouched()){
            game.setScreen(new GameScreen(game));
            dispose();
        }

    }

    @Override
    public void dispose() {
        background.dispose();
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
