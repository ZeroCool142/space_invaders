package com.mygdx.space.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.space.SpaceInvaders;

/**
 * Created by Admin on 16.11.2016.
 */
public class Enemy {

    private int speed;
    private Texture image;
    private Vector2 position;
    private boolean movingRight;
    private Rectangle bound;


    public Enemy(Vector2 position){
        speed = 20;
        image = new Texture(Gdx.files.internal("enemyTexture64x73.png"));
        movingRight = true;
        this.position = position;
        bound = new Rectangle(position.x, position.y, image.getWidth(), image.getHeight());
    }

    public Rectangle getBound() {
        return bound;
    }

    public void increaseSpeed() {
        if(this.speed > 0){
            this.speed += 10;
        }
        else this.speed -= 10;

    }

    public void revers(){
        speed = -speed;
    }

    public Texture getImage() {
        return image;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void moveDown() {
        position.y -= 20;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    public void update(float delta){
        position.add(speed * delta, 0);
        bound.setPosition(position);
    }

    public void dispose(){
        image.dispose();
    }
}
