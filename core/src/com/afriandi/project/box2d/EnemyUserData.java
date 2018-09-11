package com.afriandi.project.box2d;

import com.badlogic.gdx.math.Vector2;
import com.afriandi.project.enums.UserDataType;
import com.afriandi.project.utils.Constants;

public class EnemyUserData extends UserData
{
	private Vector2 linearVelocity;
	private String[] textureRegions;
	
	public EnemyUserData(float width, float height, String[] textureRegions)
	{
		super(width, height);
		userDataType = UserDataType.ENEMY;
		linearVelocity = Constants.ENEMY_LINEAR_VELOCITY;
		this.textureRegions = textureRegions;
	}
	
	public Vector2 getLinearVelocity()
	{
		return linearVelocity;
	}
	
	public void setLinearVelocity(Vector2 newVelocity)
	{
		this.linearVelocity = newVelocity;
	}
	
	public String[] getTextureRegions()
	{
		return textureRegions;
	}
}
