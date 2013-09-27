/**
 * 
 */
package pl.xesenix.games.effects.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
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
import com.badlogic.gdx.scenes.scene2d.Action;
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
	}
	
	
	protected void buildMenu()
	{
		Gdx.app.log(XesEffects.LOG, "Building mainmenu.");
		// preparing layout
		this.layout = new Table();
		this.layout.setFillParent(true);
		
		stage.addActor(layout);
		
		// adding buttons
		TextButton startButton = new TextButton("Start the game", game.getSkin(), "default");
		
		//startButton.debug();
		layout.add(startButton);
	}
}
