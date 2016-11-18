package com.mygdx.space.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Admin on 18.11.2016.
 */
public class Missile {
    private static int SPEED = 300;
    private Texture image;
    private Vector2 position;
    private Rectangle bounds;

    public Missile(Vector2 position){
        this.position = position;
        image = new Texture(Gdx.files.internal("missile.png"));
        bounds = new Rectangle(position.x, position.y, image.getWidth(), image.getHeight());
    }

    public Texture getImage() {
        return image;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void update(float delta){
        position.y -= SPEED * delta;
        bounds.setPosition(position);
    }

    public boolean collide(Player player){
        return bounds.overlaps(player.getBounds());
    }

    public void dispose(){
        image.dispose();
    }




}
