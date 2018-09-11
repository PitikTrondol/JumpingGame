package com.afriandi.project.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.afriandi.project.enums.ScreenEnums;
import com.afriandi.project.utils.AudioUtils;
import com.afriandi.project.utils.Constants;
import com.afriandi.project.utils.ResourceManager;

public class Splash extends GameScreen
{
	private static final String FIRST = "crazy";
	private static final String SECOND = "AF";
	private static final String THIRD = "presents";
	private static final String TITLE = "Take It!";
	
	private ScreenSwitchTask task;
	private SpriteBatch batch;
	private GlyphLayout  textLayout;
	private BitmapFont fontOne, fontTwo, fontTitle;
	
	private float captionX1;
	private float captionX2;
	private float captionX3;
	private float captionX4;
	
	private float captionY;
	private float captionY2;
	private float captionY3;
	
	private float stateTime;
	
	public Splash()
	{
		batch = new SpriteBatch();
		task = new ScreenSwitchTask(ScreenEnums.MAIN_MENU);
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Constants.FONT_NAME_ALPHA));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        
        parameter.size = 72;
        fontOne = generator.generateFont(parameter);
        
        generator = new FreeTypeFontGenerator(Gdx.files.internal(Constants.FONT_NAME_ROBOTO));
        parameter.size = 18;
        fontTwo = generator.generateFont(parameter);
        
        generator = new FreeTypeFontGenerator(Gdx.files.internal(Constants.FONT_NAME_TERRANCOND));
		parameter.size = 72;
		fontTitle = generator.generateFont(parameter);
		
        //Indie
		textLayout = new GlyphLayout();
//		TextBounds wholeCaptionBounds = fontOne.getBounds(FIRST + SECOND);
		textLayout.setText(fontOne, FIRST + SECOND);
		captionX1 = (Constants.SCREEN_WIDTH / 2) - (textLayout.width/2);
		captionY = (Constants.SCREEN_HEIGHT / 2) + textLayout.height + 7f;
		textLayout.setText(fontOne, FIRST);
		captionX2 = captionX1 + textLayout.width + 3f;
		
		//Presents
//		wholeCaptionBounds = fontTwo.getBounds(THIRD);
		textLayout.setText(fontTwo, THIRD);
		captionX3 = (Constants.SCREEN_WIDTH / 2) - (textLayout.width/2);
		captionY2 = (Constants.SCREEN_HEIGHT / 2) - 7f;
		
		//Take It
//		wholeCaptionBounds = fontTitle.getBounds(TITLE);
		textLayout.setText(fontTitle, TITLE);
		captionX4 = (Constants.SCREEN_WIDTH / 2) - (textLayout.width/2);
		captionY3 = (Constants.SCREEN_HEIGHT / 2) + (fontTitle.getCapHeight()/2);
		
		stateTime = 0f;
	}

	@Override
	public void render(float delta) 
	{
		if(stateTime < 6)
		{
			Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			batch.begin();
			fontOne.setColor(0f, 0f, 0f, 1f);
			fontOne.draw(batch, FIRST, captionX1, captionY);
			fontOne.setColor(0f, 0.3f, 0f, 1f);
			fontOne.draw(batch, SECOND, captionX2, captionY);

			fontTwo.setColor(0.2f, 0.2f, 0.2f, 1f);
			fontTwo.draw(batch, THIRD, captionX3, captionY2);
			batch.end();
		}
		else
		{
			Gdx.gl.glClearColor(0.15f, 0.15f, 0.15f, 1f);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			batch.begin();
			fontTitle.draw(batch, TITLE, captionX4, captionY3);
			batch.end();
		}

		stateTime += Gdx.graphics.getDeltaTime();
	}
	
	@Override
	public void resize(int width, int height) 
	{
		batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
	}
	
	@Override
	public void show() 
	{
		ResourceManager.instance().load();
		// Enter main menu after 8.0f seconds
		Timer.schedule(task, 12.0f);
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
		fontOne.dispose();
		fontTwo.dispose();
		fontTitle.dispose();
	}
}


