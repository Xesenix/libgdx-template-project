/**
 * 
 */

package pl.xesenix.games.tweens;

import com.badlogic.gdx.graphics.Color;

import aurelienribon.tweenengine.TweenAccessor;


/**
 * @author Xesenix
 * 
 */
public class ColorAccessor implements TweenAccessor<Color>
{
	public static final int RED = 0x1;


	public static final int GREEN = 0x2;


	public static final int BLUE = 0x4;


	public static final int ALPHA = 0x8;


	public static final int RGBA = 0xf;


	public static final int RGB = 0x7;


	@Override
	public int getValues(Color target, int tweenType, float[] returnValues)
	{
		int pointer = 0;
		
		if ((tweenType & ColorAccessor.RED) != 0)
		{
			returnValues[pointer++] = target.r;
		}

		if ((tweenType & ColorAccessor.GREEN) != 0)
		{
			returnValues[pointer++] = target.g;
		}

		if ((tweenType & ColorAccessor.BLUE) != 0)
		{
			returnValues[pointer++] = target.b;
		}

		if ((tweenType & ColorAccessor.ALPHA) != 0)
		{
			returnValues[pointer++] = target.a;
		}

		return pointer;
	}


	@Override
	public void setValues(Color target, int tweenType, float[] newValues)
	{
		int pointer = 0;

		if ((tweenType & ColorAccessor.RED) != 0)
		{
			target.r = newValues[pointer++];
		}

		if ((tweenType & ColorAccessor.GREEN) != 0)
		{
			target.g = newValues[pointer++];
		}

		if ((tweenType & ColorAccessor.BLUE) != 0)
		{
			target.b = newValues[pointer++];
		}

		if ((tweenType & ColorAccessor.ALPHA) != 0)
		{
			target.a = newValues[pointer++];
		}
	}

}
