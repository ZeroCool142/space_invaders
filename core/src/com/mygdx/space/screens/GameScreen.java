package com.mygdx.space.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.space.SpaceInvaders;
import com.mygdx.space.sprites.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * Created by Admin on 16.11.2016.
 */
public class GameScreen implements Screen {

    public static final int ENEMY_ROW = 3;
    public static final int ENEMY_COLUMN = 6;
    private final SpaceInvaders game;
    private OrthographicCamera camera;
    private Texture background;
    private int score;
    private Player player;
    private Laser laser;
    private Array<Enemy> enemies;
    private Array<Life> lives;
    private Array<Missile> missiles;
    private long lastTimeLaunch;
    private Sound explode;


    public GameScreen(final SpaceInvaders game){
        this.game = game;
        score = 0;
        background = new Texture(Gdx.files.internal("spaceBackground.png"));
        explode = Gdx.audio.newSound(Gdx.files.internal("explosion.wav"));


        lives = new Array<Life>();
        for (int i = 0; i < 3; i++){
            lives.add(new Life(new Vector2(i*40+140, SpaceInvaders.HEIGTH-32 )));
        }

        player = new Player();
        this.laser = new Laser();

        enemies = new Array<Enemy>();
        int x = 25;
        int y = SpaceInvaders.HEIGTH-100;
        for(int r = 0; r < ENEMY_ROW; r++){
            for (int c = 0; c < ENEMY_COLUMN; c++){
                enemies.add(new Enemy(new Vector2(x, y)));
                x += 100;
            }
            x = 25;
            y -= 80;
        }
        missiles = new Array<Missile>();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, SpaceInvaders.WIDTH, SpaceInvaders.HEIGTH);


    }

    private void handleInput(){
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            player.left();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            player.right();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && !laser.isVisible()){
            laser.shoot(player.getPosition());
        }
    }

    private void launchMissile(float delta){
        if (missiles.size < 3 && TimeUtils.nanoTime()-lastTimeLaunch > MathUtils.random(1000000000, 2000000000)){
            missiles.add(new Missile(new Vector2(enemies.random().getPosition())));
            lastTimeLaunch = TimeUtils.nanoTime();
        }
        for (int i = 0; i < missiles.size; i++) {
            missiles.get(i).update(delta);
            if (missiles.get(i).collide(player)){
                lives.pop();
            }
            if (missiles.get(i).getPosition().y < 0 || missiles.get(i).collide(player)){
                missiles.removeIndex(i);
            }
        }
        if (lives.size == 0){
            game.setScreen(new GameOverScreen(game, score, laser.getShots(), false));
            dispose();
        }
    }

    private void checkEnemies(){
        for (int i = 0; i < enemies.size; i++) {
            if (enemies.get(i).getPosition().y < 100){
                game.setScreen(new GameOverScreen(game, score, laser.getShots(), false));
                dispose();
            }
            if(laser.collides(enemies.get(i).getBound()) && laser.isVisible()) {
                score += 10;
                laser.setVisible(false);
                enemies.removeValue(enemies.get(i), true);
                explode.setVolume(explode.play(), 0.3f);
            }
        }
        if (enemies.size == 0){
            game.setScreen(new GameOverScreen(game, score, laser.getShots(), true));
            dispose();
        }
    }

    private void moveEnemies(float delta){
        if (maxEnemyX()) {
            for (Enemy e: enemies){
                if (e.isMovingRight()){
                    e.increaseSpeed();
                    e.moveDown();
                    e.revers();
                    e.setMovingRight(false);
                }
            }
        }
        if (minEnemyX()) {
            for (Enemy e: enemies){
                if (!e.isMovingRight()){
                    e.increaseSpeed();
                    e.moveDown();
                    e.revers();
                    e.setMovingRight(true);
                }
            }
        }
        for (Enemy e: enemies){
            e.update(delta);
        }
    }
    private boolean maxEnemyX(){
        for (Enemy e: enemies){
            if (e.getPosition().x > SpaceInvaders.WIDTH-64){
                return true;
            }
        }
        return false;
    }
    private boolean minEnemyX(){
        for (Enemy e: enemies){
            if (e.getPosition().x < 0){
                return true;
            }
        }
        return false;
    }

    @Override
    public void show() {

    }

    public void update(float delta) {
        player.update(delta);
        laser.update(delta);
        moveEnemies(delta);
        launchMissile(delta);
        checkEnemies();
        handleInput();
    }

    @Override
    public void render(float delta) {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(background, 0,0);
        game.batch.draw(player.getImage(), player.getPosition().x, player.getPosition().y);
        for(Enemy e: enemies){
            game.batch.draw(e.getImage(), e.getPosition().x, e.getPosition().y);
        }
        for(Life l: lives){
            game.batch.draw(l.getImage(), l.getPosition().x, l.getPosition().y);
        }
        if(laser.isVisible()){
            game.batch.draw(laser.getImage(), laser.getPosition().x, laser.getPosition().y);
        }
        for (Missile m: missiles){
            game.batch.draw(m.getImage(), m.getPosition().x, m.getPosition().y);
        }
        game.font.draw(game.batch, "Score: " + score, 10, SpaceInvaders.HEIGTH-10);
        game.font.draw(game.batch, "Lives: ", 100, SpaceInvaders.HEIGTH-10);
        game.batch.end();
        update(delta);
    }

    @Override
    public void dispose() {
        player.dispose();
        laser.dispose();
        for (Enemy e: enemies) {
            e.dispose();
        }
        for (Missile m: missiles) {
            m.dispose();
        }
        for (Life l: lives){
            l.dispose();
        }
        background.dispose();

        explode.dispose();
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


}
