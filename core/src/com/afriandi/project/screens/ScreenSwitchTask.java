package com.afriandi.project.screens;

import com.badlogic.gdx.utils.Timer.Task;
import com.afriandi.project.enums.ScreenEnums;

public class ScreenSwitchTask extends Task
{
	private ScreenEnums screen = null;
	public ScreenSwitchTask(ScreenEnums screen) 
	{
		this.screen = screen;
	}

	@Override
	public void run()
	{
		ScreenManager.instance().show(screen);
	}
}
