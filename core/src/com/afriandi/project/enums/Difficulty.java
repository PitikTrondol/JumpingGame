package com.afriandi.project.enums;

import com.badlogic.gdx.math.Vector2;
import com.afriandi.project.utils.Constants;

public enum Difficulty
{
	DIFFICULTY_1(1, Constants.ENEMY_LINEAR_VELOCITY, Constants.GRAVITY_SCALE, Constants.HERO_JUMPING_LINEAR_IMPULSE, 5),
    DIFFICULTY_2(2, new Vector2(-12f, 0f), Constants.GRAVITY_SCALE * 1.1f, new Vector2(0, 65f), 10),
    DIFFICULTY_3(3, new Vector2(-14f, 0f), Constants.GRAVITY_SCALE * 1.2f, new Vector2(0, 67f), 20),
	DIFFICULTY_4(4, new Vector2(-16f, 0f), Constants.GRAVITY_SCALE * 1.3f, new Vector2(0, 67f), 40),
    DIFFICULTY_5(5, new Vector2(-18f, 0f), Constants.GRAVITY_SCALE * 1.4f, new Vector2(0, 68f), 80),
    DIFFICULTY_6(6, new Vector2(-20f, 0f), Constants.GRAVITY_SCALE * 1.4f, new Vector2(0, 70f), 120),
    DIFFICULTY_7(7, new Vector2(-22f, 0f), Constants.GRAVITY_SCALE * 1.5f, new Vector2(0, 73f), 160),
    DIFFICULTY_8(8, new Vector2(-24f, 0f), Constants.GRAVITY_SCALE * 1.5f, new Vector2(0, 73f), 200),
    DIFFICULTY_9(9, new Vector2(-26f, 0f), Constants.GRAVITY_SCALE * 1.6f, new Vector2(0, 75f), 250),
    DIFFICULTY_10(10, new Vector2(-28f, 0f), Constants.GRAVITY_SCALE * 1.7f, new Vector2(0, 75f), 300),
    DIFFICULTY_11(11, new Vector2(-30f, 0f), Constants.GRAVITY_SCALE * 1.8f, new Vector2(0, 75f), 350),
    DIFFICULTY_12(12, new Vector2(-32f, 0f), Constants.GRAVITY_SCALE * 1.8f, new Vector2(0, 80f), 400),
    DIFFICULTY_13(13, new Vector2(-34f, 0f), Constants.GRAVITY_SCALE * 2.1f, new Vector2(0, 80f), 500);
	
	private int level;
    private Vector2 enemyLinearVelocity;
    private float heroGravityScale;
    private Vector2 heroJumpingLinearImpulse;
    private int scoreMultiplier;
    
    Difficulty(int level, Vector2 obstacleLinearVelocity, float heroGravityScale, Vector2 heroJumpingLinearImpulse, int scoreMultiplier)
    {
    	this.level = level;
        this.enemyLinearVelocity = obstacleLinearVelocity;
        this.heroGravityScale = heroGravityScale;
        this.heroJumpingLinearImpulse = heroJumpingLinearImpulse;
        this.scoreMultiplier = scoreMultiplier;
    }
    
    public int getLevel() 
    {
        return level;
    }

    public Vector2 getEnemyLinearVelocity() 
    {
        return enemyLinearVelocity;
    }

    public float getHeroGravityScale() 
    {
        return heroGravityScale;
    }

    public Vector2 getHeroJumpingLinearImpulse() 
    {
        return heroJumpingLinearImpulse;
    }

    public int getScoreMultiplier() 
    {
        return scoreMultiplier;
    }
}
