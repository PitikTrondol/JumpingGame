package com.afriandi.project.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.afriandi.project.enums.ScreenEnums;
import com.afriandi.project.stages.MainMenuStage;
import com.afriandi.project.utils.AudioUtils;

public class MainMenu extends GameScreen
{
	private MainMenuStage mainStage;
	private Music mainMenuMusic;
	
	public MainMenu()
	{
		mainStage = new MainMenuStage();
		mainMenuMusic = AudioUtils.getInstance().getMM_Music();
	}
	
	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		mainStage.draw();
		mainStage.act(delta);
	}
	
	@Override
	public void hide() 
	{
		AudioUtils.getInstance().stopMusic(mainMenuMusic);
		ScreenManager.instance().dispose(ScreenEnums.MAIN_MENU);
	}
	
	@Override
	public void dispose() 
	{
		mainStage.dispose();
	}
}
