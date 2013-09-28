/*******************************************************************************
 * Copyright (c) 2013 Paweł Kapalla, Xessenix.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Paweł Kapalla, Xessenix - initial API and implementation
 ******************************************************************************/

package pl.xesenix.games.effects.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;

import pl.xesenix.games.effects.XesEffects;
import aurelienribon.tweenengine.Tween;
import com.badlogic.gdx.graphics.Color;

/**
 * @author Xesenix
 *
 */
public final class MenuScreen extends AbstractMenuScreen
{

	public MenuScreen(XesEffects game)
	{
		super(game);
		this.backgroundFileName = "data/menu_screen_bg.png";
	}
	
	
	public void resize(int width, int height)
	{
		Gdx.app.log(XesEffects.LOG, "Resizing MenuScreen");
		super.resize(width, height);
		
		Vector2 menuTitleSize = Scaling.fillX.apply(512f, 100f, width, height);
		
		this.layout.setPosition(0, 0);
		this.layout.setWidth(width);
		this.layout.setHeight(height - menuTitleSize.y);
		this.layout.invalidate();
	}
	
	
	protected void addUserInterface()
	{
		Gdx.app.log(XesEffects.LOG, "Building MainMenu UI..");
		// preparing layout
		this.layout = new Table();
		this.layout.setWidth(Gdx.graphics.getWidth());
		this.layout.align(Align.top);
		
		this.stage.addActor(layout);
		
		// adding buttons
		TextButton startButton = new TextButton("Choose effect", game.getSkin(), "default");
		startButton.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
				tweenToScreen(game.getEffectsGalleryScreen(0));
			}
		});
		
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
	}
}
