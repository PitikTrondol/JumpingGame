package com.afriandi.project.enums;

import com.badlogic.gdx.Screen;
import com.afriandi.project.screens.Credits;
import com.afriandi.project.screens.Gameplay;
import com.afriandi.project.screens.MainMenu;
import com.afriandi.project.screens.Paused;
import com.afriandi.project.screens.Result;
import com.afriandi.project.screens.Splash;

public enum ScreenEnums
{
	SPLASH 
	{
        @Override
        public com.badlogic.gdx.Screen getScreenInstance() 
        {
            return new Splash();
        }
    },

	MAIN_MENU 
	{
        @Override
        public com.badlogic.gdx.Screen getScreenInstance() 
        {
             return new MainMenu();
        }
    },
    
    GAMEPLAY 
    {
        @Override
        public com.badlogic.gdx.Screen getScreenInstance()
        {
             return new Gameplay();
        }
    },
    
    PAUSED 
    {
        @Override
        public com.badlogic.gdx.Screen getScreenInstance()
        {
             return new Paused();
        }
    },
    
    RESULT 
    {
        @Override
        public com.badlogic.gdx.Screen getScreenInstance()
        {
             return new Result();
        }
    },
    
    CREDITS 
    {
        @Override
		public com.badlogic.gdx.Screen getScreenInstance() 
        {
             return new Credits();
        }
    };
    
	public abstract Screen getScreenInstance();
}
