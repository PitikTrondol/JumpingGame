package com.afriandi.project.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.afriandi.project.screens.ScreenManager;
import com.afriandi.project.utils.Constants;

public class Background extends Actor
{
	private int TOTAL_PARALLAX = 4; //total background (and foreground) to be drawn
    private int level;
    private static float distance;
    private String fileName;
    
	private TextureRegion[] layers;
	private TextureAtlas textureAtlas;
	private Rectangle[] bounds;
    
	public Background()
	{
		distance = 0;
		level = ScreenManager.instance().getLevel();
		fileName = Constants.BACKGROUND_ATLAS+level+".txt";
		
		textureAtlas = new TextureAtlas(Gdx.files.internal(fileName));
		bounds = new Rectangle[TOTAL_PARALLAX*2];
		layers = new TextureRegion[TOTAL_PARALLAX];
		
		initParallax(textureAtlas);
	}
	
	@Override
    public void act(float delta)
	{
		if(	ScreenManager.instance().getState() == Constants.STATE_PAUSED
			|| ScreenManager.instance().getState() == Constants.STATE_OVER
		)
			return;
		
		super.act(delta);
		for(int i = 1; i < TOTAL_PARALLAX; i++)
        {
        	if (leftBoundsReached(i, delta))
            {
            	resetBounds(i);
            }
            else
            {
                updateXBounds(i, -delta);
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        super.draw(batch, parentAlpha);
        drawParallax(batch);
    }
    
    public void initParallax(TextureAtlas textureAtlas)
	{
		String name;
		for(int i = 0; i < TOTAL_PARALLAX; i++)
		{
			name = i == 0 ? "farback" : ("layer"+i);
			layers[i] = textureAtlas.findRegion(name);
			
			bounds[i] = new Rectangle(-Constants.SCREEN_WIDTH / 2, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
			bounds[i+TOTAL_PARALLAX] = new Rectangle(Constants.SCREEN_WIDTH / 2, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		}
	}
    
    public void drawParallax(Batch batch)
    {
    	batch.draw(layers[0], 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    	
    	for(int i = TOTAL_PARALLAX - 1; i > 0; i--)
    	{
    		batch.draw(layers[i], bounds[i].x, bounds[i].y);
    		batch.draw(layers[i], bounds[i+TOTAL_PARALLAX].x, bounds[i+TOTAL_PARALLAX].y);
    	}
    }
    
    private boolean leftBoundsReached(int layer, float delta)
	{
		int moveSpeed = getLayerSpeed(layer);
		return (bounds[layer+TOTAL_PARALLAX].x - (delta * moveSpeed)) <= 0;
	}
    
    private void resetBounds(int layer)
    {
    	bounds[layer] = bounds[layer+TOTAL_PARALLAX];
    	bounds[layer+TOTAL_PARALLAX] = new Rectangle(Constants.SCREEN_WIDTH, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    }
    
    private void updateXBounds(int layer, float delta)
    {
    	int moveSpeed = getLayerSpeed(layer);
    	bounds[layer].x += delta * moveSpeed;
    	bounds[layer+TOTAL_PARALLAX].x += delta * moveSpeed;
    	
    	if(layer == 1)
		{
			setDistance(bounds[layer+TOTAL_PARALLAX].x / Constants.WORLD_TO_SCREEN);
		}
    }
    
    private int getLayerSpeed(int layer)
    {
    	switch(layer)
    	{
    		case 1 : return 180;
    		case 2 : return 90;
    		case 3 : return 30;
    	}
    	return 0;
    }
    
    private void setDistance(float addDIstance)
    {
    	distance += addDIstance;
    }
    
    public static float getDistance()
    {
    	return (float)Math.floor(distance);
    }
}
