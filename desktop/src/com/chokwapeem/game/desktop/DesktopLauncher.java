package com.chokwapeem.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.chokwapeem.game.Spaceshooter;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Spaceshooter";
		config.width = 600;
		config.height = 400;
		config.useGL30 = false;
		config.resizable = false;
		
		new LwjglApplication(new Spaceshooter(), config);
		
		
	}
}
