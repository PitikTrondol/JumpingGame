package com.afriandi.project.box2d;

import com.badlogic.gdx.math.Vector2;
import com.afriandi.project.enums.UserDataType;
import com.afriandi.project.utils.Constants;

public class HeroUserData extends UserData
{
	private final Vector2 runningPosition = new Vector2(Constants.HERO_X, Constants.HERO_Y);
    private final Vector2 dodgePosition = new Vector2(Constants.HERO_DODGE_X, Constants.HERO_DODGE_Y);
    private Vector2 jumpingLinearImpulse;
	
	public HeroUserData(float width, float height)
	{
		super(width, height);
		jumpingLinearImpulse = Constants.HERO_JUMPING_LINEAR_IMPULSE;
		userDataType = UserDataType.HERO;
	}
	
	public Vector2 getJumpingLinearImpulse()
	{
		return jumpingLinearImpulse;
	}
	
	public void setJumpingLinearImpulse(Vector2 newImpulse)
	{
		this.jumpingLinearImpulse = newImpulse;
	}
	
	public float getDodgeAngle()
	{
        // In radians
        return (float) (-90f * (Math.PI / 180f));
    }

    public Vector2 getRunningPosition()
    {
        return runningPosition;
    }

    public Vector2 getDodgePosition()
    {
        return dodgePosition;
    }
    
    public float getHitImpulse()
    {
    	return Constants.HERO_HIT_ANGULAR_IMPULSE;
    }
}
