package pl.xesenix.games.effects;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.LinkedHashMap;

import pl.xesenix.games.effects.screens.IScreenConfig;
import pl.xesenix.games.effects.screens.EffectScreen;

public class ScreenFactory<T extends IScreenConfig>
{
	private XesEffects game;
	
	
	private LinkedHashMap<String, T> screenConfigs;
	

	public ScreenFactory(XesEffects game)
	{
		this.game = game;
		this.screenConfigs = new LinkedHashMap<String, T>();
	}
	
	public ScreenFactory<T> add(T config)
	{
		this.screenConfigs.put(config.getName(), config);
		
		return this;
	}

	public EffectScreen getScreen(String name)
	{
		T config = this.screenConfigs.get(name);
		String className = config.getScreenClass().getName();
		
		try
		{
			Class cl = Class.forName(className);
			Constructor con = cl.getConstructor(XesEffects.class, IScreenConfig.class);
			
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
	
	public Collection<T> getCollection()
	{
		return this.screenConfigs.values();
	}

}
