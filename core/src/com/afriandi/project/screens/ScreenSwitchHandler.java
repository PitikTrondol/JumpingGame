package com.afriandi.project.screens;

import com.afriandi.project.buttons.GameButtons.ButtonListener;
import com.afriandi.project.enums.ScreenEnums;
import com.afriandi.project.utils.AudioUtils;

public class ScreenSwitchHandler implements ButtonListener
{
	private ScreenEnums screenEnums;
	private boolean isTutorial;
	public ScreenSwitchHandler(ScreenEnums screenEnums)
	{
		this.screenEnums = screenEnums;
	}
	
	public ScreenSwitchHandler(ScreenEnums screenEnums, boolean tutorial)
	{
		this.screenEnums = screenEnums;
		this.isTutorial = tutorial;
	}
	
	@Override
	public void OnRelease() 
	{
		//AudioUtils.getInstance().playSound(AudioUtils.getInstance().getHitSound());
		ScreenManager.instance().setTutorial(isTutorial);
		ScreenManager.instance().show(screenEnums);
	}

	@Override
	public int getId() 
	{
		return -1;
	}

}
