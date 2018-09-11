package com.afriandi.project.utils;

import java.util.Random;

import com.afriandi.project.enums.EnemyType;

public class RandomUtils
{
	public static EnemyType getRandomEnemyType(int level)
	{
        RandomEnum<EnemyType> randomEnum = new RandomEnum<EnemyType>(EnemyType.class);
        return randomEnum.random(level);
    }

    /**
     * mung nyonto
     * @check [Stack Overflow](http://stackoverflow.com/a/1973018) bro
     * @param <E>
     */

    private static class RandomEnum<E extends Enum<?>>
    {
    	private final int totalEnemy = 4;
        private static final Random RND = new Random();
        private final E[] values;

        public RandomEnum(Class<E> token)
        {
            values = token.getEnumConstants();
        }

        public E random(int level)
        {
        	int start = (level - 1) * totalEnemy;
            return values[RND.nextInt(values.length)];
        }
    }
}
