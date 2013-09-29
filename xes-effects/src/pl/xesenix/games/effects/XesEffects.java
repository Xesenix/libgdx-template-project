/*******************************************************************************
 * Copyright (c) 2013 Paweł Kapalla, Xessenix. All rights reserved. This program
 * and the accompanying materials are made available under the terms of the GNU
 * Lesser Public License v2.1 which accompanies this distribution, and is
 * available at http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors: Paweł Kapalla, Xessenix - initial API and implementation
 ******************************************************************************/

package pl.xesenix.games.effects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.xesenix.games.effects.screens.AbstractScreen;
import pl.xesenix.games.effects.screens.EffectScreen;
import pl.xesenix.games.effects.screens.EffectsGalleryScreen;
import pl.xesenix.games.effects.screens.MenuScreen;
import pl.xesenix.games.effects.screens.SplashScreen;
import pl.xesenix.games.tweens.ColorAccessor;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * @author Xesenix
 *
 */
public class XesEffects extends Game
{
	public static final String LOG = "XesEffects";
	
	
	public static final float GAME_SCREEN_BASE_WIDTH = 480f;
	
	
	public static final float GAME_SCREEN_BASE_HEIGHT = 320f;
	
	
	private FPSLogger fpsLogger;
	
	
	private TweenManager tweenManager;
	
	
	/**
	 * UI Skin
	 */
	private Skin skin;


	private ScreenFactory<EffectScreenConfig> effectsCollection;


	@Override
	public void create()
	{
		Gdx.app.log(XesEffects.LOG, "Creating game object..");

		this.fpsLogger = new FPSLogger();
		this.tweenManager = new TweenManager();

		Tween.setCombinedAttributesLimit(4);
		Tween.registerAccessor(Color.class, new ColorAccessor());

		this.skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
		
		int index = 1;
		this.effectsCollection = new ScreenFactory<EffectScreenConfig>(this);
		this.effectsCollection.add(
			(new EffectScreenConfig(String.format("Effect %d", index++), EffectScreen.class))
			.setTitle("Test subject number 1")
		);

		this.setScreen(this.getSplashScreen());
	}


	@Override
	public void dispose()
	{
		super.render();
		this.skin.dispose();
	}


	@Override
	public void render()
	{
		final float delta = Gdx.graphics.getDeltaTime();

		super.render();

		this.tweenManager.update(delta);

		this.fpsLogger.log();
	}


	@Override
	public void resize(final int width, final int height)
	{
		super.resize(width, height);
	}


	@Override
	public void pause()
	{
		super.pause();
	}


	@Override
	public void resume()
	{
		super.resume();
	}


	/**
	 * @return splash screen
	 */
	public SplashScreen getSplashScreen()
	{
		return new SplashScreen(this);
	}


	/**
	 * @return menu screen
	 */
	public MenuScreen getMenuScreen()
	{
		return new MenuScreen(this);
	}


	/**
	 * @return effects gallery screen
	 */
	public EffectsGalleryScreen getEffectsGalleryScreen(int page)
	{
		return new EffectsGalleryScreen(this, page);
	}


	/**
	 * @return effects gallery screen
	 */
	public EffectScreen getEffectScreen(String name)
	{
		return this.getEffects().getScreen(name);
	}


	/**
	 * @return the tweenManager
	 */
	public TweenManager getTweenManager()
	{
		return tweenManager;
	}


	/**
	 * @return skin
	 */
	public Skin getSkin()
	{
		return this.skin;
	}


	/**
	 * @return the effectFactory
	 */
	public ScreenFactory<EffectScreenConfig> getEffects()
	{
		return effectsCollection;
	}
}
