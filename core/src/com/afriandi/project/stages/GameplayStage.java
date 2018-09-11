package com.afriandi.project.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
//import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.afriandi.project.actors.Background;
import com.afriandi.project.actors.Enemy;
import com.afriandi.project.actors.Ground;
import com.afriandi.project.actors.Hero;
import com.afriandi.project.actors.Score;
import com.afriandi.project.buttons.ButtonHandler;
import com.afriandi.project.buttons.GameButtons.ButtonListener;
import com.afriandi.project.buttons.StyleButton;
import com.afriandi.project.enums.Difficulty;
import com.afriandi.project.enums.ScreenEnums;
import com.afriandi.project.screens.ScreenManager;
import com.afriandi.project.screens.ScreenSwitchTask;
import com.afriandi.project.utils.AudioUtils;
import com.afriandi.project.utils.BodyUtils;
import com.afriandi.project.utils.Constants;
import com.afriandi.project.utils.DebugUtils;
import com.afriandi.project.utils.ResourceManager;
import com.afriandi.project.utils.WorldUtils;

public class GameplayStage extends Stage implements ContactListener
{
    private static final int VIEWPORT_WIDTH 	= Constants.SCREEN_WIDTH;
    private static final int VIEWPORT_HEIGHT 	= Constants.SCREEN_HEIGHT;
    private final float TIME_STEP 				= 1 / 300f;
    private float accumulator 					= 0f;
    private float totalTimePassed;
    private int totalEnemyBeaten;
    
    private boolean enemyIsFlying;
    private boolean startDrawTuto;
    private boolean tutorialFlag;
    
    private World world;
    private Ground ground;
    private Hero hero;
    private Group pauseMenu_Group;
    private Score score, level;
    
    private OrthographicCamera camera;
    private Rectangle jumpTouchArea, duckTouchArea;
    private Vector3 touchPoint;
    
    //Buttons
  	private StyleButton exitButton;
  	private StyleButton yieldButton;
  	private StyleButton resumeButton;
  	private StyleButton musicButton;
  	private StyleButton sfxButton;
  	private StyleButton pauseButton;
  	private StyleButton levelLabel;
  	private StyleButton scoreLabel;
  	
  	// BG
  	private TextureRegion pauseArt, pauseBG;
  	private Texture alpha;
  	private TextureAtlas textureAtlas;
  	private SpriteBatch batch;
  	
  	//Task
  	private ScreenSwitchTask backToMainMenu;
  	private ScreenSwitchTask resultScreen;
  	
  	//music
  	private Music gameplayMusic;
  	
  	//Debugging
    //private Box2DDebugRenderer renderer;
    private DebugUtils debug;
    
    public GameplayStage()
    {
    	super(new ScalingViewport(Scaling.stretch, VIEWPORT_WIDTH, VIEWPORT_HEIGHT, new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT)));
    	Gdx.input.setInputProcessor(this);
    	
    	totalEnemyBeaten = 0;
    	camera = ScreenManager.instance().getCamera();
    	ScreenManager.instance().resetDifficulty();
    	resultScreen = new ScreenSwitchTask(ScreenEnums.RESULT);
    	
    	setUpWorld();
    	setUpCharacters();
    	setUpTouch();
    	setUpPauseScreen();
    	setUpPauseMenu_Buttons();
    	
    	//debugging
    	debug = new DebugUtils(camera);
        //renderer = new Box2DDebugRenderer();
    }
    
    private void setUpWorld()
    {
    	ScreenManager.instance().randomizeLevel();
    	world = WorldUtils.createWorld();
    	world.setContactListener(this);
    	
    	setUpBackground();
    	setUpGround();
    	setUpButton();
    	
    	gameplayMusic = AudioUtils.getInstance().getAP_Music();
    	AudioUtils.getInstance().playMusic(gameplayMusic);
    }
    
    private void setUpBackground()
    {
    	addActor(new Background());
    }
    
    private void setUpGround()
    {
    	ground = new Ground(WorldUtils.createGround(world));
    	addActor(ground);
    }
    
    private void setUpCharacters()
    {
    	createHero();
    	createEnemy();
    }
    
    private void createHero()
    {
    	if (hero == null) 
    	{
    		hero = new Hero(WorldUtils.createHero(world));
        	addActor(hero);
        }
    }
    
    private void createEnemy()
    {
        Enemy enemy = new Enemy(WorldUtils.createEnemy(world));
        enemy.getUserData().setLinearVelocity(ScreenManager.instance().getDifficulty().getEnemyLinearVelocity());
        
        if(ScreenManager.instance().isTutorial())
        {
        	enemyIsFlying = enemy.getRect().y > Constants.RUNNING_SHORT_ENEMY_Y;
        	tutorialFlag = true;
        }
        
        addActor(enemy);
    }
    
    private void setUpButton()
    {
    	//pause button
    	float posX = 5;
		float posY = VIEWPORT_HEIGHT - 5 - Constants.STYLE_BUTTON_WH;
    	Rectangle bounds = new Rectangle(posX, posY, Constants.STYLE_BUTTON_WH, Constants.STYLE_BUTTON_WH);
    	pauseButton = new StyleButton(bounds, new GamePauseButtonListener());
    	
    	posX = (VIEWPORT_WIDTH >> 1) - 125;
    	bounds = new Rectangle(posX, posY, 250, 50);
    	scoreLabel = new StyleButton(bounds, new GameScoreLabelListener());
    	
    	posX = (VIEWPORT_WIDTH >> 1) - 75;
    	bounds = new Rectangle(posX, posY - 55, 150, 50);
    	levelLabel = new StyleButton(bounds, new GameLevelLabelListener());
    	
    	addActor(pauseButton);
    	addActor(levelLabel);
    	addActor(scoreLabel);
    	setUpScore();
    }
    
    private void setUpTouch()
    {
        touchPoint = new Vector3();
        jumpTouchArea = new Rectangle(0, getCamera().viewportHeight / 2, getCamera().viewportWidth, getCamera().viewportHeight / 2);
        duckTouchArea = new Rectangle(0, 0, getCamera().viewportWidth, getCamera().viewportHeight / 2);
    }
    
    private void setUpScore()
    {
    	float x = (VIEWPORT_WIDTH >> 1) - 90;
    	float y = VIEWPORT_HEIGHT - 20;
    	
    	Rectangle scoreRect = new Rectangle(x, y, 200, ResourceManager.instance().getFontMid().getCapHeight());
    	score = new Score(scoreRect, true);
    	
    	x = (VIEWPORT_WIDTH >> 1) - 20;
    	y = VIEWPORT_HEIGHT - 75;
    	scoreRect = new Rectangle(x, y, 50, ResourceManager.instance().getFontMid().getCapHeight());
    	level = new Score(scoreRect, false);
    	
    	addActor(score);
    	addActor(level);
    }
    
    private float counter = 0;
    private void setGameOver(float timer)
    {
    	counter+=timer;
    	if(counter >= 1.5f)
    	{
    		ScreenManager.instance().setScore(Constants.SCORING_POINT, score.getScore());
    		ScreenManager.instance().setState(Constants.STATE_OVER);
        	ScreenManager.instance().resetDifficulty();
        	totalTimePassed = 0;
        	
        	Timer.schedule(resultScreen, 0.02f);
    	}
    }
    
    private void update(Body body)
    {
        if (!BodyUtils.bodyInBounds(body))
        {
            if (BodyUtils.isEnemy(body) && !hero.isHit())
            {
                createEnemy();
                totalEnemyBeaten++;
            }
            
            if(!BodyUtils.isEnemy(body))
            {
            	setGameOver(0.5f);
            }
            world.destroyBody(body);
        }
        else if(BodyUtils.isEnemy(body) && ScreenManager.instance().isTutorial())
        {
        	startDrawTuto = (body.getPosition().x < 15.0f) & tutorialFlag ;
        	//System.out.println(" "+startDrawTuto);
        }
    }
    
    private void translateScreenToWorldCoordinates(int x, int y)
    {
        getCamera().unproject(touchPoint.set(x, y, 0));
    }
    
    private boolean jumpAreaTouched(float x, float y)
    {
        return jumpTouchArea.contains(x, y);
    }
    
    private boolean duckAreaTouched(float x, float y)
    {
        return duckTouchArea.contains(x, y);
    }

    
    //================================================================
    // implementation from Stage
    //================================================================    
    @Override
    public void act(float delta)
    {
    	if(ScreenManager.instance().getState() == Constants.STATE_OVER) return;
    	
    	if(hero.isHit())
    	{
    		setGameOver(delta);
    	}
    	
    	if(ScreenManager.instance().getState() == Constants.STATE_PAUSED)
    	{
    		pauseMenu_Group.act(delta);
    	}
    	//else if(ScreenManager.instance().isTutorial() && startDrawTuto)
    	//{
    		//;
    	//}
    	else
    	{
    		super.act(delta);
    		
            if(ScreenManager.instance().getState() == Constants.STATE_GAMEPLAY)
            {
            	totalTimePassed +=delta;
            	updateDifficulty();
            }
            
            Array<Body> bodies = new Array<Body>(world.getBodyCount());
            world.getBodies(bodies);
            
            for (Body body : bodies)
            {
                update(body);
            }
            
            // Fixed timestep
            accumulator += delta;

            while (accumulator >= delta)
            {
                world.step(TIME_STEP, 6, 2);
                accumulator -= TIME_STEP;
            }
    	}
        //TODO: Implement interpolation
    }

    @Override
    public void draw()
    {
    	super.draw();
    	if(ScreenManager.instance().getState() == Constants.STATE_PAUSED)
    	{
    		drawPauseScreen();
    		getBatch().begin();
    		pauseMenu_Group.draw(getBatch(), 1);
    		getBatch().end();
    	}
    	
    	
        //debugging purpose
        //renderer.render(world, camera.combined);
        debug.debugRectangle(jumpTouchArea.getX(), jumpTouchArea.getY(), jumpTouchArea.getWidth(), jumpTouchArea.getHeight(), Constants.BLUE_COLOR);
        debug.debugRectangle(duckTouchArea.getX(), duckTouchArea.getY(), duckTouchArea.getWidth(), duckTouchArea.getHeight(), Constants.YELLOW_COLOR);
        
        debug.debugRectangle(hero.getRect(), Constants.GREEN_COLOR);
    }
    
    @Override
    public boolean touchDown(int x, int y, int pointer, int button)
    {
        // Need to get the actual coordinates
        translateScreenToWorldCoordinates(x, y);
        
        if(ScreenManager.instance().getState() != Constants.STATE_PAUSED)
        {
        	if (jumpAreaTouched(touchPoint.x, touchPoint.y))
            {
            	hero.jump();
            }
            else if(duckAreaTouched(touchPoint.x, touchPoint.y))
            {
            	hero.dodge();
            }
        	
        	if(ScreenManager.instance().isTutorial())
        	{
        		tutorialFlag = false;
        	}
        	
        }
        

        return super.touchDown(x, y, pointer, button);
    }
    
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
    	
        if (hero.isDodging() && ScreenManager.instance().getState() != Constants.STATE_PAUSED)
        {
            hero.stopDodge();
        }

        return super.touchUp(screenX, screenY, pointer, button);
    }

    
    //================================================================
    // implementation from ContactListener
    //================================================================
	@Override
	public void beginContact(Contact contact)
	{
		Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        if ((BodyUtils.bodyIsHero(a) && BodyUtils.bodyIsGround(b))
            || (BodyUtils.bodyIsGround(a) && BodyUtils.bodyIsHero(b)))
        {
            hero.landed();
        }
        else if((BodyUtils.bodyIsHero(a) && BodyUtils.isEnemy(b))
                || (BodyUtils.isEnemy(a) && BodyUtils.bodyIsHero(b)))
        {
        	hero.hit();
        	
        	//Scoring
        	ScreenManager.instance().setScore(Constants.SCORING_JUMP, hero.getJumpCount());
        	ScreenManager.instance().setScore(Constants.SCORING_DISTANCE, Background.getDistance());
        	ScreenManager.instance().setScore(Constants.SCORING_ENEMIES, totalEnemyBeaten);
        }
	}

	@Override
	public void endContact(Contact contact)
	{
		//
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold)
	{
		//
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse)
	{
		//
	}
	
	// 	==================================== SCORING =========================================
	//	Scoring
	//	======================================================================================
	public void updateDifficulty()
	{
		if(ScreenManager.instance().isMaxDifficulty() || hero.isHit()) return;
		Difficulty currentDifficulty = ScreenManager.instance().getDifficulty();
		
		if(totalTimePassed > ScreenManager.instance().getDifficulty().getLevel() * 5)
		{
			int nextDifficulty 		= currentDifficulty.getLevel() + 1;
			String nextDiff_Name	= "DIFFICULTY_"+nextDifficulty;
			ScreenManager.instance().setDifficulty(Difficulty.valueOf(nextDiff_Name));
			
			hero.onDifficultyChange(ScreenManager.instance().getDifficulty());
			score.setMultiplier(ScreenManager.instance().getDifficulty().getScoreMultiplier());
		}
	}
	
	// 	=========================== PAUSE SCREEN =============================
	//	Pause Screen
	//	======================================================================
	private void setUpPauseScreen()
	{
		batch 			= new SpriteBatch();
		String name 	= Constants.RESULT_SCREEN_BG + ScreenManager.instance().getLevel();
		alpha 			= new Texture(Gdx.files.internal(Constants.ALPHA_BG));
		
		name 			= "envi" + ScreenManager.instance().getLevel();
		textureAtlas 	= new TextureAtlas(Gdx.files.internal(Constants.PAUSE_SCREEN_ATLAS));
		pauseBG 		= new TextureRegion();
		pauseBG 		= textureAtlas.findRegion("pauseBG");
		pauseArt 		= new TextureRegion();
		pauseArt 		= textureAtlas.findRegion(name);
		
		backToMainMenu 	= new ScreenSwitchTask(ScreenEnums.MAIN_MENU);
	}
	
	private void drawPauseScreen()
	{
		batch.begin();
		batch.draw(alpha, 0, 0);
		
		int pauseBG_X = (VIEWPORT_WIDTH / 2) - (pauseBG.getRegionWidth() / 2);
		int pauseBG_Y = (VIEWPORT_HEIGHT / 2) - (pauseBG.getRegionHeight() / 2);
		batch.draw(pauseBG, pauseBG_X, pauseBG_Y);
		batch.draw(pauseArt, pauseBG_X + 29, pauseBG_Y + 117);
		
		batch.end();
	}
	
	private void setUpPauseMenu_Buttons()
	{
		pauseMenu_Group = new Group();
    	
		// StyleButton
		Rectangle bounds = new Rectangle(Constants.PAUSE_EXIT_X, Constants.PAUSE_EXIT_Y, Constants.STYLE_BUTTON_WH, Constants.STYLE_BUTTON_WH);
		exitButton = new StyleButton(bounds, new ButtonHandler(Constants.STATE_PAUSED, Constants.BUTTON_EXIT));
		pauseMenu_Group.addActor(exitButton);
		
		bounds = new Rectangle(Constants.PAUSE_YIELD_X, Constants.PAUSE_YIELD_Y, Constants.STYLE_BUTTON_WH, Constants.STYLE_BUTTON_WH);
		yieldButton = new StyleButton(bounds, new GameYieldButtonListener());
		pauseMenu_Group.addActor(yieldButton);
		
		bounds = new Rectangle(Constants.PAUSE_RESUME_X, Constants.PAUSE_RESUME_Y, Constants.STYLE_BUTTON_WH, Constants.STYLE_BUTTON_WH);
		resumeButton = new StyleButton(bounds, new GameResumeButtonListener());
		pauseMenu_Group.addActor(resumeButton);
		
		bounds = new Rectangle(Constants.PAUSE_MUSIC_X, Constants.PAUSE_MUSIC_Y, Constants.STYLE_BUTTON_WH, Constants.STYLE_BUTTON_WH);
		musicButton = new StyleButton(bounds, new MusicButtonListener());
		pauseMenu_Group.addActor(musicButton);
		
		bounds = new Rectangle(Constants.PAUSE_SFX_X, Constants.PAUSE_SFX_Y, Constants.STYLE_BUTTON_WH, Constants.STYLE_BUTTON_WH);
		sfxButton = new StyleButton(bounds, new SFXButtonListener());
		pauseMenu_Group.addActor(sfxButton);
		
	}
	
	//	===================== Button Listeners =======================
	//	Button Listeners
	//	==============================================================
	private class GamePauseButtonListener implements ButtonListener
	{
		@Override
		public void OnRelease() 
		{
			ScreenManager.instance().setState(Constants.STATE_PAUSED);
			addActor(pauseMenu_Group);
		}

		@Override
		public int getId()
		{
			return Constants.BUTTON_PAUSE;
		}
		
	}
	
	private class GameScoreLabelListener implements ButtonListener
	{

		@Override
		public void OnRelease() 
		{
			
		}

		@Override
		public int getId()
		{
			return Constants.LABEL_SCORE;
		}
		
	}
	
	private class GameLevelLabelListener implements ButtonListener
	{

		@Override
		public void OnRelease() 
		{
			
		}

		@Override
		public int getId()
		{
			return Constants.LABEL_LEVEL;
		}
		
	}
	
	private class GameResumeButtonListener implements ButtonListener
	{
		@Override
		public void OnRelease() 
		{
			pauseMenu_Group.remove();
			ScreenManager.instance().setState(Constants.STATE_GAMEPLAY);
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
	
	private class MusicButtonListener implements ButtonListener
	{
		@Override
		public void OnRelease() 
		{
			if(gameplayMusic.isPlaying())
			{
				gameplayMusic.pause();
			}
			
			AudioUtils.getInstance().toggleMusic();
			AudioUtils.getInstance().playMusic(gameplayMusic);
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
