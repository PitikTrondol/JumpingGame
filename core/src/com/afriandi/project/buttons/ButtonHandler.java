package com.afriandi.project.buttons;

import com.badlogic.gdx.Gdx;
import com.afriandi.project.buttons.GameButtons.ButtonListener;
import com.afriandi.project.utils.Constants;

public class ButtonHandler implements ButtonListener
{
	private int buttonId;
	private int state;
	public ButtonHandler(int state, int buttonId)
	{
		this.buttonId = buttonId;
		this.state = state;
	}
	
	@Override
	public void OnRelease() 
	{
		
		switch (state) 
		{
			case Constants.STATE_MAIN_MENU:
			{
				mainMenu_Action(buttonId);
			}
			break;
			case Constants.STATE_PAUSED:
			{
				pausedMenu_action(buttonId);
			}
			break;
			case Constants.STATE_CREDIT:
			{
				
			}
			break;
		}
	}
	
	@Override
	public int getId() 
	{
		return buttonId;
	}
	
	public void mainMenu_Action(int buttonId)
	{
		switch (buttonId) 
		{
			case Constants.BUTTON_EXIT:
				Gdx.app.exit();
			break;
			case Constants.BUTTON_MUSIC:
				System.out.println("BUTTON_MUSIC ----------------------- BUTTON_MUSIC");
			break;
			case Constants.BUTTON_SFX:
				System.out.println("BUTTON_SFX ----------------------- BUTTON_SFX");
			break;
		}
	}
	
	public void pausedMenu_action(int buttonId)
	{
		switch (buttonId) 
		{
			case Constants.BUTTON_EXIT:
				Gdx.app.exit();
				
			break;
			
			case Constants.BUTTON_MUSIC:
				System.out.println("BUTTON_SFX ----------------------- BUTTON_SFX");
			break;
			
			case Constants.BUTTON_SFX:
				System.out.println("BUTTON_SFX ----------------------- BUTTON_SFX");
			break;
		}
	}
	
}
