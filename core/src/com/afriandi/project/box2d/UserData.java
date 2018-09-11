package com.afriandi.project.box2d;

import com.afriandi.project.enums.UserDataType;

public abstract class UserData
{
	protected UserDataType userDataType;
	protected float width;
	protected float height;
	
	public UserData()
	{
		//
	}
	
	public UserData(float width, float height)
	{
		this.width = width;
		this.height = height;
	}
	
	public UserDataType getUserDataType()
	{
		return userDataType;
	}
	
	public float getWidth()
	{
		return width;
	}
	
	public void setWidth(float newWidth)
	{
		this.width = newWidth;
	}
	
	public float getHeight()
	{
		return height;
	}
	
	public void setHeight(float newHeight)
	{
		this.height = newHeight;
	}
}
