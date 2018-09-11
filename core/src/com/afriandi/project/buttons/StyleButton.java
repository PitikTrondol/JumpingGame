package com.afriandi.project.buttons;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.afriandi.project.utils.Constants;

public class StyleButton extends GameButtons 
{
	public StyleButton(Rectangle bounds, ButtonListener listener)
	{
		super(bounds, listener);
	}
	
	//======================================
	//from Class Button
	//======================================
	@Override
    public void act(float delta) 
	{
        super.act(delta);
        
        float oldX = bounds.x;
        float oldY = bounds.y;
        boolean hover = getClickListener().isOver();
        
        if(	!(getRegionName().equalsIgnoreCase("score") || getRegionName().equalsIgnoreCase("level")) )
        {
	    	this.setX(hover ? bounds.x + 2 : oldX);
	    	this.setY(hover ? bounds.y - 2 : oldY);
        }
    }
	
	 @Override
	 public void draw(Batch batch, float parentAlpha)
	 {
		 super.draw(batch, parentAlpha);
	 }
	
	//======================================
	//from Class GameButton
	//======================================
	@Override
	public void action() 
	{
		listener.OnRelease();
	}

	@Override
	protected String getRegionName() 
	{
		return Constants.BUTTON_REGION_NAME[listener.getId()];
	}
	
	public ButtonListener getListener()
	{
		return listener;
	}
}
