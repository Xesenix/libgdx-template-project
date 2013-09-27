/**
 * 
 */
package pl.xesenix.games.effects.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import java.util.Iterator;

import pl.xesenix.games.effects.XesEffects;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;

/**
 * @author Xesenix
 *
 */
public final class MenuScreen extends AbstractScreen
{

	private Texture bgTexture;
	private Image bgImage;
	private Table layout;


	public MenuScreen(XesEffects game)
	{
		super(game);
		this.bgColor = new Color(0.0f, 0.0f, 0.0f, 1);
	}
	
	
	public void render(float delta)
	{
		super.render(delta);
		//TextButton.drawDebug(stage);
		//Table.drawDebug(stage);
	}
	
	
	public void show()
	{
		Gdx.app.log(XesEffects.LOG, "Showing MenuScreen");
		super.show();
		// preparing actors
		// - background:
		this.bgTexture = new Texture("data/menu_screen_bg.png");
		this.bgTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureRegion bgTextureRegion = new TextureRegion(bgTexture, 0, 0, 512, 512);
		
		TextureRegionDrawable drawableBg = new TextureRegionDrawable(bgTextureRegion);
		
		this.bgImage = new Image(drawableBg, Scaling.fillX, Align.top);
		this.bgImage.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.bgImage.setFillParent(true);
		
		// building stage
		this.stage.clear();
		this.stage.addActor(this.bgImage);
		
		// building menu
		this.buildMenu();
		
		// show animation
		this.stage.addAction(
			sequence(
				fadeOut(0),
				parallel(
					fadeIn(1.5f)
				)
			)
		);
	}


	public void resize(int width, int height)
	{
		Gdx.app.log(XesEffects.LOG, "Resizing MenuScreen");
		super.resize(width, height);
		
		// resize background - u don`t need it if you use setFillParent to true
		//this.splashImage.setBounds(0, 0, width, height);
		//this.splashImage.invalidate();
		
		// resize stage
		this.stage.setViewport(width, height, true);
		
		Vector2 menuTitleSize = Scaling.fillX.apply(512f, 100f, width, height);
		
		this.layout.setPosition(0, 0);
		this.layout.setWidth(width);
		this.layout.setHeight(height - menuTitleSize.y);
		this.layout.invalidate();
	}
	
	
	protected void buildMenu()
	{
		Gdx.app.log(XesEffects.LOG, "Building mainmenu.");
		// preparing layout
		this.layout = new Table();
		this.layout.setWidth(Gdx.graphics.getWidth());
		this.layout.align(Align.top);
		
		stage.addActor(layout);
		
		// adding buttons
		TextButton startButton = new TextButton("Choose effect", game.getSkin(), "default");
		
		layout.add(startButton).width(300f);
		
		// new line
		layout.row();
		
		TextButton optionButton = new TextButton("Options", game.getSkin(), "default");
		
		layout.add(optionButton).width(300f);
		
		// new line
		layout.row();
		
		TextButton creditsButton = new TextButton("Credits", game.getSkin(), "default");
		
		layout.add(creditsButton).width(300f);
		
		//layout.debug();
		
		// menu in animation
		float delay = 0;
		
		for (Iterator<Actor> iter = layout.getChildren().iterator(); iter.hasNext();)
		{
			Actor actor = iter.next();
			
			actor.setY(20);
			actor.addAction(
				sequence(
					fadeOut(0),
					delay(delay),
					moveBy(50f, 0, 0),
					parallel(
						moveBy(-50f, 0, 0.5f),
						fadeIn(0.3f)
					)
				)
			);
			
			delay += 0.3f;
		}
	}
}
