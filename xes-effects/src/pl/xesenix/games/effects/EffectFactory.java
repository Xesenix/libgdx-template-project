package pl.xesenix.games.effects;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.LinkedHashMap;

import pl.xesenix.games.effects.screens.EffectScreen;

public class EffectFactory
{
	private XesEffects game;
	
	
	private LinkedHashMap<String, EffectScreenConfig> effectsConfigs;
	

	public EffectFactory(XesEffects game)
	{
		this.game = game;
		this.effectsConfigs = new LinkedHashMap<String, EffectScreenConfig>();
	}
	
	public EffectFactory add(EffectScreenConfig config)
	{
		this.effectsConfigs.put(config.getName(), config);
		
		return this;
	}

	public EffectScreen getScreen(String name)
	{
		EffectScreenConfig config = this.effectsConfigs.get(name);
		String className = config.getEffectClass().getName();
		
		try
		{
			Class cl = Class.forName(className);
			Constructor con = cl.getConstructor(XesEffects.class, EffectScreenConfig.class);
			
			return (EffectScreen) con.newInstance(this.game, config);
		}
		catch (NoSuchMethodException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SecurityException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InstantiationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IllegalArgumentException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InvocationTargetException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Collection<EffectScreenConfig> getCollection()
	{
		return this.effectsConfigs.values();
	}

}
