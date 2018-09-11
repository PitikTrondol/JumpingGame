package com.afriandi.project.enums;

import com.afriandi.project.utils.Constants;

public enum ObstacleType 
{	    		
	STANDS_LONG(2f, 1f,
				Constants.ENEMY_X, 
				Constants.RUNNING_SHORT_ENEMY_Y,
				Constants.ENEMY_DENSITY,
				Constants.SLUG_REGION_NAMES,
				false),
				
	ROTATE_LONG(2f, 2f,
				Constants.ENEMY_X,
				Constants.RUNNING_LONG_ENEMY_Y, 
				Constants.ENEMY_DENSITY,
				Constants.BOAR_REGION_NAMES,
				false);
				
	
	private float width;
	private float height;
	private float x;
	private float y;
	private float density;
	private String[] regions;
	
	ObstacleType(float width, float height, float x, float y, float density, String[] regions, boolean rotate)
	{
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.density = density;
		this.regions = regions;
	}
	
	public float getWidth()
	{
		return width;
	}
	
	public float getHeight()
	{
		return height;
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	public float getDensity()
	{
		return density;
	}
	
	public String[] getRegions()
	{
		return regions;
	}
}
