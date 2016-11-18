package com.mygdx.space.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.space.SpaceInvaders;

/**
 * Created by Admin on 16.11.2016.
 */
public class Laser {
    private static int SPEED = 1000;
    private Texture image;
    private Vector2 position;
    private boolean visible;
    private Rectangle bounds;
    private Sound laserSFX;
    private int shots;

    public Laser(){
        shots = 0;
        visible = false;
        position = new Vector2(0, 0);
        image = new Texture(Gdx.files.internal("laser.png"));
        bounds = new Rectangle(position.x, position.y, image.getWidth(), image.getHeight());
        laserSFX = Gdx.audio.newSound(Gdx.files.internal("laser.mp3"));
    }

    public int getShots() {
        return shots;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Texture getImage() {
        return image;
    }

    public Vector2 getPosition() {
        return position;
    }

    public boolean collides(Rectangle enemy){
        return bounds.overlaps(enemy);
    }

    public void shoot(Vector2 playerPosition){
        position.x = playerPosition.x+29;
        position.y = playerPosition.y+50;
        visible = true;
        shots++;
        laserSFX.play();
    }

    public void update(float delta){

        if(position.y < SpaceInvaders.HEIGTH && visible){
            position.add(0, SPEED*delta);
        }
        if(position.y > SpaceInvaders.HEIGTH){
            visible = false;
        }

        bounds.setPosition(position);
    }

    public void dispose(){
        image.dispose();
        laserSFX.dispose();

    }
}
