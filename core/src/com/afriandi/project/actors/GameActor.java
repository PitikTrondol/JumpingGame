package com.afriandi.project.actors;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.afriandi.project.box2d.UserData;
import com.afriandi.project.screens.ScreenManager;
import com.afriandi.project.utils.Constants;

public abstract class GameActor extends Actor
{
	protected Body body;
	protected UserData actorData;
	protected Rectangle bodyRectangle;
	
	public GameActor()
	{
		// 
	}
	
	public GameActor(Body body)
	{
		this.body = body;
		this.actorData = (UserData) body.getUserData();
		bodyRectangle = new Rectangle();
	}
	
	@Override
    public void act(float delta)
	{
		if(ScreenManager.instance().getState() == Constants.STATE_PAUSED)
        	return;
		
		super.act(delta);
        
        if (body.getUserData() != null)
        {
            updateRectangle();
        }
        else
        {
            // This means the world destroyed the body (enemy or runner went out of screen)
            remove();
        }
    }
	
	public abstract UserData getUserData();
	
	//keep the rect is same size with actor's 
	private void updateRectangle()
	{
        bodyRectangle.x = transformToScreen(body.getPosition().x - actorData.getWidth() / 2);
        bodyRectangle.y = transformToScreen(body.getPosition().y - actorData.getHeight() / 2);
        bodyRectangle.width = transformToScreen(actorData.getWidth());
        bodyRectangle.height = transformToScreen(actorData.getHeight());
    }

    protected float transformToScreen(float n)
    {
        return Constants.WORLD_TO_SCREEN * n;
    }
    
    public Rectangle getRect()
    {
    	return bodyRectangle;
    }
    
    public float getYVelocity()
    {
    	return this.body.getLinearVelocity().y;
    }
}
