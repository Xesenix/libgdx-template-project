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


	private TextureRegion bgTextureRegion;


	/**
	 * 
	 */
	public SplashScreen(Game game)
	{
		super(game);
	}


	public void show()
	{
		super.show();
		
		this.bgTexture = new Texture("data/splash_screen.png");
		this.bgTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}


	public void resize(int width, int height)
	{
		super.resize(width, height);
		
		this.bgTextureRegion = new TextureRegion(bgTexture, 0, 0, 512, 512);
		
		TextureRegionDrawable drawableBg = new TextureRegionDrawable(this.bgTextureRegion);
		
		Image splashImage = new Image(drawableBg, Scaling.fillX, Align.left | Align.top);
		splashImage.setBounds(0, 0, width, height);
		
		this.stage.clear();
		this.stage.addActor(splashImage);
		this.stage.setViewport(width, height, true);
		
		Gdx.app.log(XesEffects.LOG, String.format("image(%.2f, %.2f)", splashImage.getImageWidth(), splashImage.getImageHeight()));
	}


	public void dispose()
	{
		super.dispose();
		bgTexture.dispose();
	}
}
