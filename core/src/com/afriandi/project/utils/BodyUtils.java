package com.afriandi.project.utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.afriandi.project.box2d.UserData;
import com.afriandi.project.enums.UserDataType;

public class BodyUtils 
{
	public static boolean bodyIsHero(Body body)
	{
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.HERO;
    }

    public static boolean bodyIsGround(Body body)
    {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.GROUND;
    }
    
    public static boolean bodyInBounds(Body body)
    {
    	UserData userData = (UserData) body.getUserData();

        switch (userData.getUserDataType())
        {
            case HERO:
            case ENEMY:
                return body.getPosition().x + userData.getWidth() / 2 > 0;
        }
        
    	return true;
    }
    
    public static boolean isEnemy(Body body)
    {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.ENEMY;
    }
}
