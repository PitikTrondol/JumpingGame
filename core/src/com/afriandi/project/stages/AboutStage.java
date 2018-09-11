package com.afriandi.project.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.afriandi.project.buttons.StyleButton;
import com.afriandi.project.buttons.GameButtons.ButtonListener;
import com.afriandi.project.enums.ScreenEnums;
import com.afriandi.project.screens.ScreenSwitchTask;
import com.afriandi.project.utils.Constants;
import com.afriandi.project.utils.ResourceManager;

public class AboutStage extends Stage
{
	// This will be our viewport measurements while working with the debug renderer
    private static final int VIEWPORT_WIDTH = Constants.SCREEN_WIDTH;
    private static final int VIEWPORT_HEIGHT = Constants.SCREEN_HEIGHT;

	//Buttons
	private Texture bg;
	private SpriteBatch batch;
	private ScreenSwitchTask backToMainMenu;
	private BitmapFont fontBig, fontMid, fontSmall;

	public AboutStage()
	{
		super(new ScalingViewport(Scaling.stretch, VIEWPORT_WIDTH, VIEWPORT_HEIGHT, new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT)));
		
		batch 			= new SpriteBatch();
		bg 				= new Texture(Gdx.files.internal(Constants.ABOUT_BG));
		backToMainMenu 	= new ScreenSwitchTask(ScreenEnums.MAIN_MENU);
		
		fontBig 		= ResourceManager.instance().getFontBig();
		fontMid 		= ResourceManager.instance().getFontMid();
		fontSmall 		= ResourceManager.instance().getFontSmall();
		
		setupButtons();
		Gdx.input.setInputProcessor(this);
	}
	
	@Override
	public void act(float delta)
	{
		super.act(delta);
	}
	
	@Override
    public void draw()
	{
		super.draw();
		float posY = 450f;
		float lineSpace = 10f;
		batch.begin();
		batch.draw(bg, 0, 0);
		
		drawString(fontBig, "Take It", 0, posY, 800);
		posY = posY - fontBig.getCapHeight() - lineSpace;
		drawString(fontSmall, Constants.CREDIT_LIST[0], Constants.CREDIT_LIST[1], 100f, posY, 600f);
		
		posY = posY - fontMid.getCapHeight() - lineSpace - 50;
		for(int i = 4; i < Constants.CREDIT_LIST.length - 1; i+=2)
		{
			drawString(fontMid, Constants.CREDIT_LIST[i], Constants.CREDIT_LIST[i + 1], 100f, posY, 600f);
			posY = posY - fontMid.getCapHeight() - lineSpace - 15;
		}
		
		posY = posY - 20;
		drawString(fontSmall, Constants.CREDIT_LIST[2], 100f, posY, 600f);
		
		posY = posY - fontMid.getCapHeight() - lineSpace + 5;
		drawString(fontMid, "afriandi.haryanto", "@live.com", 100f, posY, 600f);
		
		batch.end();
		// super.draw();
	}
	
	private void drawString(BitmapFont font, String text, float x, float y, float width)
	{
		drawString(font, text, x, y, width, Align.center);
	}
	
	private void drawString(BitmapFont font, String text, float x, float y, float width, int alignment)
	{
		drawString(font, text, x, y, width, alignment, Constants.PALETTE_GREY);
	}
	
	private void drawString(BitmapFont font, String text, String text2, float x, float y, float width)
	{
		drawString(font, text, text2, x, y, width, Align.center, Constants.PALETTE_GREEN, Constants.PALETTE_RED);
	}
	
	private void drawString(BitmapFont font, String text, float x, float y, float width, int alignment, Color palette)
	{
		drawString(font, text, "", x, y, width, alignment, palette, null);
	}
	
	private void drawString(BitmapFont font, String first, String second, float x, float y, float width, int alignment, Color palette1, Color palette2)
	{
		GlyphLayout  textLayout = new GlyphLayout();
		textLayout.setText(font, first + second);

//		float firstStringW 		= font.getBounds(first).width;
//		float secondStringW 	= font.getBounds(second).width;
//		float fullStringWidth 	= font.getBounds(first + second).width;
		float startX 			= 0;
		
		if(alignment == Align.left)
			startX = x;
		else if(alignment == Align.center)
			startX = x + (width / 2) - (textLayout.width / 2);
		else if(alignment == Align.right)
			startX = (x + width) - textLayout.width;
		
		//palette 
		if(palette1 != Constants.PALETTE_GREY )
			font.setColor(palette1);

		textLayout.setText(font, first);
		font.draw(batch, textLayout, startX, y+textLayout.width / 3);
		
		// break process if only one text
		if(second.equalsIgnoreCase("")) return;
		
		if(palette2 != Constants.PALETTE_GREY)
			font.setColor(palette2);

		textLayout.setText(font, second);
//		font.drawWrapped(batch, second, startX + firstStringW, y, secondStringW, BitmapFont.HAlignment.CENTER);
		font.draw(batch, textLayout, startX, y+textLayout.width / 3);
		font.setColor(Constants.PALETTE_GREY);
	}
	
	private void setupButtons()
	{
		// StyleButton
		float posX = 5;
		float posY = VIEWPORT_HEIGHT - 5 - Constants.STYLE_BUTTON_WH;
		Rectangle bounds = new Rectangle(posX, posY, Constants.STYLE_BUTTON_WH, Constants.STYLE_BUTTON_WH);
		StyleButton yieldButton = new StyleButton(bounds, new GameYieldButtonListener());
		addActor(yieldButton);
	}
	
	private class GameYieldButtonListener implements ButtonListener
	{
		@Override
		public void OnRelease() 
		{
			Timer.schedule(backToMainMenu, 0.2f);
		}

		@Override
		public int getId()
		{
			return Constants.BUTTON_YIELD;
		}
		
	}
}
