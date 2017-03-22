package fr.thierry.arkialan.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import fr.thierry.arkialan.Main;

import static fr.thierry.arkialan.Main.SCREEN_HEIGHT;
import static fr.thierry.arkialan.Main.SCREEN_WIDTH;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = SCREEN_WIDTH;
		config.height = SCREEN_HEIGHT;
		config.resizable = false;

		new LwjglApplication(new Main(), config);
	}
}
