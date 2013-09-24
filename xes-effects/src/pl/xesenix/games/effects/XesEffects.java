/*******************************************************************************
 * Copyright (c) 2013 Paweł Kapalla, Xessenix. All rights reserved. This program
 * and the accompanying materials are made available under the terms of the GNU
 * Lesser Public License v2.1 which accompanies this distribution, and is
 * available at http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors: Paweł Kapalla, Xessenix - initial API and implementation
 ******************************************************************************/

package pl.xesenix.games.effects;

import pl.xesenix.games.effects.screens.AbstractScreen;
import pl.xesenix.games.effects.screens.SplashScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;


public class XesEffects extends Game
{
	public static final String LOG = XesEffects.class.getSimpleName();


	private FPSLogger fpsLogger;


	@Override
	public void create()
	{
		Gdx.app.log(XesEffects.LOG, "Creating game object..");
		fpsLogger = new FPSLogger();
		this.setScreen(this.getSplashScreen());
	}


	@Override
	public void dispose()
	{
		super.render();
	}


	@Override
	public void render()
	{
		super.render();
		fpsLogger.log();
	}


	@Override
	public void resize(final int width, final int height)
	{
		super.resize(width, height);
	}


	@Override
	public void pause()
	{
	}


	@Override
	public void resume()
	{
	}


	public AbstractScreen getSplashScreen()
	{
		return new SplashScreen(this);
	}
}
