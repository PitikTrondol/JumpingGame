package com.afriandi.project.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.afriandi.project.box2d.EnemyUserData;
import com.afriandi.project.box2d.GroundUserData;
import com.afriandi.project.box2d.HeroUserData;
import com.afriandi.project.enums.EnemyType;
import com.afriandi.project.screens.ScreenManager;

public class WorldUtils 
{
	public static World createWorld()
	{
        return new World(Constants.WORLD_GRAVITY, true);
    }

    public static Body createGround(World world)
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(Constants.GROUND_X, Constants.GROUND_Y));
        
        Body body = world.createBody(bodyDef);
        
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.GROUND_WIDTH / 2, Constants.GROUND_HEIGHT / 2);
        body.createFixture(shape, Constants.GROUND_DENSITY);
        
        //set user data
        body.setUserData(new GroundUserData(Constants.GROUND_WIDTH, Constants.GROUND_HEIGHT));
        shape.dispose();
        return body;
    }
    
    public static Body createHero(World world)
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(Constants.HERO_X, Constants.HERO_Y));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.HERO_WIDTH / 2, Constants.HERO_HEIGHT / 2);
        
        Body body = world.createBody(bodyDef);
        body.setGravityScale(Constants.GRAVITY_SCALE);
        body.createFixture(shape, Constants.HERO_DENSITY);
        body.resetMassData();
        
        //set user data
        body.setUserData(new HeroUserData(Constants.HERO_WIDTH, Constants.HERO_HEIGHT));
        shape.dispose();
        return body;
    }
    
    public static Body createEnemy(World world)
    {
        EnemyType enemyType = RandomUtils.getRandomEnemyType(ScreenManager.instance().getLevel());
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(new Vector2(enemyType.getX(), enemyType.getY()));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(enemyType.getWidth() / 2, enemyType.getHeight() / 2);
        Body body = world.createBody(bodyDef);
        body.createFixture(shape, enemyType.getDensity());
        body.resetMassData();
        EnemyUserData userData = new EnemyUserData(enemyType.getWidth(), enemyType.getHeight(), enemyType.getRegions());
        body.setUserData(userData);
        shape.dispose();
        return body;
    }
}
