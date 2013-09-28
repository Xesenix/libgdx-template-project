
package pl.xesenix.games.effects.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;

import pl.xesenix.games.effects.XesEffects;


abstract public class AbstractMenuScreen extends AbstractScreen
{

	protected Texture bgTexture;
	
	
	protected Image bgImage;
	
	
	protected Table layout;
	
	
	protected String backgroundFileName = "data/background.png";
	

	public AbstractMenuScreen(XesEffects game)
	{
		super(game);
	}
	
	
	public void show()
	{
		Gdx.app.log(XesEffects.LOG, "Showing AbstractMenuScreen");
		super.show();
		
		this.addBackground();
		this.addUserInterface();
		this.tweenIn();
	}
	
	
	protected void addBackground()
	{
		this.bgTexture = new Texture(this.backgroundFileName);
		this.bgTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureRegion bgTextureRegion = new TextureRegion(bgTexture, 0, 0, 512, 512);
		
		TextureRegionDrawable drawableBg = new TextureRegionDrawable(bgTextureRegion);
		
		this.bgImage = new Image(drawableBg, Scaling.fillX, Align.top);
		this.bgImage.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.bgImage.setFillParent(true);
		
		this.stage.addActor(this.bgImage);
	}
	
	
	abstract protected void addUserInterface();
	
	
	public void tweenIn()
	{
		// stage in animation
		this.stage.addAction(
			sequence(
				fadeOut(0),
				parallel(
					fadeIn(1.5f)
				)
			)
		);
		
		// menu in animation
		this.tweenInUserInterface();
	}
	
	
	public float tweenInUserInterface()
	{
		float delay = 0;
		float step = Math.max(0.1f, 1f / (float)this.layout.getChildren().size);
		
		for (Iterator<Actor> iter = this.layout.getChildren().iterator(); iter.hasNext();)
		{
			Actor actor = iter.next();
			
			actor.addAction(
				sequence(
					fadeOut(0),
					delay(delay),
					moveBy(150f, 0, 0),
					parallel(
						moveBy(-150f, 0, 0.5f),
						fadeIn(0.3f)
					)
				)
			);
			
			delay += step;
		}
		
		return delay + 0.5f;
	}
	
	
	public float tweenOut()
	{
		return this.tweenOutUserInterface();
	}
	
	
	public float tweenOutUserInterface()
	{
		float delay = 0;
		float step = Math.max(0.1f, 1f / (float)this.layout.getChildren().size);
		
		for (Iterator<Actor> iter = this.layout.getChildren().iterator(); iter.hasNext();)
		{
			Actor actor = iter.next();
			
			actor.addAction(
				sequence(
					delay(delay),
					parallel(
						fadeOut(0.5f),
						moveBy(-150f, 0, 0.5f)
					)
				)
			);
			
			delay += step;
		}
		
		return delay + 0.5f;
	}
	
	
	public void tweenToScreen(final AbstractScreen targetScreen)
	{
		this.game.getTweenManager().killAll();
		
		float tweenOutTime = this.tweenOut();
		
		this.bgImage.addAction(
			sequence(
				delay(tweenOutTime - 1.5f),
				fadeOut(1.5f),
				new Action() {
					public boolean act(float delta)
					{
						game.setScreen(targetScreen);
						return true;
					}
				}
			)
		);
	}
}