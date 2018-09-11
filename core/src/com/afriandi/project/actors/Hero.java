package com.afriandi.project.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.afriandi.project.box2d.HeroUserData;
import com.afriandi.project.enums.Difficulty;
import com.afriandi.project.screens.ScreenManager;
import com.afriandi.project.utils.AudioUtils;
import com.afriandi.project.utils.Constants;

public class Hero extends GameActor
{
	private boolean jumping;
	private boolean dodging;
	private boolean hit;
	private boolean faceRight = true;

    private float moveSpeed = 1f;
    private int jumpCount;
    
	private Animation<TextureRegion> runningAnimation;
    private TextureRegion jumpingTexture;
    private TextureRegion dodgingTexture;
    private TextureRegion hitTexture;
    private float stateTime;
    
    private Sound sfx_hit;
    private Sound sfx_jump;
    
	public Hero(Body newBody)
	{
		super(newBody);
		
		TextureAtlas textureAtlas = new TextureAtlas(Constants.HERO_ATLAS_PATH);
        TextureRegion[] runningFrames = new TextureRegion[Constants.HERO_RUNNING_REGION_NAMES.length];
        for (int i = 0; i < Constants.HERO_RUNNING_REGION_NAMES.length; i++)
        {
            String path = Constants.HERO_RUNNING_REGION_NAMES[i];
            runningFrames[i] = textureAtlas.findRegion(path);
        }
        
        runningAnimation = new Animation(0.07f, runningFrames);
        stateTime = 0f;
        jumpingTexture = textureAtlas.findRegion(Constants.HERO_JUMPING_REGION_NAME);
        dodgingTexture = textureAtlas.findRegion(Constants.HERO_DODGING_REGION_NAME);
        hitTexture = textureAtlas.findRegion(Constants.HERO_HIT_REGION_NAME);

        sfx_hit = AudioUtils.getInstance().getHitSound();
        sfx_jump =AudioUtils.getInstance().getJumpSound();
	}

	//======================================
	//Implementation from GameActor
	//======================================
	@Override
	public HeroUserData getUserData()
	{
		return (HeroUserData)actorData;
	}
	
	//======================================
	//Implementation from Actor
	//======================================
	@Override
    public void draw(Batch batch, float parentAlpha)
	{
        super.draw(batch, parentAlpha);
        
        float x = bodyRectangle.x - (bodyRectangle.width * 0.1f);
        float y = bodyRectangle.y;
        float width = bodyRectangle.width * 1.2f;
        
        if (dodging)
        {
            batch.draw(	dodgingTexture,
            			x,
            			y + bodyRectangle.height / 4,
            			width,
            			bodyRectangle.height * 3 / 4);
        }
        else if (hit)
        {
            // Draw Hero with rotation if applied (scale 1)
            batch.draw(	hitTexture,
            			x, //drawing x
            			y, //drawing y
            			bodyRectangle.width * 0.5f, //origin x
            			bodyRectangle.height * 0.5f, //origin y
            			width, 
            			bodyRectangle.height,
            			1f, 1f, // scale
            			(float) Math.toDegrees(body.getAngle())); //rotation relatives to body move
        }
        else if (jumping)
        {
            batch.draw(jumpingTexture, x, y, width, bodyRectangle.height);
        }
        else
        {
            // Running
        	batch.draw(runningAnimation.getKeyFrame(stateTime, true).getTexture(), 
        			x, 
        			y, 
        			bodyRectangle.width * 0.5f, 	//Origin X
        			bodyRectangle.height * 0.5f, 	//Origin Y
        			width, 
        			bodyRectangle.height, 
        			1f,	1f, 						//scale
        			0, 								//rotation
        			runningAnimation.getKeyFrame(stateTime, true).getRegionX(), //textureRegion x
        			runningAnimation.getKeyFrame(stateTime, true).getRegionY(), 
        			runningAnimation.getKeyFrame(stateTime, true).getRegionWidth(), 
        			runningAnimation.getKeyFrame(stateTime, true).getRegionHeight(),  
        			!faceRight, //flip x
        			false);
        }
    }
	
	@Override
    public void act(float delta)
	{
		if(ScreenManager.instance().getState() == Constants.STATE_PAUSED) return;
		super.act(delta);
		stateTime += Gdx.graphics.getDeltaTime();
	}
	
	public void jump()
	{
		if (!(jumping || dodging || hit) /**|| (jumpCount < 2)*/)
		{
            body.applyLinearImpulse(getUserData().getJumpingLinearImpulse(), body.getWorldCenter(), true);
            jumping = true;
            AudioUtils.getInstance().playSound(sfx_jump);
            jumpCount++;
        }
	}
	
	public void landed()
	{
		jumping = false;
	}
	
	public void move(boolean moveRight)
	{
		faceRight = moveRight;
		body.setLinearVelocity(	new Vector2(faceRight ? moveSpeed : -moveSpeed, 0));
	}
	
	public boolean isFacingRight()
	{
		return faceRight;
	}
	
	public void dodge()
	{
        if (!(jumping || hit))
        {
            body.setTransform(getUserData().getDodgePosition(), getUserData().getDodgeAngle());
            dodging = true;
        }
    }

    public void stopDodge()
    {
        dodging = false;
        body.setTransform(getUserData().getRunningPosition(), 0f);
    }

    public boolean isDodging()
    {
        return dodging;
    }
    
    public void hit()
    {
    	body.applyAngularImpulse(getUserData().getHitImpulse(), true);
        hit = true;
        AudioUtils.getInstance().playSound(sfx_hit);
    }
    
    public boolean isHit()
    {
    	return hit;
    }
    
    // Scoring and Difficulty
    public void onDifficultyChange(Difficulty newDifficulty)
    {
    	setGravityScale(newDifficulty.getHeroGravityScale());
    	getUserData().setJumpingLinearImpulse(newDifficulty.getHeroJumpingLinearImpulse());
    }
    
    public void setGravityScale(float gravityScale)
    {
    	body.setGravityScale(gravityScale);
    	body.resetMassData();
    }
    
    public int getJumpCount()
    {
    	return jumpCount;
    }
}
