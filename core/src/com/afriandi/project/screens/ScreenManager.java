package com.afriandi.project.screens;

import java.util.Random;

import com.afriandi.project.TakeItGame;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.IntMap;
import com.afriandi.project.enums.Difficulty;
import com.afriandi.project.enums.ScreenEnums;
import com.afriandi.project.utils.Constants;

public class ScreenManager
{
	// This will be our viewport measurements while working with the debug renderer
    private static final int VIEWPORT_WIDTH 	= Constants.SCREEN_WIDTH;
    private static final int VIEWPORT_HEIGHT 	= Constants.SCREEN_HEIGHT;
    private static ScreenManager smInstance 	= new ScreenManager();
    private int s_gameState;
	private int level 							= 1;
	private float[] scoreArray;
	private boolean isTutorial;
	
	private TakeItGame game;
	private IntMap<Screen> screens;
	private OrthographicCamera s_gameCamera;
	private Difficulty difficulty;
	
	private ScreenManager()
	{
		screens = new IntMap<Screen>();
	}
	
	public static ScreenManager instance()
	{
		if(smInstance == null)
			smInstance = new ScreenManager();
		
		return smInstance;
	}
	
	public void initialize(TakeItGame game)
	{
		scoreArray = new float[Constants.SCORING_TOTAL];
		this.game = game;
		isTutorial = false;

		setupCamera();
		setState(Constants.STATE_SPLASH);
		show(ScreenEnums.SPLASH);
	}
	
	public void setState(int state)
	{
		this.s_gameState = state;
	}
	
	public int getState()
	{
		return s_gameState;
	}
	
	private void setupCamera()
    {
        s_gameCamera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        s_gameCamera.position.set(s_gameCamera.viewportWidth / 2, s_gameCamera.viewportHeight / 2, 0f);
        s_gameCamera.update();
    }
	
	public OrthographicCamera getCamera()
	{
		return s_gameCamera;
	}
	
	public void show(ScreenEnums screenEnum)
	{
		if(game == null) return;
		
		if (!screens.containsKey(screenEnum.ordinal())) 
		{
            screens.put(screenEnum.ordinal(), screenEnum.getScreenInstance());
        }
		
		setState(screenEnum.ordinal());
		game.setScreen(screens.get(screenEnum.ordinal()));
	}
	
	//remove unused Screen
	public void dispose(ScreenEnums screen)
	{
		if (!screens.containsKey(screen.ordinal()))
			return;
		
		screens.remove(screen.ordinal()).dispose();
	}
	
	//dispose ScreenManager
	public void dispose()
	{
		for (Screen screen : screens.values())
		{
            screen.dispose();
        }
		
        screens.clear();
        smInstance = null;
	}
	
	public void randomizeLevel()
	{
		//randomize background
		Random random = new Random();
		setLevel(random.nextInt(3) + 1);
	}
	
	//interchange
	public void setLevel(int level)
	{
		this.level = level;
	}
	
	public int getLevel()
	{
		return this.level;
	}
	
	// ==============================================
	// Tutorial
	// ==============================================
	public void setTutorial(boolean tutorial)
	{
		isTutorial = tutorial;
	}
	
	public boolean isTutorial()
	{
		return isTutorial;
	}
	
	
	// ==============================================
	// Scoring
	// ==============================================
	public void setScore(int index, float value)
	{
		this.scoreArray[index] = value;
	}
	
	public float getScore(int index)
	{
		return this.scoreArray[index];
	}
	
	public float[] getScoreArray()
	{
		return this.scoreArray;
	}
	
	// =====================================================
	// Difficulty
	// =====================================================
	public Difficulty getDifficulty()
	{
		return difficulty;
	}
	
	public void setDifficulty(Difficulty difficulty)
	{
		this.difficulty = difficulty;
	}
	
	public boolean isMaxDifficulty()
	{
		return difficulty == Difficulty.values()[Difficulty.values().length - 1]; //max array from enums
	}
	
	public void resetDifficulty()
	{
		setDifficulty(Difficulty.values()[0]); //first value on array
	}
	
}


