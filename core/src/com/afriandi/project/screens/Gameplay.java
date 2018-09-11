package com.afriandi.project.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.afriandi.project.enums.ScreenEnums;
import com.afriandi.project.stages.GameplayStage;
import com.afriandi.project.utils.AudioUtils;

public class Gameplay extends GameScreen
{
	private GameplayStage stage;
	private Music gameplayMusic;
	
	public Gameplay()
	{
		gameplayMusic = AudioUtils.getInstance().getAP_Music();
		stage = new GameplayStage();
	}
	
	@Override
	public void render(float delta)
	{
		//clear screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.draw();
		stage.act(delta);
	}
	
	@Override
	public void hide() 
	{
		AudioUtils.getInstance().stopMusic(gameplayMusic);
		ScreenManager.instance().dispose(ScreenEnums.GAMEPLAY);
	}
	
	@Override
	public void dispose() 
	{
		stage.dispose();
	}
}
