package com.afriandi.project.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.afriandi.project.buttons.ButtonHandler;
import com.afriandi.project.buttons.StyleButton;
import com.afriandi.project.buttons.TextButton;
import com.afriandi.project.buttons.GameButtons.ButtonListener;
import com.afriandi.project.enums.ScreenEnums;
import com.afriandi.project.screens.ScreenManager;
import com.afriandi.project.screens.ScreenSwitchHandler;
import com.afriandi.project.utils.AudioUtils;
import com.afriandi.project.utils.Constants;
import com.afriandi.project.utils.DebugUtils;
import com.afriandi.project.utils.ResourceManager;

public class MainMenuStage extends Stage
{
	// This will be our viewport measurements while working with the debug renderer
    private static final int VIEWPORT_WIDTH = Constants.SCREEN_WIDTH;
    private static final int VIEWPORT_HEIGHT = Constants.SCREEN_HEIGHT;
    private float stateTime;
	private int lineHeight = 0;
    
    //Buttons
	private StyleButton exitButton;
	private StyleButton musicButton;
	private StyleButton sfxButton;
	
	private TextButton playButton;
	private TextButton tutorialButton;
	private TextButton creditsButton;
	
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Texture bg;
	private Animation<TextureRegion> mm_hero;
	private BitmapFont font = ResourceManager.instance().getFontMid();
	
	private Music mainMenuMusic;
	
	private DebugUtils debug;
	
	public MainMenuStage()
	{
		super(new ScalingViewport(Scaling.stretch,
				VIEWPORT_WIDTH,
                VIEWPORT_HEIGHT,
                new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT)));
		
		bg = new Texture(Gdx.files.internal(Constants.SPLASH_BG));
		TextureAtlas textureAtlas = new TextureAtlas(Constants.MM_HERO_ATLAS);
		TextureRegion[] heroFrames = new TextureRegion[Constants.MM_HERO_REGION_NAMES.length];
        for (int i = 0; i < Constants.MM_HERO_REGION_NAMES.length; i++)
        {
            String path = Constants.MM_HERO_REGION_NAMES[i];
            heroFrames[i] = textureAtlas.findRegion(path);
        }
        mm_hero = new Animation(0.1f, heroFrames);
		
		batch = new SpriteBatch();
		lineHeight = Math.round(3.0f * font.getCapHeight());
		camera = ScreenManager.instance().getCamera();
		
		setupButtons();
		Gdx.input.setInputProcessor(this);
		debug = new DebugUtils(camera);
		
		mainMenuMusic = AudioUtils.getInstance().getMM_Music();
		AudioUtils.getInstance().playMusic(mainMenuMusic);
	}
	
	@Override
	public void act(float delta)
	{
		super.act(delta);
		stateTime += Gdx.graphics.getDeltaTime();
	}
	
	@Override
    public void draw()
	{
		batch.begin();
		batch.draw(bg, 0, 0);
		batch.end();
		
		super.draw();
		
		batch.begin();
		playButton.draw(batch, camera);
		tutorialButton.draw(batch, camera);
		creditsButton.draw(batch, camera);
		
		batch.draw(mm_hero.getKeyFrame(stateTime, true).getTexture(), 
				250, 
				70, 
    			93, 	//Origin X
    			182, 	//Origin Y
    			mm_hero.getKeyFrame(stateTime, true).getRegionWidth(),
    			mm_hero.getKeyFrame(stateTime, true).getRegionHeight(),
    			1f,	1f, 						//scale
    			0, 								//rotation
    			mm_hero.getKeyFrame(stateTime, true).getRegionX(), //textureRegion x
    			mm_hero.getKeyFrame(stateTime, true).getRegionY(), 
    			mm_hero.getKeyFrame(stateTime, true).getRegionWidth(), 
    			mm_hero.getKeyFrame(stateTime, true).getRegionHeight(),  
    			false, //flip x
    			false);
		batch.end();
		
		debug.debugRectangle(exitButton.getRect(), Constants.BLUE_COLOR);
		debug.debugRectangle(musicButton.getRect(), Constants.BLUE_COLOR);
		debug.debugRectangle(sfxButton.getRect(), Constants.BLUE_COLOR);
	}
	
	private void setupButtons()
	{
		// StyleButton
		float posX = Constants.BUTTON_EXIT_X - Constants.STYLE_BUTTON_WH;
		float posY = Constants.BUTTON_EXIT_Y - Constants.STYLE_BUTTON_WH;
		Rectangle bounds = new Rectangle(posX, posY, Constants.STYLE_BUTTON_WH, Constants.STYLE_BUTTON_WH);
		exitButton = new StyleButton(bounds, new ButtonHandler(Constants.STATE_MAIN_MENU, Constants.BUTTON_EXIT));
		addActor(exitButton);
		
		bounds = new Rectangle(Constants.BUTTON_MUSIC_X, Constants.BUTTON_MUSIC_Y, Constants.STYLE_BUTTON_WH, Constants.STYLE_BUTTON_WH);
		musicButton = new StyleButton(bounds, new MusicButtonListener());
		addActor(musicButton);
		
		bounds = new Rectangle(Constants.BUTTON_SFX_X, Constants.BUTTON_SFX_Y, Constants.STYLE_BUTTON_WH, Constants.STYLE_BUTTON_WH);
		sfxButton = new StyleButton(bounds, new SFXButtonListener());
		addActor(sfxButton);

		// TextButton
		playButton = new TextButton(Constants.TEXT_PLAY, font, new ScreenSwitchHandler(ScreenEnums.GAMEPLAY, false));
		tutorialButton = new TextButton(Constants.TEXT_TUTO, font, new ScreenSwitchHandler(ScreenEnums.GAMEPLAY, true));
		creditsButton = new TextButton(Constants.TEXT_ABOUT, font, new ScreenSwitchHandler(ScreenEnums.CREDITS));
		
		batch.setProjectionMatrix(camera.combined);
		
		int centerX = Constants.TEXT_BUTTON_X;
		int centerY = Constants.TEXT_BUTTON_Y;
		
		playButton.setX(centerX - playButton.getWidth());
		playButton.setY(centerY + lineHeight);
		tutorialButton.setX(centerX - tutorialButton.getWidth());
		tutorialButton.setY(centerY);
		creditsButton.setX(centerX - creditsButton.getWidth());
		creditsButton.setY(centerY - lineHeight);
	}
	
	private class MusicButtonListener implements ButtonListener
	{
		@Override
		public void OnRelease() 
		{
			if(mainMenuMusic.isPlaying())
			{
				mainMenuMusic.stop();
			}

			AudioUtils.getInstance().toggleMusic();
            AudioUtils.getInstance().playMusic(mainMenuMusic);
		}

		@Override
		public int getId()
		{
			return AudioUtils.getInstance().getMusicRegionId();
		}
	}
	
	private class SFXButtonListener implements ButtonListener
	{
		@Override
		public void OnRelease() 
		{
			AudioUtils.getInstance().toggleSound();
			AudioUtils.getInstance().playSound(AudioUtils.getInstance().getJumpSound());
		}

		@Override
		public int getId()
		{
			return AudioUtils.getInstance().getSoundRegionId();
		}
	}
}
