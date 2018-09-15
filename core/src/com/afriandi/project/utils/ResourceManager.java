package com.afriandi.project.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;



/**Assign class AssetsManager is the only object that hold game assets*/
public class ResourceManager 
{
	private static ResourceManager s_managerInstance = new ResourceManager();//singleton
	
//	private TextureAtlas s_gameTextureAtlas;
//	private HashMap<String, TextureRegion> s_texturesMap;
//	private HashMap<String, Animation> s_animationsMap;

    private BitmapFont[] fontRoboto;
    private BitmapFont fontAlphacrisp;

	private ResourceManager()
	{
//		s_gameTextureAtlas 	= new TextureAtlas(Gdx.files.internal(Constants.MENU_ATLAS_LIST[0]));
//		s_texturesMap 		= new HashMap<String, TextureRegion>();
//		s_animationsMap 	= new HashMap<String, Animation>();

        fontRoboto = new BitmapFont[3];
	}
	
	public static ResourceManager instance()
	{
		if(null == s_managerInstance)
			s_managerInstance = new ResourceManager();
		
		return s_managerInstance;
	}
	
	public void loadFonts()
    {
        // Fonts
        int scaleSize = (Gdx.graphics.getWidth() / Constants.SCREEN_WIDTH);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Constants.FONT_NAME_ROBOTO));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        for(int i = 0; i < fontRoboto.length; i++)
        {
            if(null == fontRoboto[i])
            {
                parameter.size = (18 << i) * scaleSize;
                fontRoboto[i] = generator.generateFont(parameter);
                fontRoboto[i].setColor(0.21f, 0.22f, 0.21f, 1f);
            }
        }

        generator = new FreeTypeFontGenerator(Gdx.files.internal(Constants.FONT_NAME_ALPHA));
        parameter.size = (72 * scaleSize);
        fontAlphacrisp = generator.generateFont(parameter);

        generator.dispose();
    }

	public void loadSprites()
	{
        // Sprite
	}
	
	//Getter
	public BitmapFont getFontSmall()
	{
		return fontRoboto[0];
	}
	
	public BitmapFont getFontMid()
	{
		return fontRoboto[1];
	}
	
	public BitmapFont getFontBig()
	{
		return fontRoboto[2];
	}

    public BitmapFont getFontSplash()
    {
        return fontAlphacrisp;
    }
	
	public void dispose()
	{
//		s_gameTextureAtlas.dispose();
//		s_texturesMap.clear();
//		s_animationsMap.clear();

		for (int i = 0; i < fontRoboto.length; i++)
        {
            fontRoboto[i].dispose();
        }
        fontRoboto = null;

		s_managerInstance = null;
	}
}
