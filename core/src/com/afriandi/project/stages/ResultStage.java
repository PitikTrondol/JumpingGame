package com.afriandi.project.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.afriandi.project.buttons.StyleButton;
import com.afriandi.project.buttons.GameButtons.ButtonListener;
import com.afriandi.project.enums.ScreenEnums;
import com.afriandi.project.screens.ScreenManager;
import com.afriandi.project.screens.ScreenSwitchTask;
import com.afriandi.project.utils.Constants;
import com.afriandi.project.utils.ResourceManager;

public class ResultStage extends Stage
{
	// This will be our viewport measurements while working with the debug renderer
    private static final int VIEWPORT_WIDTH = Constants.SCREEN_WIDTH;
    private static final int VIEWPORT_HEIGHT = Constants.SCREEN_HEIGHT;
    private float valueX;
    private float fieldX, firstLineY, lineHeight;
    private int pauseBG_X, pauseBG_Y;
    
	// BG
	private TextureRegion pauseBG;
	private Texture bg, alpha;
	private SpriteBatch batch;
	private BitmapFont font;
	private GlyphLayout layout;
	
	private ScreenSwitchTask backToMainMenu;
	
	public ResultStage()
	{
		super(new ScalingViewport(Scaling.stretch, VIEWPORT_WIDTH, VIEWPORT_HEIGHT, new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT)));
		
		String name 	= Constants.RESULT_SCREEN_BG + ScreenManager.instance().getLevel();
		bg 				= new Texture(Gdx.files.internal(name+".png"));
		alpha 			= new Texture(Gdx.files.internal(Constants.ALPHA_BG));

		TextureAtlas textureAtlas 	= new TextureAtlas(Gdx.files.internal(Constants.PAUSE_SCREEN_ATLAS));
		pauseBG 		= new TextureRegion();
		pauseBG 		= textureAtlas.findRegion("pauseBG");
		batch 			= new SpriteBatch();
		backToMainMenu 	= new ScreenSwitchTask(ScreenEnums.MAIN_MENU);
		
		//fonts
		font			= ResourceManager.instance().getFontSmall();
        layout          = new GlyphLayout();

        float[] points 	= ScreenManager.instance().getScoreArray();
        layout.setText(font, (""+getMeterFormat(points[Constants.SCORING_DISTANCE] / 100)));

        float textWidth = layout.width;
		valueX			= (Constants.TEXT_BOX_X + Constants.TEXT_BOX_WIDTH - 45) - textWidth;
        float lineSpace	= 20.0f;
		fieldX			= Constants.TEXT_BOX_X + 55;
		
		lineHeight 		= font.getCapHeight() + lineSpace;
		firstLineY		= (Constants.TEXT_BOX_Y + Constants.TEXT_BOX_HEIGHT) - 70;

		pauseBG_X = (VIEWPORT_WIDTH / 2) - (pauseBG.getRegionWidth() / 2);
		pauseBG_Y = (VIEWPORT_HEIGHT / 2) - (pauseBG.getRegionHeight() / 2);
		
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
		float[] points = ScreenManager.instance().getScoreArray();
		
		batch.begin();
		batch.draw(bg, 0, 0);
		batch.draw(alpha, 0, 0);
		batch.draw(pauseBG, pauseBG_X, pauseBG_Y);
		
		for(int i = 0; i < points.length; i++)
		{
            layout.setText(font, Constants.SCORING_NAMES[i]);
            font.setColor(Constants.PALETTE_GREEN);
			font.draw(batch, layout, fieldX, firstLineY - (i * lineHeight));
			
			font.setColor(Constants.PALETTE_RED);
			if(i == Constants.SCORING_DISTANCE)
            {
                layout.setText(font, ": "+getMeterFormat(points[i] / 100));
                font.draw(batch, layout, valueX, firstLineY - (i * lineHeight));
            }
			else
            {
                layout.setText(font, ": "+(int)Math.floor(points[i]));
                font.draw(batch, layout, valueX, firstLineY - (i * lineHeight));
            }
			font.setColor(Constants.PALETTE_GREY);
		}
		
		batch.end();
		super.draw();
	}
	
	private void setupButtons()
	{
		// StyleButton
		Rectangle bounds = new Rectangle(Constants.RESULT_YIELD_X, Constants.RESULT_YIELD_Y, Constants.STYLE_BUTTON_WH, Constants.STYLE_BUTTON_WH);
        StyleButton yieldButton = new StyleButton(bounds, new GameYieldButtonListener());
		addActor(yieldButton);
		
		bounds = new Rectangle(Constants.RESULT_RETRY_X, Constants.RESULT_RETRY_Y, Constants.STYLE_BUTTON_WH, Constants.STYLE_BUTTON_WH);
        StyleButton retryButton = new StyleButton(bounds, new GameRetryButtonListener());
		addActor(retryButton);
	}
	
	private String getMeterFormat(float distance)
	{
		String format;
		if((distance / 1000) > 1)
		{
			float dist = Math.round(distance);
			format = (dist / 1000)+" km";
		}
		else
		{
			format = distance+" m";
		}
		
		return format;
	}
	
	private class GameRetryButtonListener implements ButtonListener
	{
		@Override
		public void OnRelease() 
		{
			ScreenManager.instance().show(ScreenEnums.GAMEPLAY);
		}

		@Override
		public int getId()
		{
			return Constants.BUTTON_RESUME;
		}
		
	}
	
	private class GameYieldButtonListener implements ButtonListener
	{
		@Override
		public void OnRelease() 
		{
			Timer.schedule(backToMainMenu, 0.5f);
		}

		@Override
		public int getId()
		{
			return Constants.BUTTON_YIELD;
		}
	}
}
