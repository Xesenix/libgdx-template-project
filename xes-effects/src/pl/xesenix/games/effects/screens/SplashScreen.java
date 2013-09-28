/*******************************************************************************
 * Copyright (c) 2013 Paweł Kapalla, Xessenix. All rights reserved. This program
 * and the accompanying materials are made available under the terms of the GNU
 * Lesser Public License v2.1 which accompanies this distribution, and is
 * available at http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors: Paweł Kapalla, Xessenix - initial API and implementation
 ******************************************************************************/

package pl.xesenix.games.effects.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import pl.xesenix.games.effects.XesEffects;
import pl.xesenix.games.tweens.ColorAccessor;
import aurelienribon.tweenengine.Tween;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;

/**
 * @author Xesenix
 * 
 */
public final class SplashScreen extends AbstractScreen implements Screen
{

	private Texture bgTexture;
	
	
	private Image bgImage;
	
	
	/**
	 * 
	 */
	public SplashScreen(XesEffects game)
	{
		super(game);
		this.bgColor = new Color(0.61f, 0.78f, 0.95f, 1);
	}
	
	
	public void render(float delta)
	{
		super.render(delta);
		
		if (Gdx.input.isTouched())
		{
			this.tweenToScreen(game.getMenuScreen());
		}
	}


	public void show()
	{
		Gdx.app.log(XesEffects.LOG, "Showing SplashScreen");
		super.show();
		
		// preparing actors
		// - background:
		this.bgTexture = new Texture("data/splash_screen.png");
		this.bgTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureRegion bgTextureRegion = new TextureRegion(bgTexture, 0, 0, 512, 512);
		
		TextureRegionDrawable drawableBg = new TextureRegionDrawable(bgTextureRegion);
		
		this.bgImage = new Image(drawableBg, Scaling.fillX, Align.left | Align.top);
		this.bgImage.setFillParent(true);
		
		// building stage
		this.stage.clear();
		this.stage.addActor(this.bgImage);
		
		// show animation
		this.stage.addAction(
			sequence(
				fadeOut(0),
				moveTo(0, Gdx.graphics.getHeight() / 2),
				parallel(
					fadeIn(1.5f),
					moveTo(0, 0, 1.5f)
				)
			)
		);
	}


	public void dispose()
	{
		super.dispose();
		this.bgTexture.dispose();
	}
	
	
	public void tweenToScreen(final AbstractScreen targetScreen)
	{
		game.getTweenManager().killAll();
		
		Tween
		.to(this.bgColor, ColorAccessor.RGBA, 1.0f)
		.target(
			targetScreen.bgColor.r,
			targetScreen.bgColor.g,
			targetScreen.bgColor.b,
			targetScreen.bgColor.a
		)
		.start(game.getTweenManager());
		
		this.stage.addAction(
			sequence(
				parallel(
					fadeOut(1.5f),
					moveTo(0, Gdx.graphics.getHeight(), 2.0f)
				),
				new Action() {
					public boolean act(float delta)
					{
						game.setScreen(targetScreen);
						return true;
					}
				}
			)
		);
		
		this.bgImage.addAction(fadeOut(1.5f));
	}
}
