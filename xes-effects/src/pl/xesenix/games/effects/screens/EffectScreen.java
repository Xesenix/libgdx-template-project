/**
 * 
 */
package pl.xesenix.games.effects.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import pl.xesenix.games.effects.EffectScreenConfig;
import pl.xesenix.games.effects.XesEffects;

/**
 * @author Xesenix
 *
 */
public class EffectScreen extends AbstractScreen
{

	protected EffectScreenConfig config;
	protected Table layout;

	/**
	 * @param game
	 */
	public EffectScreen(XesEffects game, EffectScreenConfig config)
	{
		super(game);
		this.config = config;
	}

	
	public void show()
	{
		super.show();
		
		this.layout = new Table();
		this.layout.setFillParent(true);
		
		this.stage.addActor(this.layout);
		
		Label label = new Label(this.config.getName(), game.getSkin(), "default");
		
		this.layout.add(label);
		
		TextButton backButton = new TextButton("back", game.getSkin(), "default");
		backButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y)
			{
				game.setScreen(game.getMenuScreen());
			}
		});

		this.layout.add(backButton);
		
	}
}
