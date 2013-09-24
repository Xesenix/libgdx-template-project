/*******************************************************************************
 * Copyright (c) 2013 Paweł Kapalla, Xessenix. All rights reserved. This program
 * and the accompanying materials are made available under the terms of the GNU
 * Lesser Public License v2.1 which accompanies this distribution, and is
 * available at http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors: Paweł Kapalla, Xessenix - initial API and implementation
 ******************************************************************************/
/**
 * 
 */

package pl.xesenix.games.effects.screens;

import pl.xesenix.games.effects.XesEffects;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


/**
 * @author Xesenix
 * 
 */
public final class SplashScreen extends AbstractScreen implements Screen
{

	private Texture bgTexture;


	private TextureRegion bgTextureRegion;


	/**
	 * 
	 */
	public SplashScreen(Game game)
	{
		super(game);
	}


	/*
	 * (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#render(float)
	 */
	@Override
	public void render(float delta)
	{
		super.render(delta);

		
		this.batch.setProjectionMatrix(camera.combined);
		
		this.batch.begin();
		this.batch.draw(bgTextureRegion, 0, 0, Gdx.graphics.getWidth(),	Gdx.graphics.getHeight());
		this.batch.end();

	}


	public void show()
	{
		super.show();
		
		this.bgTexture = new Texture("data/splash_screen.png");
		this.bgTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		this.bgTextureRegion = new TextureRegion(bgTexture,
			0,
			0,
			512,
			(int) (512 * ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth()))
		);
	}


	public void resize(int width, int height)
	{
		super.resize(width, height);
		// repair of aspect ratio
		this.bgTextureRegion = new TextureRegion(bgTexture, 0, 0, 512, (int) (512 * ((float) height / (float) width)));

		Gdx.app.log(XesEffects.LOG,
			String.format("region (%d, %d) resized (%d, %d) graphics (%d, %d) camera (%.2f, %.2f)",
				this.bgTextureRegion.getRegionWidth(),
				this.bgTextureRegion.getRegionHeight(),
				width,
				height,
				Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(),
				this.camera.viewportWidth,
				this.camera.viewportHeight
			)
		);
	}


	public void dispose()
	{
		super.dispose();
		bgTexture.dispose();
	}
}
