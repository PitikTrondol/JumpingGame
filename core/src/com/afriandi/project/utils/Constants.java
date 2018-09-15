package com.afriandi.project.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public interface Constants
{
	//App properties
	int SCREEN_WIDTH 	= 800;
	int SCREEN_HEIGHT 	= 480;
	
	// States
	int STATE_SPLASH 			= 0;
	int STATE_MAIN_MENU 		= STATE_SPLASH + 1;
	int STATE_GAMEPLAY 		= STATE_MAIN_MENU + 1;
	int STATE_PAUSED 			= STATE_GAMEPLAY + 1;
	int STATE_OVER				= STATE_PAUSED + 1;
	int STATE_CREDIT 			= STATE_OVER + 1;
	int STATE_MAX 				= STATE_CREDIT + 1;
	
	//Score array
	int SCORING_POINT			= 0;
	int SCORING_ENEMIES		= SCORING_POINT + 1;
	int SCORING_JUMP			= SCORING_ENEMIES + 1;
	int SCORING_DISTANCE		= SCORING_JUMP + 1;
	int SCORING_TOTAL			= SCORING_DISTANCE + 1;
	
	String[] SCORING_NAMES		= new String[]
	{
			"POINT",
			"ENEMY",
			"JUMP",
			"DISTANCE",
	};
		
	
	//Debugging
	Color BLUE_COLOR 		= Color.BLUE;
	Color YELLOW_COLOR 		= Color.YELLOW;
	Color CYAN_COLOR 		= Color.CYAN;
	Color MAGENTA_COLOR 	= Color.MAGENTA;
	Color GREEN_COLOR 		= Color.GREEN;
	Color RED_COLOR 		= Color.RED;
	Color ORANGE_COLOR 		= Color.ORANGE;
	
	// ========================================================
	// About Screen
	// ========================================================

	String[] CREDIT_LIST = 
	{
		"Powered by : ", "libGDX",
		"Send your Question to", "afriandi.haryanto@live.com",
		"DEV : ", "Afriandi (https://github.com/PitikTrondol/)",
		"ART : ", "Ahmad Lukman (@madLukman.devianArt)",
	};
	
    //==============================
	//Gameplay environment
	//==============================
	//World
    Vector2 WORLD_GRAVITY 		= new Vector2(0.0f, -10.0f);
    float GRAVITY_SCALE 		= 3.0f;
    float WORLD_TO_SCREEN 		= 32; //tile size
    int WORLD_SPEED 			= 150;
    
    //Ground
    String GROUND_IMAGE_PATH 	= "GROUND/ground"; 
    float GROUND_X 				= 0;
    float GROUND_Y 				= 0;
    float GROUND_WIDTH 			= 50f;
    float GROUND_HEIGHT 		= 4f;
    float GROUND_DENSITY 		= 0f;
    
    //Hero
    float HERO_DENSITY 							= 0.5f;
    float HERO_X 								= 5f;
    float HERO_Y 								= GROUND_Y + GROUND_HEIGHT;
    float HERO_WIDTH 							= 2f;
    float HERO_HEIGHT 							= 4f;
    float HERO_DODGE_X 							= 5f;
    float HERO_DODGE_Y 							= HERO_Y - 1f;
    float HERO_HIT_ANGULAR_IMPULSE 				= 10f;
    Vector2 HERO_JUMPING_LINEAR_IMPULSE 		= new Vector2(0, 65f);
    
    //Enemy
    float ENEMY_X 								= 25f;
    float ENEMY_DENSITY 						= HERO_DENSITY;
    float RUNNING_SHORT_ENEMY_Y 				= 3f;
    float RUNNING_LONG_ENEMY_Y 					= RUNNING_SHORT_ENEMY_Y;
    float FLYING_ENEMY_Y 						= 5.8f; //ladybug
    float FLYING_ENEMY_WIDE_Y 					= 6f; //bee
    Vector2 ENEMY_LINEAR_VELOCITY 				= new Vector2(-10f, 0);
    
    //Background
    float FOREGROUND_Y = WORLD_TO_SCREEN;
    
    //Fonts
    int ID_PALETTE_GREY								= 0;
    int ID_PALETTE_GREEN								= ID_PALETTE_GREY + 1;
    int ID_PALETTE_RED									= ID_PALETTE_GREEN + 1;
    int ID_PALETTE_TOTAL								= ID_PALETTE_RED + 1;
    
    Color PALETTE_GREY							= new Color(0.21f, 0.22f, 0.21f, 1f);
    Color PALETTE_GREEN							= new Color(0.0f, 0.3f, 0.0f, 1f);
    Color PALETTE_RED							= new Color(0.3f, 0.0f, 0.0f, 1f);
    
    String FONT_NAME_ROBOTO 					= "FONTS/Roboto.ttf";
    String FONT_NAME_CHOMP 						= "FONTS/Chomp.ttf";
    String FONT_NAME_ALPHA 						= "FONTS/Alphacrisp.ttf";
    String FONT_NAME_TERRANCOND 				= "FONTS/Terrancond.ttf";
    
    //PNGs
    String RESULT_SCREEN_BG						= "MENUS/RESULT";
    String ALPHA_BG								= "MENUS/BG.png";
    String SPLASH_BG							= "MENUS/SPLASH.png";
    String ABOUT_BG								= "MENUS/ABOUT.png";
    String TUTORIAL_JUMP						= "MENUS/JUMP.png";
    String TUTORIAL_DODGE						= "MENUS/DODGE.png";
    
    //==============================
    //SpriteSheets
    //==============================
    //Atlas
    String[] MENU_ATLAS_LIST = new String[] 
    {
    	"MENUS/SPLASH.txt",
    	"MENUS/MAIN_MENU.txt",
    	"MENUS/BUTTONS.txt",
    };
    
    String HERO_ATLAS_PATH 		= "HERO/HERO.txt";
    String ENEMY_ATLAS_PATH 	= "ENEMIES/ENEMIES.txt";
    String BUTTON_ATLAS_PATH 	= "MENUS/BUTTONS.txt";
    String BG_ATLAS_PATH 		= "MENUS/BG.txt";
    String BACKGROUND_ATLAS 	= "PARALLAX/level";
    String MM_HERO_ATLAS 		= "MENUS/MM_HERO.txt";
    String PAUSE_SCREEN_ATLAS	= "MENUS/PAUSE_SCREEN.txt";
    
    //Regions Menus
    String[] BUTTON_REGION_NAME		= new String[]
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
    
    String[] MM_HERO_REGION_NAMES 		= new String[] 
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
    String HERO_DODGING_REGION_NAME 			= "duck";
    String HERO_HIT_REGION_NAME 				= "jump";
    String HERO_JUMPING_REGION_NAME 			= "run5";
    String[] HERO_RUNNING_REGION_NAMES 			= new String[] 
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
    String[] BOAR_REGION_NAMES 	= new String[] 
	{
		"boar1",
		"boar2",
		"boar3",
		"boar4",
		"boar5",
	};
    
    String[] RAT_REGION_NAMES 	= new String[] 
	{
		"rat1",
		"rat2",
		"rat3",
		"rat4",
		"rat5",
		"rat6",
	};
    
    String[] LIZARD_REGION_NAMES 	= new String[] 
	{
		"lizard1",
		"lizard2",
		"lizard3",
		"lizard4",
	};
    
    //Wide Enemies
    String[] SLUG_REGION_NAMES 	= new String[] 
    {
    	"slug1",
    	"slug2",
    	"slug3",
    	"slug4",
    };
    
    String[] SNAKE_REGION_NAMES 	= new String[] 
    {
    	"uler1",
    	"uler2",
    	"uler3",
    	"uler4",
    };
    
    // Flying Small Enemies
    String[] LADYBUG_REGION_NAMES 	= new String[]
    {
    	"ladybug1",
    	"ladybug2",
    	"ladybug3",
    };
    
    //Flying Big Enemies
    String[] FOREST_BEE_REGION_NAMES 	= new String[]
    {
    	"bee1",
    	"bee2",
    	"bee3",
    	"bee4",
    };
    
    String[] VULTURE_REGION_NAMES 	= new String[]
    {
    	"vulture1",
    	"vulture2",
    };
    
    String[] UNDERGROUND_BEE_REGION_NAMES 	= new String[]
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
  	int BUTTON_MUSIC 				= 0;
  	int BUTTON_SFX 				    = 1;
  	int BUTTON_EXIT 				= 2;
  	int BUTTON_YIELD				= 3;
  	int BUTTON_RESUME				= 4;
  	int BUTTON_BACK 				= 5;
  	int BUTTON_PAUSE 				= 6;
  	int LABEL_LEVEL 				= 7;
  	int LABEL_SCORE 				= 8;
  	int BUTTON_MUSIC_OFF			= 9;
  	int BUTTON_SFX_OFF				= 10;
  	
    //main menu
  	String TEXT_PLAY		= "PLAY";
    String TEXT_TUTO		= "TUTORIAL";
    String TEXT_ABOUT		= "ABOUT";
    
  	int TEXT_BUTTON_X 		= SCREEN_WIDTH - 30;
  	int TEXT_BUTTON_Y 		= SCREEN_HEIGHT - 150;
  	
  	int STYLE_BUTTON_WH		= 54;
  	
    float BUTTON_EXIT_X 	= TEXT_BUTTON_X;
    float BUTTON_EXIT_Y 	= 30 + STYLE_BUTTON_WH;
    
    float BUTTON_MUSIC_X 	= 30;
    float BUTTON_MUSIC_Y 	= 30;
    
    float BUTTON_SFX_X 		= BUTTON_MUSIC_X + STYLE_BUTTON_WH + 10;
    float BUTTON_SFX_Y 		= BUTTON_MUSIC_Y;
    
    //Pause menu
    int TEXT_BOX_WIDTH 		= 395;
    int TEXT_BOX_HEIGHT		= 406;
    int TEXT_BOX_X 			= (SCREEN_WIDTH >> 1) - (TEXT_BOX_WIDTH >> 1);
    int TEXT_BOX_Y 			= (SCREEN_HEIGHT >> 1) - (TEXT_BOX_HEIGHT >> 1);
    
    float PAUSE_EXIT_X 		= (TEXT_BOX_X + TEXT_BOX_WIDTH) - (STYLE_BUTTON_WH + 30);
    float PAUSE_EXIT_Y 		= TEXT_BOX_Y + 40;
    
    float PAUSE_YIELD_X		= TEXT_BOX_X + 30;
    float PAUSE_YIELD_Y		= TEXT_BOX_Y + 40;
    
    float PAUSE_RESUME_X	= (SCREEN_WIDTH >> 1) - (STYLE_BUTTON_WH >> 1);
    float PAUSE_RESUME_Y	= TEXT_BOX_Y + 20;
    
    float PAUSE_MUSIC_X		= (TEXT_BOX_X + TEXT_BOX_WIDTH) + 10;
    float PAUSE_MUSIC_Y		= (TEXT_BOX_Y + TEXT_BOX_HEIGHT) - STYLE_BUTTON_WH;
    
    float PAUSE_SFX_X		= PAUSE_MUSIC_X + (STYLE_BUTTON_WH + 10);
    float PAUSE_SFX_Y		= PAUSE_MUSIC_Y;
    
    //result screen
    float RESULT_YIELD_X	= ((SCREEN_WIDTH >> 1) - (STYLE_BUTTON_WH)) - 40 ;
    float RESULT_YIELD_Y	= TEXT_BOX_Y + 30;
    
    float RESULT_RETRY_X	= (SCREEN_WIDTH >> 1) + 40;
    float RESULT_RETRY_Y	= TEXT_BOX_Y + 30;
    
    //Sound
    String GAMEPLAY_MUSIC	= "SOUND/LoopMusic.ogg";
    String MAIN_MENU_MUSIC	= "SOUND/background.mp3";
    String SFX_JUMP 		= "SOUND/jump.wav";
    String SFX_HIT	 		= "SOUND/hit.wav";
    
    /*String MUSIC_ON 		= "music_on";
    String MUSIC_OFF 		= "music_off";
    String SFX_ON 			= "sound_on";
    String SFX_OFF 			= "sound_off";*/
}
