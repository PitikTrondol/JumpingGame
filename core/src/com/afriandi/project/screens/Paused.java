package com.afriandi.project.screens;

import com.afriandi.project.enums.ScreenEnums;
import com.afriandi.project.stages.AboutStage;

public class Paused extends GameScreen
{
	private AboutStage pause;
	public Paused()
	{
		pause = new AboutStage();
	}
	
	@Override
	public void render(float delta) 
	{
		pause.draw();
		pause.act(delta);
	}
	
	@Override
	public void hide() 
	{
		ScreenManager.instance().dispose(ScreenEnums.PAUSED);
	}
	
	@Override
	public void dispose() 
	{
		pause.dispose();
	}
}
