package com.afriandi.project.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public interface Constants
{
	//App properties
	public static final int SCREEN_WIDTH 	= 800;
	public static final int SCREEN_HEIGHT 	= 480;
	
	// States
	public final int STATE_SPLASH 			= 0;
	public final int STATE_MAIN_MENU 		= STATE_SPLASH + 1;
	public final int STATE_GAMEPLAY 		= STATE_MAIN_MENU + 1;
	public final int STATE_PAUSED 			= STATE_GAMEPLAY + 1;
	public final int STATE_OVER				= STATE_PAUSED + 1;
	public final int STATE_CREDIT 			= STATE_OVER + 1;
	public final int STATE_MAX 				= STATE_CREDIT + 1;
	
	//Score array
	public final int SCORING_POINT			= 0;
	public final int SCORING_ENEMIES		= SCORING_POINT + 1;
	public final int SCORING_JUMP			= SCORING_ENEMIES + 1;
	public final int SCORING_DISTANCE		= SCORING_JUMP + 1;
	public final int SCORING_TOTAL			= SCORING_DISTANCE + 1;
	
	public final String[] SCORING_NAMES		= new String[]
	{
			"POINT",
			"ENEMY",
			"JUMP",
			"DISTANCE",
	};
		
	
	//Debugging
	public static final Color BLUE_COLOR 		= Color.BLUE;
	public static final Color YELLOW_COLOR 		= Color.YELLOW;
	public static final Color CYAN_COLOR 		= Color.CYAN;
	public static final Color MAGENTA_COLOR 	= Color.MAGENTA;
	public static final Color GREEN_COLOR 		= Color.GREEN;
	public static final Color RED_COLOR 		= Color.RED;
	public static final Color ORANGE_COLOR 		= Color.ORANGE;
	
	// ========================================================
	// About Screen
	// ========================================================
	public static final String[] POW 		= {"Powered by : ", "libGDX"};
	public static final String EMAIL	= "afriandi.haryanto@live.com";
	
	public static final String[] CREDIT_LIST = 
	{
		"Powered by : ", "libGDX",
		"Send your Question to", "afriandi.haryanto@live.com",
		"DEV : ", "@afriandi",
		"ART : ", "@madLukman.devianArt",
		"GD  : ", "@afriandi",
	};
	
    //==============================
	//Gameplay environment
	//==============================
	//World
    public static final Vector2 WORLD_GRAVITY 		= new Vector2(0.0f, -10.0f);
    public static final float GRAVITY_SCALE 		= 3.0f;
    public static final float WORLD_TO_SCREEN 		= 32; //tile size
    public static final int WORLD_SPEED 			= 150;
    
    //Ground
    public static final String GROUND_IMAGE_PATH 	= "GROUND/ground"; 
    public static final float GROUND_X 				= 0;
    public static final float GROUND_Y 				= 0;
    public static final float GROUND_WIDTH 			= 50f;
    public static final float GROUND_HEIGHT 		= 4f;
    public static final float GROUND_DENSITY 		= 0f;
    
    //Hero
    public static final float HERO_DENSITY 							= 0.5f;
    public static final float HERO_X 								= 5f;
    public static final float HERO_Y 								= GROUND_Y + GROUND_HEIGHT;
    public static final float HERO_WIDTH 							= 2f;
    public static final float HERO_HEIGHT 							= 4f;
    public static final float HERO_DODGE_X 							= 5f;
    public static final float HERO_DODGE_Y 							= HERO_Y - 1f;
    public static final float HERO_HIT_ANGULAR_IMPULSE 				= 10f;
    public static final Vector2 HERO_JUMPING_LINEAR_IMPULSE 		= new Vector2(0, 65f);
    
    //Enemy
    public static final float ENEMY_X 								= 25f;
    public static final float ENEMY_DENSITY 						= HERO_DENSITY;
    public static final float RUNNING_SHORT_ENEMY_Y 				= 3f;
    public static final float RUNNING_LONG_ENEMY_Y 					= RUNNING_SHORT_ENEMY_Y;
    public static final float FLYING_ENEMY_Y 						= 5.8f; //ladybug
    public static final float FLYING_ENEMY_WIDE_Y 					= 6f; //bee
    public static final Vector2 ENEMY_LINEAR_VELOCITY 				= new Vector2(-10f, 0);
    
    //Background
    public static final float FOREGROUND_Y = WORLD_TO_SCREEN;
    
    //Fonts
    public final int ID_PALETTE_GREY								= 0;
    public final int ID_PALETTE_GREEN								= ID_PALETTE_GREY + 1;
    public final int ID_PALETTE_RED									= ID_PALETTE_GREEN + 1;
    public final int ID_PALETTE_TOTAL								= ID_PALETTE_RED + 1;
    
    public static final Color PALETTE_GREY							= new Color(0.21f, 0.22f, 0.21f, 1f);
    public static final Color PALETTE_GREEN							= new Color(0.0f, 0.3f, 0.0f, 1f);
    public static final Color PALETTE_RED							= new Color(0.3f, 0.0f, 0.0f, 1f);
    
    public static final String FONT_NAME_ROBOTO 					= "FONTS/Roboto.ttf";
    public static final String FONT_NAME_CHOMP 						= "FONTS/Chomp.ttf";
    public static final String FONT_NAME_ALPHA 						= "FONTS/Alphacrisp.ttf";
    public static final String FONT_NAME_TERRANCOND 				= "FONTS/Terrancond.ttf";
    
    //PNGs
    public static final String RESULT_SCREEN_BG						= "MENUS/RESULT";
    public static final String ALPHA_BG								= "MENUS/BG.png";
    public static final String SPLASH_BG							= "MENUS/SPLASH.png";
    public static final String ABOUT_BG								= "MENUS/ABOUT.png";
    public static final String TUTORIAL_JUMP						= "MENUS/JUMP.png";
    public static final String TUTORIAL_DODGE						= "MENUS/DODGE.png";
    
    //==============================
    //SpriteSheets
    //==============================
    //Atlas
    public static final String[] MENU_ATLAS_LIST = new String[] 
    {
    	"MENUS/SPLASH.txt",
    	"MENUS/MAIN_MENU.txt",
    	"MENUS/BUTTONS.txt",
    };
    
    public static final String HERO_ATLAS_PATH 		= "HERO/HERO.txt";
    public static final String ENEMY_ATLAS_PATH 	= "ENEMIES/ENEMIES.txt";
    public static final String BUTTON_ATLAS_PATH 	= "MENUS/BUTTONS.txt";
    public static final String BG_ATLAS_PATH 		= "MENUS/BG.txt";
    public static final String BACKGROUND_ATLAS 	= "PARALLAX/level";
    public static final String MM_HERO_ATLAS 		= "MENUS/MM_HERO.txt";
    public static final String PAUSE_SCREEN_ATLAS	= "MENUS/PAUSE_SCREEN.txt";
    
    //Regions Menus
    public final String[] BUTTON_REGION_NAME		= new String[]
    {
	    "music_on",
	    "sfx_on",
	    "close",
		"yield",
		"play",
		"share",
		"pause",
		"level",
		"score",
		"music_off",
		"sfx_off",
    };
    
    public static final String[] MM_HERO_REGION_NAMES 		= new String[] 
    {
    	"hero1",
    	"hero2",
    	"hero3",
    	"hero4",
    	"hero5",
    	"hero6",
    	"hero7",
    	"hero8",
    	"hero9",
    	"hero10",
    };
    
    // =============================
    // Actors Regions
    // =============================
    
    // Hero
    public static final String HERO_DODGING_REGION_NAME 			= "duck";
    public static final String HERO_HIT_REGION_NAME 				= "jump";
    public static final String HERO_JUMPING_REGION_NAME 			= "run5";
    public static final String[] HERO_RUNNING_REGION_NAMES 			= new String[] 
    {
    	"run1",
    	"run2",
    	"run3",
    	"run4",
    	"run5",
    	"run6",
    	"run7",
    	"run8",
    };
    
    // Big Enemies
    public static final String[] BOAR_REGION_NAMES 	= new String[] 
	{
		"boar1",
		"boar2",
		"boar3",
		"boar4",
		"boar5",
	};
    
    public static final String[] RAT_REGION_NAMES 	= new String[] 
	{
		"rat1",
		"rat2",
		"rat3",
		"rat4",
		"rat5",
		"rat6",
	};
    
    public static final String[] LIZARD_REGION_NAMES 	= new String[] 
	{
		"lizard1",
		"lizard2",
		"lizard3",
		"lizard4",
	};
    
    //Wide Enemies
    public static final String[] SLUG_REGION_NAMES 	= new String[] 
    {
    	"slug1",
    	"slug2",
    	"slug3",
    	"slug4",
    };
    
    public static final String[] SNAKE_REGION_NAMES 	= new String[] 
    {
    	"uler1",
    	"uler2",
    	"uler3",
    	"uler4",
    };
    
    // Flying Small Enemies
    public static final String[] LADYBUG_REGION_NAMES 	= new String[]
    {
    	"ladybug1",
    	"ladybug2",
    	"ladybug3",
    };
    
    //Flying Big Enemies
    public static final String[] FOREST_BEE_REGION_NAMES 	= new String[]
    {
    	"bee1",
    	"bee2",
    	"bee3",
    	"bee4",
    };
    
    public static final String[] VULTURE_REGION_NAMES 	= new String[]
    {
    	"vulture1",
    	"vulture2",
    };
    
    public static final String[] UNDERGROUND_BEE_REGION_NAMES 	= new String[]
    {
    	"glowBee1",
    	"glowBee2",
    	"glowBee3",
    	"glowBee4",
    };
    
    
    //==============================
    //Button Properties
    //==============================
  	//Button Ids
  	public final int BUTTON_MUSIC 				= 0;
  	public final int BUTTON_SFX 				= 1;
  	public final int BUTTON_EXIT 				= 2;
  	public final int BUTTON_YIELD				= 3;
  	public final int BUTTON_RESUME				= 4;
  	public final int BUTTON_BACK 				= 5;
  	public final int BUTTON_PAUSE 				= 6;
  	public final int LABEL_LEVEL 				= 7;
  	public final int LABEL_SCORE 				= 8;
  	public final int BUTTON_MUSIC_OFF			= 9;
  	public final int BUTTON_SFX_OFF				= 10;
  	
    //main menu
  	public static final String TEXT_PLAY		= "PLAY";
    public static final String TEXT_TUTO		= "TUTORIAL";
    public static final String TEXT_ABOUT		= "ABOUT";
    
  	public static final int TEXT_BUTTON_X 		= SCREEN_WIDTH - 30;
  	public static final int TEXT_BUTTON_Y 		= SCREEN_HEIGHT - 150;
  	
  	public static final int STYLE_BUTTON_WH		= 54;
  	
    public static final float BUTTON_EXIT_X 	= TEXT_BUTTON_X;
    public static final float BUTTON_EXIT_Y 	= 30 + STYLE_BUTTON_WH;
    
    public static final float BUTTON_MUSIC_X 	= 30;
    public static final float BUTTON_MUSIC_Y 	= 30;
    
    public static final float BUTTON_SFX_X 		= BUTTON_MUSIC_X + STYLE_BUTTON_WH + 10;
    public static final float BUTTON_SFX_Y 		= BUTTON_MUSIC_Y;
    
    //Pause menu
    public static final int TEXT_BOX_WIDTH 		= 395;
    public static final int TEXT_BOX_HEIGHT		= 406;
    public static final int TEXT_BOX_X 			= (SCREEN_WIDTH >> 1) - (TEXT_BOX_WIDTH >> 1);
    public static final int TEXT_BOX_Y 			= (SCREEN_HEIGHT >> 1) - (TEXT_BOX_HEIGHT >> 1);
    
    public static final float PAUSE_EXIT_X 		= (TEXT_BOX_X + TEXT_BOX_WIDTH) - (STYLE_BUTTON_WH + 30);
    public static final float PAUSE_EXIT_Y 		= TEXT_BOX_Y + 40;
    
    public static final float PAUSE_YIELD_X		= TEXT_BOX_X + 30;
    public static final float PAUSE_YIELD_Y		= TEXT_BOX_Y + 40;
    
    public static final float PAUSE_RESUME_X	= (SCREEN_WIDTH >> 1) - (STYLE_BUTTON_WH >> 1);
    public static final float PAUSE_RESUME_Y	= TEXT_BOX_Y + 20;
    
    public static final float PAUSE_MUSIC_X		= (TEXT_BOX_X + TEXT_BOX_WIDTH) + 10;
    public static final float PAUSE_MUSIC_Y		= (TEXT_BOX_Y + TEXT_BOX_HEIGHT) - STYLE_BUTTON_WH;
    
    public static final float PAUSE_SFX_X		= PAUSE_MUSIC_X + (STYLE_BUTTON_WH + 10);
    public static final float PAUSE_SFX_Y		= PAUSE_MUSIC_Y;
    
    //result screen
    public static final float RESULT_YIELD_X	= ((SCREEN_WIDTH >> 1) - (STYLE_BUTTON_WH)) - 40 ;
    public static final float RESULT_YIELD_Y	= TEXT_BOX_Y + 30;
    
    public static final float RESULT_RETRY_X	= (SCREEN_WIDTH >> 1) + 40;
    public static final float RESULT_RETRY_Y	= TEXT_BOX_Y + 30;
    
    //Sound
    public static final String GAMEPLAY_MUSIC	= "SOUND/LoopMusic.ogg";
    public static final String MAIN_MENU_MUSIC	= "SOUND/background.mp3";
    public static final String SFX_JUMP 		= "SOUND/jump.wav";
    public static final String SFX_HIT	 		= "SOUND/hit.wav";
    
    public static final String MUSIC_ON 		= "music_on";
    public static final String MUSIC_OFF 		= "music_off";
    public static final String SFX_ON 			= "sound_on";
    public static final String SFX_OFF 			= "sound_off";
}
