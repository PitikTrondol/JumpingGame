package com.afriandi.project.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.afriandi.project.buttons.GameButtons.ButtonListener;

public class TextButton
{
	private static final Color NORMAL_COLOR = new Color(0.99f, 0.99f, 0.99f, 0.99f);
	private static final Color HOVER_COLOR = new Color(0f, 0.5f, 0f, 1f);
	
	private String caption = null;
	private BitmapFont font = null;
	private int x = 0;
	private int y = 0;
	private int width = 0;
	private int height = 0;
	private Rectangle bounds = null;
	private ButtonListener handler = null;

	public TextButton(String caption, BitmapFont font, ButtonListener handler, int x, int y) 
	{
		this.caption = caption;
		this.font = font;
		this.x = x;
		this.y = y;
		this.handler = handler;
		calculateDimensions();
	}
	
	public TextButton(String caption, BitmapFont font, ButtonListener handler) 
	{
		this(caption, font, handler, 0, 0);
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
	
	public int getX() 
	{
		return x;
	}
	
	public void setX(int x) 
	{
		this.x = x;
		bounds.x = x;
	}
	
	public int getY() 
	{
		return y;
	}
	
	public void setY(int y) 
	{
		this.y = y;
		bounds.y = y - height;
	}
	
	public int getWidth() 
	{
		return width;
	}
	
	public int getHeight() 
	{
		return height;
	}
	
	public Rectangle getRect()
	{
		return bounds;
	}
	
	public void draw(SpriteBatch batch, Camera camera) 
	{
		Color originalColor = font.getColor();
		Vector3 cursorPosition = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(cursorPosition);
		boolean isIntersect = bounds.contains(cursorPosition.x, cursorPosition.y);
		font.setColor(isIntersect ? HOVER_COLOR : NORMAL_COLOR);
		font.draw(batch, caption, x, y);
		font.setColor(originalColor);
		if (isIntersect && (Gdx.input.isTouched() || Gdx.input.isButtonPressed(Buttons.LEFT))) 
		{
			handler.OnRelease();
		}
	}
	
	private void calculateDimensions() 
	{
        GlyphLayout dimensions = new GlyphLayout();
        dimensions.setText(font, caption);
//		TextBounds dimensions = font.getBounds(caption);
		width = Math.round(dimensions.width);
		height = Math.round(dimensions.height);
		bounds = new Rectangle(x, y - height, width, height);
	}
}
