package com.afriandi.project.buttons;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.afriandi.project.utils.Constants;

public abstract class GameButtons extends Button
{
	protected Rectangle bounds;
	protected ButtonListener listener;
	
	protected String region;
	private Skin skin;
	private TextureAtlas buttonAtlas;
    
	public GameButtons(Rectangle bounds, ButtonListener listener)
	{
		setRect(bounds);
		this.listener = listener;
		
		skin = new Skin();
		buttonAtlas = new TextureAtlas(Constants.BUTTON_ATLAS_PATH);
		skin.addRegions(buttonAtlas);
		loadStyle();
		
		addListener(
			new ClickListener()
			{
				@Override
	            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) 
				{
	                action();
	                loadStyle();
	                return true;
	            }
			});
	}
	
	private void setRect(Rectangle bounds)
	{
		this.bounds = bounds;
		setWidth(bounds.width);
		setHeight(bounds.height);
		setBounds(bounds.x, bounds.y, bounds.width, bounds.height);
	}
	
	protected void loadStyle() 
	{
        ButtonStyle style = new ButtonStyle();
        style.up = skin.getDrawable(getRegionName());
        setStyle(style);
    }
	
	public Rectangle getRect() 
	{
        return bounds;
    }
	
	protected abstract String getRegionName();
	public abstract void action();

	public interface ButtonListener
	{
		public void OnRelease();
		public int getId();
	}
}
