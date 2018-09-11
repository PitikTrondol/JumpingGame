package com.afriandi.project.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GameLabel extends Actor
{
	private static final Color COLOR = new Color(1f, 1f, 1f, 1f);
	
	private String caption = null;
	private BitmapFont font = null;
	private float x = 0;
	private float y = 0;
	private float width = 0;
	private float height = 0;
	private boolean isABoutText;
	
	public GameLabel(String caption, BitmapFont font, int x, int y, boolean aboutText)
	{
		this.caption = caption;
		this.font = font;
		this.x = x;
		this.y = y;
		this.isABoutText = aboutText;
		calculateDimensions();
	}
	
	public GameLabel(String caption, BitmapFont font) 
	{
		this(caption, font, 0, 0, false);
	}
	
	public String getCaption() 
	{
		return caption;
	}
	
	public void setCaption(String caption) 
	{
		this.caption = caption;
		calculateDimensions();
	}
	
	public void setX(float x) 
	{
		this.x = x;
	}
	
	public void setY(float y) 
	{
		this.y = y;
	}
	
	@Override
    public void draw(Batch batch, float parentAlpha) 
	{
        super.draw(batch, parentAlpha);

        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, caption);
        Color originalColor = font.getColor();
		font.setColor(COLOR);
		
		if(isABoutText)
			font.draw(batch, layout, x, y);
		else
			font.draw(batch, caption, x, y);
		
		font.setColor(originalColor);
    }
	
	public void draw(Batch batch)
	{
		Color originalColor = font.getColor();
		font.setColor(COLOR);

		GlyphLayout layout = new GlyphLayout();
		layout.setText(font, caption);
		
		if(isABoutText)
            font.draw(batch, layout, x, y);
		else
			font.draw(batch, caption, x, y);
		
		font.setColor(originalColor);
	}
	
	private void calculateDimensions() 
	{
        GlyphLayout dimensions = new GlyphLayout();
        dimensions.setText(font, caption);

		this.width = Math.round(dimensions.width);
		this.height = Math.round(dimensions.height);
		
		setWidth(width);
        setHeight(height);
	}
}
