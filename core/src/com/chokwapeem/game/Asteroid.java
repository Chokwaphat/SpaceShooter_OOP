package com.chokwapeem.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;

public class Asteroid extends SpaceObject {
	
	private int type;
	public static final int SMALL = 0;
	public static final int MEDIUM = 1;
	public static final int LARGE = 2;
	
	private int numPoints;
	private float[] dists;
	
	private boolean remove;
	
	public Asteroid(float x, float y, int type) {
		
		this.x = x;
		this.y = y;
		this.type = type;
		
		if(type == SMALL) {
			numPoints = 8;
			width = 40;
			height = 40;
			speed = MathUtils.random(70, 100);
			
		}
		
		if(type == MEDIUM) {
			numPoints = 10;
			width = 20;
			height = 40;
			speed = MathUtils.random(50, 60);
			
		}
		
		
		if(type == LARGE) {
			numPoints = 12;
			width = 40;
			height = 40;
			speed = MathUtils.random(20, 30);
			
		}
		
		rotationSpeed = MathUtils.random(-1, 1);
		radians = MathUtils.random(2 * (float)Math.PI);
		dx = MathUtils.cos(radians) * speed;
		dy = MathUtils.sin(radians) * speed;
		
		shapex = new float[numPoints];
		shapey = new float[numPoints];
		dists = new float[numPoints];
		
		int radius = width / 2;
		for(int i = 0; i < numPoints; i++) {
			dists[i] = MathUtils.random(radius / 2, radius);
		}
		
		setShape();
		
	}
	
	private void setShape() {
		float angle = 0;
		for(int i = 0; i < numPoints; i++) {
			shapex[i] = x + MathUtils.cos(angle + radians) * dists[i];
			shapey[i] = y + MathUtils.sin(angle + radians) * dists[i];
			angle += 2 * (float)Math.PI / numPoints;
		}
	
	}
	
	public boolean shouldRemove() {
		return remove;
	}
	
	public void update(float dt) {
		x += dx * dt;
		y += dy * dt;
		
		radians += rotationSpeed * dt;
		setShape();
		
		wrap();
		
	}
	
	public void draw(ShapeRenderer sr) {
		sr.setColor(1, 1, 1, 1);
		sr.begin(ShapeType.Line);
		for(int i = 0, j = shapex.length - 1; i < shapex.length; j = i++) {
			sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);
		}
		sr.end();
	}
}
