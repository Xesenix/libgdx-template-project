/*******************************************************************************
 * Copyright (c) 2013 Paweł Kapalla, Xessenix. All rights reserved. This program
 * and the accompanying materials are made available under the terms of the GNU
 * Lesser Public License v2.1 which accompanies this distribution, and is
 * available at http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors: Paweł Kapalla, Xessenix - initial API and implementation
 ******************************************************************************/

package pl.xesenix.games.effects.screens;

import pl.xesenix.games.effects.XesEffects;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


public class AbstractScreen implements Screen
{

	protected Game game;


	protected SpriteBatch batch;


	protected OrthographicCamera camera;


	private Skin skin;


	public AbstractScreen(Game game)
	{
		this.game = game;
		this.batch = new SpriteBatch();
		this.skin = new Skin();
	}


	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}


	@Override
	public void resize(int width, int height)
	{
		this.camera.setToOrtho(false, width, height);
		this.camera.update();
	}


	@Override
	public void show()
	{
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, width, height);
		//this.camera.update();
		
		Gdx.app.log(XesEffects.LOG,
			String.format("pos (%.2f, %.2f, %.2f)",
				this.camera.position.x,
				this.camera.position.y,
				this.camera.position.z
			)
		);
	}


	@Override
	public void hide()
	{
		// TODO Auto-generated method stub

	}


	@Override
	public void pause()
	{
		// TODO Auto-generated method stub

	}


	@Override
	public void resume()
	{
		// TODO Auto-generated method stub

	}


	@Override
	public void dispose()
	{
		batch.dispose();
	}


	/**
	 * @return the skin
	 */
	public Skin getSkin()
	{
		return skin;
	}

}
