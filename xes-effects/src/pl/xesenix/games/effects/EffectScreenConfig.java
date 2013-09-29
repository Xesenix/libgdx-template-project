package pl.xesenix.games.effects;

import pl.xesenix.games.effects.screens.IScreenConfig;

public class EffectScreenConfig implements IScreenConfig
{
	private String name;
	
	
	private Class screenClass;


	private String title;
	
	
	public EffectScreenConfig(String name, Class screenClass)
	{
		setName(name);
		setScreenClass(screenClass);
	}
	
	
	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public EffectScreenConfig setName(String name)
	{
		this.name = name;
		
		return this;
	}

	/**
	 * @return the effectClass
	 */
	public Class getScreenClass()
	{
		return screenClass;
	}

	/**
	 * @param screenClass the effectClass to set
	 */
	public EffectScreenConfig setScreenClass(Class screenClass)
	{
		this.screenClass = screenClass;
		
		return this;
	}


	public String getTitle()
	{
		return this.title;
	}
	
	
	/**
	 * @param String title
	 * @return EffectScreenConfig
	 */
	public EffectScreenConfig setTitle(String title)
	{
		this.title = title;
		
		return this;
	}
}
