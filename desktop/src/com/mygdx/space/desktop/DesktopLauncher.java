package com.mygdx.space.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.space.SpaceInvaders;

public class DesktopLauncher {
	public static void main (String[] arg) {


		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new SpaceInvaders(), config);

		config.width = SpaceInvaders.WIDTH;
		config.height = SpaceInvaders.HEIGTH;
		config.title = SpaceInvaders.TITLE;
	}
}
