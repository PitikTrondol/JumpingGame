package com.afriandi.project.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.afriandi.project.box2d.EnemyUserData;
import com.afriandi.project.screens.ScreenManager;
import com.afriandi.project.utils.Constants;
import com.afriandi.project.utils.DebugUtils;

public class Enemy extends GameActor
{
	private Animation<TextureRegion> animation;
    private float stateTime;
    private DebugUtils debug;
    
	public Enemy(Body body)
	{
		super(body);
		
		TextureAtlas textureAtlas = new TextureAtlas(Constants.ENEMY_ATLAS_PATH);
        TextureRegion[] runningFrames = new TextureRegion[getUserData().getTextureRegions().length];
        for (int i = 0; i < getUserData().getTextureRegions().length; i++)
        {
            String path = getUserData().getTextureRegions()[i];
            runningFrames[i] = textureAtlas.findRegion(path);
        }
        animation = new Animation(0.08f, runningFrames);
        stateTime = 0f;
        
        debug = new DebugUtils(ScreenManager.instance().getCamera());
	}
	
	//======================================
	//Implementation from GameActor
	//======================================
	@Override
    public EnemyUserData getUserData()
	{
        return (EnemyUserData) actorData;
    }
	
	//======================================
	//Implementation from Actor
	//======================================
	@Override
    public void act(float delta)
	{
		if(ScreenManager.instance().getState() == Constants.STATE_PAUSED) return;
		super.act(delta);
        stateTime += Gdx.graphics.getDeltaTime();
        
        body.setLinearVelocity(getUserData().getLinearVelocity());
    }
	
	@Override
    public void draw(Batch batch, float parentAlpha)
	{
        super.draw(batch, parentAlpha);
        
        batch.draw(animation.getKeyFrame(stateTime, true), 
        		(bodyRectangle.x - (bodyRectangle.width * 0.1f)), 
                bodyRectangle.y, bodyRectangle.width * 1.2f, bodyRectangle.height * 1.1f);
        
        debug.debugRectangle(batch, bodyRectangle, Constants.RED_COLOR);
    }

}
