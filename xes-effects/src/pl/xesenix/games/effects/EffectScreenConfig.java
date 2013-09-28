package pl.xesenix.games.effects;

public class EffectScreenConfig
{
	private String name;
	
	
	private Class effectClass;
	
	
	public EffectScreenConfig(String name, Class<?> effectClass)
	{
		setName(name);
		setEffectClass(effectClass);
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
	public Class getEffectClass()
	{
		return effectClass;
	}

	/**
	 * @param effectClass the effectClass to set
	 */
	public EffectScreenConfig setEffectClass(Class effectClass)
	{
		this.effectClass = effectClass;
		
		return this;
	}
}
