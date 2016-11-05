package com.chokwapeem.game;

import com.chokwapeem.game.Spaceshooter;

public class SpaceObject {
	
	protected float x;
	protected float y;
	
	protected float dx;
	protected float dy;
	
	protected float radians;
	protected float speed;
	protected float rotationSpeed;
	
	protected int width;
	protected int height;
	
	protected float[] shapex;
	protected float[] shapey;
	
	protected void wrap () {
		if(x < 0) {
			x = Spaceshooter.WIDTH;
		}
		
		if(x > Spaceshooter.WIDTH) {
			x = 0;
		}
		
		if(y < 0) {
			y = Spaceshooter.HEIGHT;
		}
		
		if(y > Spaceshooter.HEIGHT) {
			y = 0;
		}
			
	}
}
