/**
 * 
 */
package pl.xesenix.games.effects.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import pl.xesenix.games.effects.EffectScreenConfig;
import pl.xesenix.games.effects.XesEffects;

/**
 * @author Xesenix
 *
 */
public class EffectsGalleryScreen extends AbstractMenuScreen
{
	public static final int COLUMN_COUNT = 4;
	
	
	public static final int ITEM_PER_PAGE = 16;
	
	
	private int page;
	
	
	/**
	 * @param game
	 */
	public EffectsGalleryScreen(XesEffects game, int page)
	{
		super(game);
		this.page = page;
	}
	
	
	@Override
	protected void addUserInterface()
	{
		Gdx.app.log(XesEffects.LOG, "Building EffectGallery UI..");
		// preparing layout
		this.layout = new Table();
		this.layout.setFillParent(true);
		
		this.stage.addActor(layout);
		
		float cellWidth = XesEffects.GAME_SCREEN_BASE_WIDTH / (float) EffectsGalleryScreen.COLUMN_COUNT;
		float cellHeight = XesEffects.GAME_SCREEN_BASE_HEIGHT / (float) ((EffectsGalleryScreen.ITEM_PER_PAGE - 1) / EffectsGalleryScreen.COLUMN_COUNT + 2);
		
		Collection<EffectScreenConfig> effectsCollection = this.game.getEffectFactory().getCollection();
		List<EffectScreenConfig> allEffectsList = new ArrayList<EffectScreenConfig>(effectsCollection);
		int fromItemIndex = Math.min(page * EffectsGalleryScreen.ITEM_PER_PAGE, allEffectsList.size());
		int toItemIndex = Math.min((page + 1) * EffectsGalleryScreen.ITEM_PER_PAGE, allEffectsList.size());
		List<EffectScreenConfig> effects = allEffectsList.subList(fromItemIndex, toItemIndex);
		int i = 0;
		
		for (final EffectScreenConfig config: effects)
		{
			if (i++ % EffectsGalleryScreen.COLUMN_COUNT == 0)
			{
				this.layout.row();
			}
			
			TextButton effectButton = new TextButton(String.format("%d", EffectsGalleryScreen.ITEM_PER_PAGE * page + i), game.getSkin(), "default");
			effectButton.addListener(new ClickListener() {
				public void clicked(InputEvent event, float x, float y)
				{
					tweenToScreen(game.getEffectScreen(config.getName()));
				}
			});
			
			this.layout.add(effectButton).width(cellWidth).height(cellHeight);
		}
		
		while (i < EffectsGalleryScreen.COLUMN_COUNT)
		{
			this.layout.add().width(cellWidth).height(cellHeight);
			i++;
		}
		
		this.layout.row();
		
		while ((i - 1) / EffectsGalleryScreen.COLUMN_COUNT < (EffectsGalleryScreen.ITEM_PER_PAGE - 1) / EffectsGalleryScreen.COLUMN_COUNT)
		{
			i += EffectsGalleryScreen.COLUMN_COUNT;
			this.layout.add().width(XesEffects.GAME_SCREEN_BASE_WIDTH).height(cellHeight).colspan(EffectsGalleryScreen.COLUMN_COUNT);
			this.layout.row();
		}
		
		if (page > 0)
		{
			TextButton prevButton = new TextButton("<", game.getSkin(), "default");
			prevButton.addListener(new ClickListener() {
				public void clicked(InputEvent event, float x, float y)
				{
					changePage(page - 1);
				}
			});
			
			this.layout.add(prevButton).width(cellWidth);
		}
		else
		{
			this.layout.add();
		}
		
		TextButton backButton = new TextButton("main menu", game.getSkin(), "default");
		backButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y)
			{
				tweenToScreen(game.getMenuScreen());
			}
		});
		
		this.layout.add(backButton).width((EffectsGalleryScreen.COLUMN_COUNT - 2) * cellWidth).colspan(EffectsGalleryScreen.COLUMN_COUNT - 2);
		
		if (page < allEffectsList.size() / EffectsGalleryScreen.ITEM_PER_PAGE)
		{
			TextButton nextButton = new TextButton(">", game.getSkin(), "default");
			nextButton.addListener(new ClickListener() {
				public void clicked(InputEvent event, float x, float y)
				{
					changePage(page + 1);
				}
			});
			
			this.layout.add(nextButton).width(cellWidth);
		}
		else
		{
			this.layout.add();
		}
		
		//this.layout.debug();
	}
	
	
	public EffectsGalleryScreen setPage(int page)
	{
		this.page = page;
		
		return this;
	}
	
	
	public void changePage(final int page)
	{
		final EffectsGalleryScreen self = this;
		
		stage.addAction(
			sequence(
				delay(tweenOutUserInterface()),
				new Action() {
					public boolean act(float delta)
					{
						self.setPage(page);
						self.addUserInterface();
						self.tweenInUserInterface();
						return true;
					}
				}
			)
		);
	}
}
