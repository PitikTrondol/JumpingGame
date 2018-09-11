package com.afriandi.project.enums;

import com.afriandi.project.utils.Constants;

public enum EnemyType
{
	//Underground Enemy ==============================================
	RUNNING_WIDE_UNDERGROUND(	3f, 2f,
    				Constants.ENEMY_X, 
    				Constants.RUNNING_SHORT_ENEMY_Y,
    				Constants.ENEMY_DENSITY,
    				Constants.SLUG_REGION_NAMES),
    				
    RUNNING_BIG_UNDERGROUND(	2.5f, 2.5f,
    				Constants.ENEMY_X,
    				Constants.RUNNING_LONG_ENEMY_Y, 
    				Constants.ENEMY_DENSITY,
    				Constants.RAT_REGION_NAMES),
    				
    FLYING_SMALL_UNDERGROUND(	1.5f, 1.5f,
    				Constants.ENEMY_X, 
    				Constants.FLYING_ENEMY_Y, 
    				Constants.ENEMY_DENSITY,
    				Constants.LADYBUG_REGION_NAMES),
    				
    FLYING_WIDE_UNDERGROUND(	2.0f, 2f, 
    				Constants.ENEMY_X, 
    				Constants.FLYING_ENEMY_WIDE_Y, 
    				Constants.ENEMY_DENSITY,
    				Constants.UNDERGROUND_BEE_REGION_NAMES),
	    				
	//Padang Pasir =========================================================
    RUNNING_WIDE_DESERT(	3f, 2f,
    				Constants.ENEMY_X, 
    				Constants.RUNNING_SHORT_ENEMY_Y,
    				Constants.ENEMY_DENSITY,
    				Constants.SNAKE_REGION_NAMES),
    				
    RUNNING_BIG_DESERT(	2.5f, 2.5f,
    				Constants.ENEMY_X,
    				Constants.RUNNING_LONG_ENEMY_Y, 
    				Constants.ENEMY_DENSITY,
    				Constants.LIZARD_REGION_NAMES),
    				
    FLYING_SMALL_DESERT(	1.5f, 1.5f,
    				Constants.ENEMY_X, 
    				Constants.FLYING_ENEMY_Y, 
    				Constants.ENEMY_DENSITY,
    				Constants.FOREST_BEE_REGION_NAMES),
    				
    FLYING_WIDE_DESERT(	4.0f, 2f, 
    				Constants.ENEMY_X, 
    				Constants.FLYING_ENEMY_WIDE_Y, 
    				Constants.ENEMY_DENSITY,
    				Constants.VULTURE_REGION_NAMES),
    				
	//Hutan Enemy =========================================================
	RUNNING_WIDE_FOREST(	3f, 2f,
    				Constants.ENEMY_X, 
    				Constants.RUNNING_SHORT_ENEMY_Y,
    				Constants.ENEMY_DENSITY,
    				Constants.SNAKE_REGION_NAMES),
    				
    RUNNING_BIG_FOREST(	2.5f, 2.5f,
    				Constants.ENEMY_X,
    				Constants.RUNNING_LONG_ENEMY_Y, 
    				Constants.ENEMY_DENSITY,
    				Constants.BOAR_REGION_NAMES),
    				
    FLYING_SMALL_FOREST(	1.5f, 1.5f,
    				Constants.ENEMY_X, 
    				Constants.FLYING_ENEMY_Y, 
    				Constants.ENEMY_DENSITY,
    				Constants.LADYBUG_REGION_NAMES),
    				
    FLYING_WIDE_FOREST(	2.0f, 2f, 
    				Constants.ENEMY_X, 
    				Constants.FLYING_ENEMY_WIDE_Y, 
    				Constants.ENEMY_DENSITY,
    				Constants.FOREST_BEE_REGION_NAMES);

    private float width;
    private float height;
    private float x;
    private float y;
    private float density;
    private String[] regions;

    EnemyType(float width, float height, float x, float y, float density, String[] regions)
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
