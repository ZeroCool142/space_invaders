package com.mygdx.space.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Admin on 17.11.2016.
 */
public class Life {
    private Texture image;
    private Vector2 position;

    public Life(Vector2 position){
        image = new Texture("heart.png");
        this.position = position;
    }

    public Texture getImage() {
        return image;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void dispose(){
        image.dispose();
    }
}
