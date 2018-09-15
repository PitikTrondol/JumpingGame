package com.afriandi.project;

import com.afriandi.project.screens.ScreenManager;
import com.afriandi.project.utils.AudioUtils;
import com.afriandi.project.utils.ResourceManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class TakeItGame extends Game {

	@Override
	public void create()
	{
		ScreenManager.instance().initialize(this);
	}

	@Override
	public void dispose()
	{
		super.dispose();
		ScreenManager.instance().dispose();
		ResourceManager.instance().dispose();
		AudioUtils.dispose();
	}
}
