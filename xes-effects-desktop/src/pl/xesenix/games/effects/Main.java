
package pl.xesenix.games.effects;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


public class Main
{
	public static void main(String[] args)
	{
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "xes-effects";
		cfg.useGL20 = false;
		cfg.width = (int) XesEffects.GAME_SCREEN_BASE_WIDTH;
		cfg.height = (int) XesEffects.GAME_SCREEN_BASE_HEIGHT;
		cfg.addIcon("icons/icon32.png", FileType.Internal);
		cfg.addIcon("icons/icon16.png", FileType.Internal);
		
		new LwjglApplication(new XesEffects(), cfg);
	}
}
