package com.afriandi.project.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.afriandi.project.box2d.GroundUserData;
import com.afriandi.project.screens.ScreenManager;
import com.afriandi.project.utils.Constants;

public class Ground extends GameActor
{
	private final TextureRegion textureRegion;
	private int speed = Constants.WORLD_SPEED;
	private int level;
	
    private Rectangle textureRegionBounds1;
    private Rectangle textureRegionBounds2;
    
	public Ground(Body body)
	{
		super(body);
		level = ScreenManager.instance().getLevel();
		textureRegion = new TextureRegion(new Texture(Gdx.files.internal(Constants.GROUND_IMAGE_PATH+level+".png")));
        textureRegionBounds1 = new Rectangle(0 - getUserData().getWidth() / 2, 0, getUserData().getWidth(), getUserData().getHeight());
        textureRegionBounds2 = new Rectangle(getUserData().getWidth() / 2, 0, getUserData().getWidth(), getUserData().getHeight());
	}

	@Override
	public GroundUserData getUserData()
	{
		return (GroundUserData) actorData;
	}
	
	@Override
    public void act(float delta)
	{
        super.act(delta);
        if(ScreenManager.instance().getState() == Constants.STATE_PAUSED)
			return;
        
        if (leftBoundsReached(delta))
        {
            resetBounds();
        }
        else
        {
            updateXBounds(-delta);
        }
    }
	
	@Override
    public void draw(Batch batch, float parentAlpha)
	{
        super.draw(batch, parentAlpha);
        batch.draw(textureRegion, textureRegionBounds1.x, bodyRectangle.y, bodyRectangle.getWidth(), bodyRectangle.getHeight());
        batch.draw(textureRegion, textureRegionBounds2.x, bodyRectangle.y, bodyRectangle.getWidth(), bodyRectangle.getHeight());
    }

    private boolean leftBoundsReached(float delta)
    {
        //fix blinking on the left side
        return (textureRegionBounds2.x - transformToScreen(delta * speed)) <= -2*Constants.WORLD_TO_SCREEN;
    }

    private void updateXBounds(float delta)
    {
        textureRegionBounds1.x += transformToScreen(delta * speed);
        textureRegionBounds2.x += transformToScreen(delta * speed);
    }

    private void resetBounds()
    {
        textureRegionBounds1 = textureRegionBounds2;
        textureRegionBounds2 = new Rectangle(textureRegionBounds1.x + bodyRectangle.width, 0, bodyRectangle.width, bodyRectangle.height);
    }
    
}
