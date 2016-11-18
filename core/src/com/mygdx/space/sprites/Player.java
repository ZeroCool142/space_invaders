package com.mygdx.space.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.space.SpaceInvaders;

/**
 * Created by Admin on 16.11.2016.
 */
public class Player {

    private static final int MOVEMENT_X = 100;

    private Vector2 position;
    private Vector2 velocity;
    private Texture image;
    private Rectangle bounds;

    public Player(){
        image = new Texture(Gdx.files.internal("playerTexture64x49.png"));
        position = new Vector2(SpaceInvaders.WIDTH/2-image.getWidth()/2, 20);
        velocity = new Vector2(0, 0);
        bounds = new Rectangle(position.x, position.y, image.getWidth(),image.getHeight());
    }

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Texture getImage(){
        return image;
    }

    public void left() {
        velocity.x = -MOVEMENT_X;
    }
    public void right() {
        velocity.x = MOVEMENT_X;
    }

    private void resetVelocity() {
        velocity.x = 0;
    }

    public void update(float delta) {

        if (velocity.x != 0){
            position.add(velocity.x * delta, 0);
        }

        if (position.x < 0){
            position.x = 0;
            resetVelocity();
        }
        if (position.x > SpaceInvaders.WIDTH-image.getWidth()){
            position.x = SpaceInvaders.WIDTH-image.getWidth();
            resetVelocity();
        }
        resetVelocity();

        bounds.setPosition(position);
    }

    public void dispose() {
        image.dispose();
    }


}
