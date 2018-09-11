package com.afriandi.project.screens;

import com.afriandi.project.enums.ScreenEnums;
import com.afriandi.project.stages.AboutStage;

public class Credits extends GameScreen
{
	private AboutStage stage;
	public Credits()
	{
		stage = new AboutStage();
	}
	
	@Override
	public void render(float delta) 
	{
		stage.draw();
		stage.act(delta);
	}

	@Override
	public void hide() 
	{
		ScreenManager.instance().dispose(ScreenEnums.CREDITS);
	}
	
	@Override
	public void dispose() 
	{
		stage.dispose();
	}
}
