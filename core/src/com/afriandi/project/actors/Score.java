package com.afriandi.project.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.afriandi.project.screens.ScreenManager;
import com.afriandi.project.utils.Constants;
import com.afriandi.project.utils.ResourceManager;

public class Score extends Actor
{
	private float score;
	private boolean isScore;
    private int multiplier;
    private Rectangle bounds;
    private BitmapFont font;
	
    public Score(Rectangle bounds, boolean isScore) 
    {
        this.bounds = bounds;
        setWidth(bounds.width);
        setHeight(bounds.height);
        score = 0;
        
        this.isScore = isScore;
        multiplier = 5;
        font = ResourceManager.instance().getFontMid();
    }
    
    @Override
    public void act(float delta) 
    {
        super.act(delta);
        if (ScreenManager.instance().getState() != Constants.STATE_GAMEPLAY) 
        {
            return;
        }
        
        if(isScore)
        	score += multiplier * delta;
        else
        	score = ScreenManager.instance().getDifficulty().getLevel();
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) 
    {
    	super.draw(batch, parentAlpha);
        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, String.format("%d", getScore()));
    	font.draw(batch, layout, bounds.x, bounds.y);
    }
    
    public int getScore() 
    {
        return (int) Math.floor(score);
    }
    
    public void setMultiplier(int multiplier)
    {
        this.multiplier = multiplier;
    }
}
