package com.llaminator.ghostline.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.llaminator.ghostline.GamePlay;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		System.setProperty("user.name","\\xD0\\x90\\xD1\\x80\\xD1\\x82\\xD1\\x91\\xD0\\xBC");
		config.width = 600;
		config.height = 800;
		new LwjglApplication(new GamePlay(), config);
	}
}
