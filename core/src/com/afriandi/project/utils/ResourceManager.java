package com.afriandi.project.utils;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;



/**Assign class AssetsManager is the only object that hold game assets*/
public class ResourceManager 
{
	private static ResourceManager s_managerInstance = new ResourceManager();//singleton
	
	private TextureAtlas s_gameTextureAtlas;
	private HashMap<String, TextureRegion> s_texturesMap;
	private HashMap<String, Animation> s_animationsMap;
	private BitmapFont fontSmall;
    private BitmapFont fontMid;
    private BitmapFont fontBig;
	
	private ResourceManager()
	{
		s_gameTextureAtlas 	= new TextureAtlas(Gdx.files.internal(Constants.MENU_ATLAS_LIST[0]));
		s_texturesMap 		= new HashMap<String, TextureRegion>();
		s_animationsMap 	= new HashMap<String, Animation>();
		
		fontSmall 	= new BitmapFont();
		fontMid 	= new BitmapFont();
		fontBig 	= new BitmapFont();
	}
	
	public static ResourceManager instance()
	{
		if(null == s_managerInstance)
			s_managerInstance = new ResourceManager();
		
		return s_managerInstance;
	}
	
	public void load()
	{
		// Fonts
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Constants.FONT_NAME_ROBOTO));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 32;
        fontMid = generator.generateFont(parameter);
        fontMid.setColor(0.21f, 0.22f, 0.21f, 1f);
        
        parameter.size = 72;
        fontBig = generator.generateFont(parameter);
        fontBig.setColor(0.21f, 0.22f, 0.21f, 1f);
        
        parameter.size = 24;
        fontSmall = generator.generateFont(parameter);
        fontSmall.setColor(0.21f, 0.22f, 0.21f, 1f);
        
        generator.dispose();
	}
	
	//Getter
	public TextureRegion getTextureRegion(String key)
	{
		return s_texturesMap.get(key);
	}
	
	public TextureAtlas getTextureAtlas()
	{
		return s_gameTextureAtlas;
	}
	
	public BitmapFont getFontSmall()
	{
		return fontSmall;
	}
	
	public BitmapFont getFontMid()
	{
		return fontMid;
	}
	
	public BitmapFont getFontBig()
	{
		return fontBig;
	}
	
	public void dispose()
	{
		s_managerInstance = null;
		s_gameTextureAtlas.dispose();
		s_texturesMap.clear();
		s_animationsMap.clear();
		
		fontMid.dispose();
		fontBig.dispose();
		fontSmall.dispose();
	}
}
