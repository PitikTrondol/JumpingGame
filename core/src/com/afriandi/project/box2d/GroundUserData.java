package com.afriandi.project.box2d;

import com.afriandi.project.enums.UserDataType;

public class GroundUserData extends UserData
{
	public GroundUserData(float width, float height)
	{
		super(width, height);
		userDataType = UserDataType.GROUND;
	}
}
