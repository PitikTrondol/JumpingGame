package com.afriandi.project.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.afriandi.project.enums.ScreenEnums;
import com.afriandi.project.utils.ResourceManager;

public class Splash extends GameScreen
{
	private static final String FIRST = "crazy";
	private static final String SECOND = "AF";
	private static final String THIRD = "presents";
//	private static final String TITLE = "Take It!";
	
	private ScreenSwitchTask task;
	private SpriteBatch batch;
	private BitmapFont fontOne, fontTwo;
	
	private float crazyX;
	private float asFuckX;
	private float presentX;

	private float crazyAsFuckY;
	private float presentY;

	public Splash()
	{
		batch = new SpriteBatch();
		task = new ScreenSwitchTask(ScreenEnums.MAIN_MENU);

        fontOne = ResourceManager.instance().getFontSplash();
        fontTwo = ResourceManager.instance().getFontMid();

        int screenMidW = Gdx.graphics.getWidth() >> 1;
        int screenMidH = Gdx.graphics.getHeight() >> 1;

        //Crazy As Fuck
        GlyphLayout textLayout = new GlyphLayout();
		textLayout.setText(fontOne, FIRST + SECOND);
		crazyX = (screenMidW) - (textLayout.width / 2);
		crazyAsFuckY = (screenMidH) + (textLayout.height / 2);
		textLayout.setText(fontOne, FIRST);
		asFuckX = crazyX + textLayout.width + 5.0f;

		//Presents
        presentY = (screenMidH) - (textLayout.height / 2) - 3.0f;
        textLayout.setText(fontTwo, THIRD);
        presentX = (screenMidW) - (textLayout.width / 2);
	}

	@Override
	public void render(float delta)
	{
        Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        fontOne.setColor(0f, 0f, 0f, 1f);
        fontOne.draw(batch, FIRST, crazyX, crazyAsFuckY);
        fontOne.setColor(0f, 0.3f, 0f, 1f);
        fontOne.draw(batch, SECOND, asFuckX, crazyAsFuckY);

        fontTwo.setColor(0.2f, 0.2f, 0.2f, 1f);
        fontTwo.draw(batch, THIRD, presentX, presentY);
        batch.end();
    }
	
	@Override
	public void resize(int width, int height) 
	{
		batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
	}
	
	@Override
	public void show() 
	{
		Timer.schedule(task, 6.0f);
	}
	
	@Override
	public void hide() 
	{
		ScreenManager.instance().dispose(ScreenEnums.SPLASH);
	}
	
	@Override
	public void dispose() 
	{
		batch.dispose();
	}
}


