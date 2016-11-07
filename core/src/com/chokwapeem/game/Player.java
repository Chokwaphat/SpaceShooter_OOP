package com.chokwapeem.game;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.chokwapeem.game.Spaceshooter;

public class Player extends SpaceObject {
	
	private ArrayList<Bullet> bullets;
	private final int MAX_BULLETS = 7;
	
	private boolean left;
	private boolean right;
	private boolean up;

	private float maxSpeed;
	private float accelerate;
	private float decelerate;

	public Player (ArrayList<Bullet> bullets) {
		
		this.bullets = bullets;
		x = Spaceshooter.WIDTH / 2 - 200;
		y = Spaceshooter.HEIGHT / 2;

		maxSpeed = 300;
		accelerate = 200;
		decelerate = 10;

		shapex = new float[4];
		shapey = new float[4];
		
		radians = (float)Math.PI  / 2;
		rotationSpeed = 3;

	}
	
	private void setShape() {
		shapex[0] = x + MathUtils.cos(radians) * 13;
		shapey[0] = y + MathUtils.sin(radians) * 13;

		shapex[1] = x + MathUtils.cos(radians - 4 * (float)Math.PI / 5) * 13;
		shapey[1] = y + MathUtils.sin(radians - 4 * (float)Math.PI  / 5) * 13;

		shapex[2] = x + MathUtils.cos(radians + (float)Math.PI ) * 5;
		shapey[2] = y + MathUtils.sin(radians + (float)Math.PI ) * 5;

		shapex[3] = x + MathUtils.cos(radians + 4 * (float)Math.PI  / 5) * 13;
		shapey[3] = y + MathUtils.sin(radians + 4 * (float)Math.PI  / 5) * 13;

	}

	public void setLeft(boolean b) {
		
		left = b;
	}

	public void setRight(boolean b) {
		
		right = b;
	}

	public void setUp(boolean b) {
		
		up = b;
	}
	
	public void shoot() {
		
		if(bullets.size() == MAX_BULLETS) {
			
			return;
		}
		
		bullets.add(new Bullet(x, y, radians));
	}
	
	public void update(float dt) {
		
		if(left) {
			radians += rotationSpeed * dt;
		} else if(right) {
			radians -= rotationSpeed * dt;
		}

		if(up) {
			dx += MathUtils.cos(radians) * accelerate * dt;
			dy += MathUtils.sin(radians) * accelerate * dt;
			
		}

		float vec = (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
		if(vec > 0) {
			dx -= (dx / vec) * decelerate * dt;
			dy -= (dy / vec) * decelerate * dt;
		}

		if(vec > maxSpeed) {
			dx = (dx / vec) * maxSpeed;
			dy = (dy / vec) * maxSpeed;
		}

		x += dx * dt;
		y += dy * dt;

		setShape();

		wrap();
	}

	public void draw(ShapeRenderer sr) {

		sr.setColor(255, 255, 0, 1);

		sr.begin(ShapeType.Line);

		for(int i = 0, j = shapex.length - 1; i < shapex.length; j = i++) {
			sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);
		}
		
		sr.end();

	}
}
