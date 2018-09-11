package com.afriandi.project.utils;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
//import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class DebugUtils
{
	Camera camera;
	ShapeRenderer shapeRender;
	Batch batch; 
	
	public DebugUtils(Camera camera)
	{
		this.camera = camera;
		
		if(batch == null)
		batch = new SpriteBatch();
		
		if(shapeRender == null)
		shapeRender = new ShapeRenderer();
	}
	
	public void debugRectangle(float x, float y, float w, float h, Color color)
	{
		Rectangle rect = new Rectangle(x, y, w, h);
		debugRectangle(this.batch, rect, color);
	}
	
	public void debugRectangle(Rectangle rect, Color color)
	{
		debugRectangle(this.batch, rect, color);
	}
	
	public void debugRectangle(Batch batch, Rectangle rect, Color color)
	{
		//batch.setProjectionMatrix(camera.combined);

		//shapeRender.setColor(color);
		//shapeRender.begin(ShapeType.Line);
		//shapeRender.rect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
		//shapeRender.end();
	}
}
