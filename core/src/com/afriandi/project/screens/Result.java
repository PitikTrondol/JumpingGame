package com.afriandi.project.screens;

import com.afriandi.project.enums.ScreenEnums;
import com.afriandi.project.stages.ResultStage;

public class Result extends GameScreen
{
	private ResultStage resultStage;
	public Result()
	{
		resultStage = new ResultStage();
	}
	
	@Override
	public void render(float delta) 
	{
		resultStage.draw();
		resultStage.act(delta);
	}
	
	@Override
	public void hide() 
	{
		ScreenManager.instance().dispose(ScreenEnums.RESULT);
	}
	
	@Override
	public void dispose() 
	{
		resultStage.dispose();
	}
}
