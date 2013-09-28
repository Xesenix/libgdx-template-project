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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * @author Xesenix
 *
 */
public class AbstractScreen implements Screen
{

	protected XesEffects game;


	protected Skin skin;


	protected Stage stage;
	
	
	public Color bgColor = new Color(0.0f, 0.0f, 0.0f, 1);


	public AbstractScreen(XesEffects game)
	{
		this.game = game;
		this.skin = new Skin();
		this.stage = new Stage();
	}


	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(bgColor.r, bgColor.g, bgColor.b, bgColor.a);
		//Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		this.stage.act(delta);
		this.stage.draw();
		
		Table.drawDebug(this.stage);
	}


	@Override
	public void resize(int width, int height)
	{
		Gdx.app.log(XesEffects.LOG, "Resizing AbstractScreen");
		
		this.stage.setViewport(width, height, true);
	}


	@Override
	public void show()
	{
		Gdx.app.log(XesEffects.LOG, "Showing AbstractScreen");
		Gdx.input.setInputProcessor(this.stage);
		
		this.stage.clear();
	}


	@Override
	public void hide()
	{
		Gdx.app.log(XesEffects.LOG, "Hiding AbstractScreen");
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
		this.stage.dispose();
		this.skin.dispose();
	}

}
